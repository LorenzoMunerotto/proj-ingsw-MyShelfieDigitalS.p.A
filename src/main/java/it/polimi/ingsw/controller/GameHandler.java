package it.polimi.ingsw.controller;

import it.polimi.ingsw.client.clientMessage.Move;
import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.gameEntity.personal_cards.*;
import it.polimi.ingsw.model.gameMechanics.BoardManager;
import it.polimi.ingsw.model.gameMechanics.BreakRulesException;
import it.polimi.ingsw.model.gameMechanics.LibraryManager;
import it.polimi.ingsw.model.gameMechanics.PointsManager;
import it.polimi.ingsw.model.gameState.events.LibraryUpdateEvent;
import it.polimi.ingsw.model.gameState.exceptions.IllegalNumOfPlayersException;
import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.server.VirtualClient;
import it.polimi.ingsw.server.serverMessage.*;

import java.util.*;

public class GameHandler {

    private final List<VirtualClient> virtualClients;
    private final GameData gameData;
    LibraryManager libraryManager;
    BoardManager boardManager;
    PointsManager pointsManager;


    /**
     * Constructor of GameHandler
     */
    public GameHandler() {
        this.gameData = new GameData();
        this.virtualClients = new ArrayList<>();
    }

    /**
     * This method set the managers in order to work on the current Player/library
     */
    public void setUpManagers() {

        libraryManager.setLibrary(gameData.getCurrentPlayer().getLibrary());
        pointsManager.setPlayer(gameData.getCurrentPlayer());
    }

    public void nextPlayer() {
        if (gameData.getFirstFullLibraryUsername().isPresent() && gameData.getCurrentPlayerIndex() == gameData.getNumOfPlayers() - 1) {
           endGame();
        } else if (gameData.getCurrentPlayerIndex() == gameData.getNumOfPlayers() - 1) {
            gameData.setCurrentPlayerIndex(0);
        } else {
            gameData.setCurrentPlayerIndex(gameData.getCurrentPlayerIndex() + 1);
        }
    }

    /**
     * This method manage the game ending
     */
    private void endGame(){
        sendAll(new EndGameMessage());
        for(VirtualClient virtualClient: virtualClients){
            virtualClient.getSocketClientConnection().close();
        }
    }

    /**
     * This method send a message only to the currentPlayer.
     *
     * @param serverMessage is the message to send
     */
    public void sendToCurrentPlayer(ServerMessage serverMessage) {

        for (VirtualClient client : virtualClients) {
            if (Objects.equals(client.getUsername(), gameData.getCurrentPlayer().getUsername())) {
                client.send(serverMessage);
            }
        }
    }

    /**
     * This method process the message Move arrived from a Client.
     *
     * @param move the message arrived from the Client
     */
    public void handle(Move move) {

        setUpManagers();
        int numberOfTiles = move.getCoordinateList().size();

        try {
            libraryManager.hasEnoughSpace(move.getColumn(), numberOfTiles);
            libraryManager.insertItemTiles(move.getColumn(), boardManager.grabItemTiles(move.getCoordinateList()));

            if (!pointsManager.isPresentFirstFullLibraryUsername() && libraryManager.isFull()) {
                pointsManager.setFirstFullLibraryUsername(Optional.of(getCurrentPlayerUsername()));
                pointsManager.setPresentFirstFullLibraryUsername(true);
                gameData.setFirstFullLibraryUsername(getCurrentPlayerUsername());
            }

            pointsManager.updateTotalPoints();

            if (boardManager.isRefillTime()) {
                boardManager.refillBoard();
            }

            sendAll(new EndTurnMessage("turn finished"));
            nextPlayer();

        } catch (BreakRulesException e) {
            sendToCurrentPlayer(new BreakRulesMessage((e.getType())));
            sendToCurrentPlayer(new MoveRequest());

            // based on the exception type, the client must be notified of the error
            // if the methods end without throwing exceptions, the model has changed and an event has been generated by the manager
            // listened by the virtual clients and sent to each client
        }

    }

    public void setNumOfPlayers(Integer num) throws IllegalNumOfPlayersException {
        gameData.setNumOfPlayers(num);
    }

    /**
     * This method set up the GameHandler at the beginning of the match.
     */
    public void startGame() {
        // create the board based on the number of players
        gameData.setBoard(new Board(gameData.getNumOfPlayers()));

        // create the managers
        this.libraryManager = new LibraryManager();
        this.boardManager = new BoardManager(gameData.getBoard(), gameData.getBag());
        this.pointsManager = new PointsManager(gameData.getNumOfPlayers());

        // each virtual client must be notified of the events generated in gameData and in the managers
        for (VirtualClient client : virtualClients) {
            gameData.addListener(client);
            boardManager.addListener(client);
            libraryManager.addListener(client);
            pointsManager.addListener(client);
        }

        // each client must be notified of the events generated in his player
        // it means that each virtual client must be listener of his player
        for (Player player : gameData.getPlayers()) {
            for (VirtualClient client : virtualClients) {
                if (client.getClientID() == player.getClintID()) {
                    player.addListener(client);
                }
            }
        }

        //così comunico a tutti i client chi sono i giocatori e inizializzo librerie e punteggi su ciascun virtualModel
        for (Player player : gameData.getPlayers()) {
            for (VirtualClient client : virtualClients) {
                client.handle(new LibraryUpdateEvent(player.getLibrary(), player.getUsername()));
            }
        }
        // it decides the order of the players
        Collections.shuffle(gameData.getPlayers(), new Random());
        // assign the personal goal cards to each player and the common goal cards
        assignPersonalGoalCard();
        assignCommonGoalCards();
        pointsManager.setCommonGoalCardList(gameData.getCommonGoalCardsList());
        // refill the board
        boardManager.refillBoard();

        gameData.setCurrentPlayerIndex(0); // needs a fix to include the chair
    }

    /**
     * This method set the PersonalGoalCard on each player in gameData
     */
    public void assignPersonalGoalCard() {
        CardsContainer allPersonalGoalCards = new CardsContainer();
        List<PersonalGoalCard> personalGoalCards = allPersonalGoalCards.getPersonalGoalCards(gameData.getNumOfPlayers());
        for (Player player : gameData.getPlayers()) {
            player.setPersonalGoalCard(personalGoalCards.remove(0));
        }
    }

    /**
     * This method set the commonGoalCardList in GameData
     */
    public void assignCommonGoalCards() {
        gameData.setCommonGoalCardsList(CommonCardFactory.createCards());
    }

    /**
     * This method create the Player, and add it in GameData.
     *
     * @param username is the username of the player
     * @param clientId is the id of the client
     */
    public void addPlayer(String username, Integer clientId) {
        gameData.addPlayer(new Player(username, clientId));
    }

    /**
     * This method broadcast a message to the players in game.
     *
     * @param serverMessage is the message to send
     */
    public void sendAll(ServerMessage serverMessage) {
        for (VirtualClient client : virtualClients) {
            client.send(serverMessage);
        }
    }


    /**
     * This method add a VirtualClient in the list of virtualClients.
     *
     * @param virtualClient is the virtualClient to add
     */
    public void addVirtualClient(VirtualClient virtualClient) {
        virtualClients.add(virtualClient);
    }

    public String getCurrentPlayerUsername(){
        return gameData.getCurrentPlayer().getUsername();
    }

    /**
     * This method stop the game when a client disconnected from the server
     * @param username username of the player who lost the connection
     */
    public void stopGameByClientDisconnection(String username){

        for(VirtualClient virtualClient: virtualClients){
            if (!virtualClient.getUsername().equals(username)){
                virtualClient.send(new DisconnectionMessage(username));
                virtualClient.getSocketClientConnection().close();
            }
        }
    }
}
