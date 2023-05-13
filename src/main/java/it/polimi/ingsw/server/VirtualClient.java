package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.model.gameState.events.*;
import it.polimi.ingsw.server.serverMessage.*;

import java.util.Objects;

/**
 * This class represents the Client in the Server.
 * For each client connected the server has an instance of this object
 */
public class VirtualClient implements ModelChangeEventHandler {

    /**
     * The socket connection to the client.
     */
    private final SocketClientConnection socketClientConnection;
    /**
     * The client ID.
     */
    private final int clientID;
    /**
     * The username of the client.
     */
    private final String username;
    /**
     * The game handler.
     */
    private final GameHandler gameHandler;

    /**
     * Default constructor.
     *
     * @param socketClientConnection the socket connection to the client
     * @param username               the username of the client
     * @param clientID               the client ID
     * @param gameHandler            the game handler
     */
    public VirtualClient(SocketClientConnection socketClientConnection, String username, int clientID, GameHandler gameHandler) {
        this.socketClientConnection = socketClientConnection;
        this.username = username;
        this.clientID = clientID;
        this.gameHandler = gameHandler;
    }

    /**
     * Get the socket connection to the client.
     *
     * @return the socket connection to the client
     */
    public SocketClientConnection getSocketClientConnection() {
        return socketClientConnection;
    }

    /**
     * Get the client ID.
     *
     * @return the client ID
     */
    public Integer getClientID() {
        return clientID;
    }

    /**
     * Get the username of the client.
     *
     * @return the username of the client
     */
    public String getUsername() {
        return username;
    }

    /**
     * Send a message to the client.
     *
     * @param serverMessage the message to send
     */
    public void send(ServerMessage serverMessage) {
        socketClientConnection.send(serverMessage);
    }

    /**
     * Get the game handler.
     *
     * @return the game handler
     */
    public GameHandler getGameHandler() {
        return gameHandler;
    }

    /**
     * Update the client with the model event.
     *
     * @param modelEvent the model event that has been observed
     */
    public void update(ModelEvent modelEvent) {
        modelEvent.accept(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(BoardSetEvent boardSetEvent) {
        socketClientConnection.send(new BoardSetMessage("setting the board", boardSetEvent.getBoardGrid()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(BoardUpdateEvent boardUpdateEvent) {
        socketClientConnection.send(new BoardUpdateMessage("updating the board", boardUpdateEvent.getCoordinates(), boardUpdateEvent.getChecksum()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(BoardRefillEvent boardRefillEvent) {
        socketClientConnection.send(new BoardRefillMessage("board refilled", boardRefillEvent.getBoardGrid(), boardRefillEvent.getChecksum()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(LibraryUpdateEvent libraryUpdateEvent) {
        socketClientConnection.send(new LibraryUpdateMessage("updating the library", gameHandler.getCurrentPlayerUsername(), libraryUpdateEvent.getItemTileTypeList(), libraryUpdateEvent.getColumn(), libraryUpdateEvent.getChecksum()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(LibrarySetEvent librarySetEvent) {
        socketClientConnection.send(new LibrarySetMessage("initial library setup", librarySetEvent.getUsername(), librarySetEvent.getLibraryGrid()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(PointsUpdateEvent pointsUpdateEvent) {
        socketClientConnection.send(new PointsUpdateMessage("updating scores", pointsUpdateEvent.getUsername(), pointsUpdateEvent.getPoints()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(CurrentPlayerUpdateEvent currentPlayerUpdateEvent) {
        socketClientConnection.send(new StartTurnMessage(currentPlayerUpdateEvent.getCurrentPlayer()));
        if (Objects.equals(currentPlayerUpdateEvent.getCurrentPlayer().getValue0(), username)) {
            socketClientConnection.send(new MoveRequest());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(FirstFullLibraryEvent firstFullLibraryEvent) {
        socketClientConnection.send(new FirstFullLibraryMessage(firstFullLibraryEvent.getUsername()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(CommonCardsSetEvent commonCardsSetEvent) {
        socketClientConnection.send(new CommonCardsSetMessage(commonCardsSetEvent));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(CommonCardReachEvent commonCardReachEvent) {
        socketClientConnection.send(new CommonCardReachMessage(commonCardReachEvent));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(PersonalCardSetEvent personalCardSetEvent) {
        socketClientConnection.send(new PersonalCardSetMessage(personalCardSetEvent.getPersonalGoalCard(), personalCardSetEvent.getIndex()));
    }
}