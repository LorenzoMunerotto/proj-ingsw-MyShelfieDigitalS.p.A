package it.polimi.ingsw.controller;

import it.polimi.ingsw.client.clientMessage.Move;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.gameEntity.personal_cards.AllPersonalGoalCards;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import it.polimi.ingsw.model.gameMechanics.BoardManager;
import it.polimi.ingsw.model.gameMechanics.BreakRulesException;
import it.polimi.ingsw.model.gameMechanics.LibraryManager;
import it.polimi.ingsw.model.gameMechanics.PointsManager;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalNumOfPlayersException;
import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.server.VirtualClient;
import it.polimi.ingsw.server.serverMessage.*;

import java.util.*;

public class GameHandler {

    private List<VirtualClient> clients;
    GameData gameData;
    LibraryManager libraryManager;
    BoardManager boardManager;
    PointsManager pointsManager;


    /**
     * Constructor of GameHandler
     */
    public GameHandler() {
        this.gameData = new GameData();
        this.clients = new ArrayList<>();

    }

    /**
     * This method set the managers in order to work on the current Player/library
     */
    public void setUpManagers(){
        libraryManager.setLibrary(gameData.getCurrentPlayer().getLibrary());
        pointsManager.setPlayer(gameData.getCurrentPlayer());
    }

    public void nextPlayer(){


        if (gameData.getFirstFullLibraryUsername().isPresent() && gameData.getCurrentPlayerIndex()== gameData.getNumOfPlayers()-1){
           //broadcast( new EndGameMessage());
        }
        else if (gameData.getCurrentPlayerIndex() == gameData.getNumOfPlayers()-1){
            gameData.setCurrentPlayerIndex(0);

        }
        else{
            gameData.setCurrentPlayerIndex(gameData.getCurrentPlayerIndex()+1);

        }



    }

    /**
     * This method send a message only to the currentPlayer
     * @param serverMessage
     */
    public void sendToCurrentPlayer(ServerMessage serverMessage){

        for (VirtualClient client: clients){
            if (client.getUsername()==gameData.getCurrentPlayer().getUsername()){
                client.send(serverMessage);
            }
        }
    }

    /**
     * This method process the message Move arrived from a Client,
     * @param move
     */
    public void handle(Move move) {

        setUpManagers();
        int numberOfTiles = move.getCoordinateList().size();

        try {
            libraryManager.hasEnoughSpace(move.getColumn(), numberOfTiles);
            libraryManager.insertItemTiles(move.getColumn(), boardManager.grabItemTiles(move.getCoordinateList()));

            //se parte un eccezione non serve fare quello sotto
            pointsManager.updateTotalPoints();

            if (pointsManager.isThereFullLibrary()){
                gameData.setFirstFullLibraryUsername(pointsManager.getFirstFullLibraryUsername().get());
            }

            if( boardManager.isRefillTime()){
                boardManager.refillBoard();
            }

            sendAll(new LibraryUpdateMessage("", gameData.getCurrentPlayer().getUsername(), gameData.getCurrentPlayer().getLibrary()));
            nextPlayer();
            sendAll(new StartTurnMessage(gameData.getCurrentPlayer().getUsername()));
            sendToCurrentPlayer(new MoveRequest());

        }catch (BreakRulesException e){


            sendToCurrentPlayer(new BreakRulesMessage((e.getType())));
            sendToCurrentPlayer(new MoveRequest());
            // qua in base al tipo di eccezione che si è sollevata mando il messaggio al client per avvisarlo dell' errore

            //se i metodi concludono senza solevare eccezioni  il modello è cambiato quindi è stato generato
            // un evento dal manager, ascoltato dai virtual client e inviato ad ogni client
        }

    }

    public void setNumOfPlayers(Integer num) throws IllegalNumOfPlayersException {
        gameData.setNumOfPlayers(num);
    }

    /**
     * This method setup the GameHandler at the beginning of the match
     */
    public void startGame(){

        //istanzio e assegno la board in base al numero di giocatori esatti
        gameData.setBoard(new Board(gameData.getNumOfPlayers()));

        //cro i manager
        this.libraryManager = new LibraryManager();
        this.boardManager = new BoardManager(gameData.getBoard(), gameData.getBag());
        this.pointsManager = new PointsManager(libraryManager);

        // ogni virtual client deve essere notificato degli eventi generati in gameData e nei managers
        for (VirtualClient client : clients){
            gameData.addListener(client);
            boardManager.addListener(client);
            libraryManager.addListener(client);
            pointsManager.addListener(client);
        }

        //ogni client deve essere notificato degli eventi che rigurdano solo lui, tipo l'assegnamento delle carte  personali
        //per questo ogni virtual client deve essere listener del rispetivo oggetto player
        for (Player player : gameData.getPlayers() ){
            for (VirtualClient client: clients){
                if (client.getClientID()==player.getClintID()){
                    player.addListener(client);
                }
            }
        }

        //si decide l'ordine di gioco
        Collections.shuffle(gameData.getPlayers(), new Random()); //farlo in questo modo è una cosa dangerous ma intanto funziona ;)

        //si assegnano le carte personali e comuni
        assignPersonalGoalCard();
        assignCommonGoalCards();

        //completo il set up dei managers
        pointsManager.setCommonGoalCardList(gameData.getCommonGoalCardsList());
        pointsManager.setNumOfPlayers(gameData.getNumOfPlayers());

        //per ultimo prima di partire si refilla la board
        boardManager.refillBoard();

        gameData.setCurrentPlayerIndex(0);
        // (per ora) da modificare usando chair

        //sendAll(new StartGameMessage());

        //comunico a tutti l'inizio del turno di un giocatore
        sendAll(new StartTurnMessage(gameData.getCurrentPlayer().getUsername()));
        //al giocatore corrente invio la richiesta della mossa
        sendToCurrentPlayer(new MoveRequest());
    }

    /**
     * This method set the PersonalGoalCard on each player in gameData
     */
    public void assignPersonalGoalCard(){
        Set<Integer> numberOfPersonalCards = new HashSet<>();
        Random random = new Random();
        AllPersonalGoalCards allPersonalGoalCards = AllPersonalGoalCards.makeAllPersonalGoalCards();
        for(int i=0; i< gameData.getNumOfPlayers();i++){
            while (true){
                int randomNumber= random.nextInt(12);
                if(!numberOfPersonalCards.contains(randomNumber)){
                    PersonalGoalCard randomPersonalGoalCard = allPersonalGoalCards.getCards().get(randomNumber);
                    gameData.getPlayers().get(i).setPersonalGoalCard(randomPersonalGoalCard);
                    numberOfPersonalCards.add(randomNumber);
                    break;
                }
            }
        }

    }

    /**
     * This method set the commonGoalCardList in GameData
     */
    public void assignCommonGoalCards(){

        gameData.setCommonGoalCardsList( CommonCardFactory.createCards());
    }

    /**
     * This method create the Player, and add it in GameData
     * @param username
     * @param clientId
     */
    public void addPlayer(String username, Integer clientId){

            gameData.addPlayer(new Player(username, clientId));

    }

    /**
     * This method broadcast a message to the players in game
     * @param serverMessage
     */
    public void sendAll(ServerMessage serverMessage){
        for (VirtualClient client : clients){
            client.send(serverMessage);
        }

    }


    /**
     * This method add a VirtualClient in the list of virtualClients
     * @param virtualClient
     */
    public void addVirtualClient(VirtualClient virtualClient){
        clients.add(virtualClient);
    }

}
