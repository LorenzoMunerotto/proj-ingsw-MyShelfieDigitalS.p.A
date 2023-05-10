package it.polimi.ingsw.controller;

import it.polimi.ingsw.view.events.Move;
import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.gameEntity.personal_cards.*;
import it.polimi.ingsw.model.gameMechanics.*;
import it.polimi.ingsw.model.gameState.events.LibrarySetEvent;
import it.polimi.ingsw.model.gameState.exceptions.IllegalNumOfPlayersException;
import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.server.VirtualClient;
import it.polimi.ingsw.server.serverMessage.*;

import java.util.*;
import java.util.stream.Collectors;

import static it.polimi.ingsw.model.gameEntity.enums.ItemTileType.EMPTY;
import static it.polimi.ingsw.model.gameEntity.enums.ItemTileType.NULL;

public class GameHandler {

    private final List<VirtualClient> virtualClients;
    private final GameData gameData;
    private LibraryManager libraryManager;
    private BoardManager boardManager;
    private PointsManager pointsManager;

    /**
     * Constructor of GameHandler
     */
    public GameHandler() {
        this.gameData = new GameData();
        this.virtualClients = new ArrayList<>();
    }

    /**
     * This method set up the GameHandler at the beginning of the match.
     */
    public void startGame() {
        gameData.setBoard(new Board(gameData.getNumberOfPlayers()));

        this.libraryManager = new LibraryManager();
        this.boardManager = new BoardManager(gameData.getBoard(), gameData.getBag());
        this.pointsManager = new PointsManager();

        for (VirtualClient client : virtualClients) {
            gameData.addListener(client);
            boardManager.addListener(client);
            libraryManager.addListener(client);
            pointsManager.addListener(client);
        }

        for (Player player : gameData.getPlayers()) {
            for (VirtualClient client : virtualClients) {
                if (client.getClientID() == player.getClintID()) {
                    player.addListener(client);
                }
                client.handle(new LibrarySetEvent(player.getLibrary(), player.getUsername()));
            }
        }

        assignPersonalGoalCard();
        assignCommonGoalCards();
        pointsManager.setCommonGoalCardList(gameData.getCommonGoalCardsList());
        boardManager.refillBoard();
        Collections.shuffle(gameData.getPlayers(), new Random());
        gameData.setCurrentPlayerIndex(0);
        gameData.getCurrentPlayer().setChair(true);
    }
    
    /**
     * This method set the PersonalGoalCard on each player in gameData
     */
    public void assignPersonalGoalCard() {
        CardsContainer allPersonalGoalCards = new CardsContainer();
        List<PersonalGoalCard> personalGoalCards = allPersonalGoalCards.getPersonalGoalCards(gameData.getNumberOfPlayers());
        for (Player player : gameData.getPlayers()) {
            player.setPersonalGoalCard(personalGoalCards.remove(0));
            // personal card set event
        }
    }

    /**
     * This method set the commonGoalCardList in GameData
     */
    public void assignCommonGoalCards() {
        gameData.setCommonGoalCardsList(CommonCardFactory.createCards());
    }

    /**
     * This method process the message Move arrived from a Client.
     *
     * @param move the message arrived from the Client
     */
    public void handle(Move move) {
        setUpManagers();
        try {
            checkMove(move);
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
            sendAll(new EndTurnMessage("The turn is over!"));
            nextPlayer();
        } catch (BreakRulesException e) {
            sendToCurrentPlayer(new BreakRulesMessage((e.getType())));
            sendToCurrentPlayer(new MoveRequest());
        }
    }

    /**
     * This method checks if the coordinates and the column of the move are valid and respect the rules.
     *
     * @param move the move to check
     * @throws BreakRulesException if the move breaks the rules
     */
    public void checkMove(Move move) throws BreakRulesException {
        Board board = boardManager.getBoard();
        Library library = libraryManager.getLibrary();

        int size = move.getCoordinateList().size();
        if (size > 3) throw new BreakRulesException(BreakRules.TOO_MUCH_TILES_SELECTED);
        if (size < 1) throw new BreakRulesException(BreakRules.TOO_FEW_TILES_SELECTED);
        if (new HashSet<>(move.getCoordinateList()).size() < size)
            throw new BreakRulesException(BreakRules.DUPLICATE_TILES_SELECTED);

        if (!(boardManager.isLined(move.getCoordinateList())))
            throw new BreakRulesException(BreakRules.TILES_NOT_ALIGNED);

        for (Coordinate coordinate : move.getCoordinateList()) {
            int row = coordinate.getRow();
            int column = coordinate.getColumn();

            if (board.getItemTile(row, column) == NULL) throw new BreakRulesException(BreakRules.NOT_PLAYABLE_TILE);
            if (board.getItemTile(row, column) == EMPTY) throw new BreakRulesException(BreakRules.EMPTY_CELL);
            if (!boardManager.hasSideFree(row, column)) throw new BreakRulesException(BreakRules.SURROUNDED_TILE);
            if (boardManager.isAlone(row, column)) throw new BreakRulesException(BreakRules.ALONE_TILE);
        }
        if (move.getColumn() < 0 || move.getColumn() >= library.getLibraryGrid()[0].length)
            throw new BreakRulesException(BreakRules.COLUMN_OUT_OF_BOUNDS);
        if (size > libraryManager.emptyTilesCounter(move.getColumn()))
            throw new BreakRulesException(BreakRules.COLUMN_OUT_OF_SPACE);
    }

    /**
     * This method set the managers in order to work on the current Player/library
     */
    public void setUpManagers() {
        libraryManager.setLibrary(gameData.getCurrentPlayer().getLibrary());
        libraryManager.setUsername(gameData.getCurrentPlayer().getUsername());
        pointsManager.setPlayer(gameData.getCurrentPlayer());
    }

    /**
     * This method create the Player and add it in GameData.
     *
     * @param username is the username of the player
     * @param clientId is the id of the client
     */
    public void addPlayer(String username, Integer clientId) {
        gameData.addPlayer(new Player(username, clientId));
    }

    /**
     * This method changes the current player.
     */
    public void nextPlayer() {
        if (gameData.getFirstFullLibraryUsername().isPresent() && gameData.getCurrentPlayerIndex() == gameData.getNumberOfPlayers() - 1) {
            endGame();
        } else if (gameData.getCurrentPlayerIndex() == gameData.getNumberOfPlayers() - 1) {
            gameData.setCurrentPlayerIndex(0);
        } else {
            gameData.setCurrentPlayerIndex(gameData.getCurrentPlayerIndex() + 1);
        }
    }

    /**
     * Set the number of players in the game.
     *
     * @param number is the number of players
     * @throws IllegalNumOfPlayersException if the number of players is not valid
     */
    public void setNumberOfPlayers(Integer number) throws IllegalNumOfPlayersException {
        gameData.setNumberOfPlayers(number);
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

    /**
     * Get the username of the current player.
     *
     * @return the username of the current player
     */
    public String getCurrentPlayerUsername() {
        return gameData.getCurrentPlayer().getUsername();
    }

    /**
     * Get the list of player's username of this game.
     *
     * @return the list of player's username of this game
     */
    public List<String> getPlayersUsername() {
        return gameData.getPlayers().stream().map(Player::getUsername).collect(Collectors.toList());
    }

    /**
     * This method manage the game ending
     */
    private void endGame() {
        sendAll(new EndGameMessage());
        for (VirtualClient virtualClient : virtualClients) {
            virtualClient.getSocketClientConnection().close();
        }
    }

    /**
     * This method stop the game when a client disconnected from the server
     *
     * @param username username of the player who lost the connection
     */
    public void stopGameByClientDisconnection(String username) {

        for (VirtualClient virtualClient : virtualClients) {
            if (!virtualClient.getUsername().equals(username)) {
                virtualClient.send(new DisconnectionMessage(username));
                virtualClient.getSocketClientConnection().close();
            }
        }
    }
}
