package it.polimi.ingsw.controller;

import it.polimi.ingsw.client.clientMessage.Move;
import it.polimi.ingsw.client.view.VirtualModelProxy;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.BoardCell;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.personal_cards.CardsContainer;
import it.polimi.ingsw.model.gameEntity.personal_cards.Goal;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;
import it.polimi.ingsw.model.gameMechanics.BoardManager;
import it.polimi.ingsw.model.gameMechanics.BreakRulesException;
import it.polimi.ingsw.model.gameMechanics.LibraryManager;
import it.polimi.ingsw.model.gameMechanics.PointsManager;
import it.polimi.ingsw.model.gameState.exceptions.IllegalNumOfPlayersException;
import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.server.VirtualClient;
import it.polimi.ingsw.server.serverMessage.*;

import java.util.*;

public class GameHandler {

    private final List<VirtualClient> virtualClients;
    private final GameData gameData;
    private final VirtualModelProxy virtualModel;
    LibraryManager libraryManager;
    BoardManager boardManager;
    PointsManager pointsManager;


    /**
     * Constructor of GameHandler
     */
    public GameHandler() {
        this.gameData = new GameData();
        this.virtualModel = new VirtualModelProxy();
        this.virtualClients = new ArrayList<>();
    }

    /**
     * This method set the managers in order to work on the current Player/library
     */
    public void setUpManagers() {
        libraryManager.setLibrary(gameData.getCurrentPlayer().getLibrary());
    }

    public void nextPlayer() {
        if (gameData.getFirstFullLibraryUsername().isPresent() && gameData.getCurrentPlayerIndex() == gameData.getNumOfPlayers() - 1) {
            //broadcast( new EndGameMessage());
        } else if (gameData.getCurrentPlayerIndex() == gameData.getNumOfPlayers() - 1) {
            gameData.setCurrentPlayerIndex(0);
        } else {
            gameData.setCurrentPlayerIndex(gameData.getCurrentPlayerIndex() + 1);
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

            pointsManager.updateTotalPoints();

            if (pointsManager.isPresentFirstFullLibraryUsername() && pointsManager.getFirstFullLibraryUsername().isPresent()) {
                gameData.setFirstFullLibraryUsername(pointsManager.getFirstFullLibraryUsername().get());
            }

            if (boardManager.isRefillTime()) {
                boardManager.refillBoard();
            }

            sendAll(new LibraryUpdateMessage("", gameData.getCurrentPlayer().getUsername(), gameData.getCurrentPlayer().getLibrary()));
            nextPlayer();
            sendAll(new StartTurnMessage(gameData.getCurrentPlayer().getUsername()));
            sendToCurrentPlayer(new MoveRequest());

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
        this.pointsManager = new PointsManager(gameData.getCurrentPlayer(), gameData.getNumOfPlayers(), gameData.getCommonGoalCardsList());

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

        // it decides the order of the players
        Collections.shuffle(gameData.getPlayers(), new Random());

        // assign the personal goal cards to each player and the common goal cards
        assignPersonalGoalCard();
        assignCommonGoalCards();

        // refill the board
        boardManager.refillBoard();

        gameData.setCurrentPlayerIndex(0); // needs a fix to include the chair

        //sendAll(new StartGameMessage());

        // notify every client of the current turn
        sendAll(new StartTurnMessage(gameData.getCurrentPlayer().getUsername()));

        // to the current player is sent a MoveRequest
        sendToCurrentPlayer(new MoveRequest());
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

    public void setUpVirtualModel(){
        updateVirtualBoard();
        updateVirtualLibrary();
        setVirtualPersonalGoalCard();
    }

    /**
     * This method updates the state of the virtual board.
     */
    public void updateVirtualBoard(){
        int row = gameData.getBoard().getROWS();
        int col = gameData.getBoard().getCOLUMNS();
        BoardCell[][] virtualBoard = new BoardCell[row][col];
        for(int currentRow = 0; currentRow < row; currentRow++){
            for(int currentColumn = 0; currentColumn < col; currentColumn++){
                BoardCell cell = gameData.getBoard().getBoardCell(currentRow,currentColumn);
                virtualBoard[currentRow][currentColumn] = new BoardCell(cell.isPlayable());
                if(virtualBoard[currentRow][currentColumn].isPlayable()){
                    virtualBoard[currentRow][currentColumn].setItemTile(new ItemTile(cell.getItemTile().getItemTileType()));
                }
            }
        }
        virtualModel.updateBoard(virtualBoard);
    }

    /**
     * This method updates the state of the virtual library.
     */
    public void updateVirtualLibrary(){
        int row = gameData.getCurrentPlayer().getLibrary().getROWS();
        int col = gameData.getCurrentPlayer().getLibrary().getCOLUMNS();
        ItemTile[][] virtualLibrary = new ItemTile[row][col];
        for(int currentRow = 0; currentRow < row; currentRow++){
            for(int currentColumn = 0; currentColumn < col; currentColumn++){
                ItemTile tile = gameData.getCurrentPlayer().getLibrary().getItemTile(currentRow,currentColumn);
                virtualLibrary[currentRow][currentColumn] = new ItemTile(tile.getItemTileType());
            }
        }
        virtualModel.updateLibrary(virtualLibrary);
    }

    /**
     * This method set the state of the virtual personal goal card.
     */
    public void setVirtualPersonalGoalCard() {
        int row = gameData.getCurrentPlayer().getLibrary().getROWS();
        int col = gameData.getCurrentPlayer().getLibrary().getCOLUMNS();
        ItemTile[][] virtualPersonalGoalCard = new ItemTile[row][col];

        for (int currentRow = 0; currentRow < row; currentRow++) {
            for (int currentColumn = 0; currentColumn < col; currentColumn++) {
                virtualPersonalGoalCard[currentRow][currentColumn] = new ItemTile(ItemTileType.EMPTY);
            }
        }

        List<Goal> goals = gameData.getCurrentPlayer().getPersonalGoalCard().getGoals();
        for (Goal goal : goals) {
            int goalRow = goal.getRow();
            int goalCol = goal.getColumn();
            ItemTileType type = goal.getItemTileType();
            virtualPersonalGoalCard[goalRow][goalCol] = new ItemTile(type);
        }
        virtualModel.updatePersonalGoalCard(virtualPersonalGoalCard);
    }
}
