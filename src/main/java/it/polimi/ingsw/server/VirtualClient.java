package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.model.gameState.events.*;
import it.polimi.ingsw.server.serverMessage.*;

import java.util.Objects;

/**
 * This class represents the Client in the Server.
 * For each client connected the server has an instance of this object
 * Every VirtualClients is a listeners of his GameData and his Managers,
 * so when they notify the virtual client of an Event [ModelEvent] virtualClient
 * manages it with the method handle(ModelEvent)
 */
public class VirtualClient implements ModelChangeEventHandler {

    private final SocketClientConnection socketClientConnection;
    private final Integer clientID;
    private final String username;
    private final GameHandler gameHandler;

    public VirtualClient(SocketClientConnection socketClientConnection, String username, Integer clientID, GameHandler gameHandler) {
        this.socketClientConnection = socketClientConnection;
        this.username = username;
        this.clientID = clientID;
        this.gameHandler = gameHandler;
    }

    public SocketClientConnection getSocketClientConnection() {
        return socketClientConnection;
    }

    public Integer getClientID() {
        return clientID;
    }

    public String getUsername() {
        return username;
    }

    public void send(ServerMessage serverMessage) {
        socketClientConnection.send(serverMessage);
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }


    public void update(ModelEvent modelEvent) {
        modelEvent.accept(this);
    }

    @Override
    public void handle(BoardSetEvent boardSetEvent) {
        socketClientConnection.send(new BoardSetMessage("setting the board", boardSetEvent.getBoardGrid()));
    }

    @Override
    public void handle(BoardUpdateEvent boardUpdateEvent) {
        socketClientConnection.send(new BoardUpdateMessage("updating the board", boardUpdateEvent.getCoordinates(), boardUpdateEvent.getChecksum()));
    }

    @Override
    public void handle(BoardRefillEvent boardRefillEvent) {
        socketClientConnection.send(new BoardRefillMessage("board refilled", boardRefillEvent.getBoardGrid(), boardRefillEvent.getChecksum()));
    }

    @Override
    public void handle(LibraryUpdateEvent libraryUpdateEvent) {
        socketClientConnection.send(new LibraryUpdateMessage("updating the library", gameHandler.getCurrentPlayerUsername(), libraryUpdateEvent.getItemTileTypeList(), libraryUpdateEvent.getColumn(), libraryUpdateEvent.getChecksum()));
    }

    @Override
    public void handle(LibrarySetEvent librarySetEvent) {
        socketClientConnection.send(new LibrarySetMessage("initial library setup", librarySetEvent.getUsername(), librarySetEvent.getLibraryGrid()));
    }

    @Override
    public void handle(PointsUpdateEvent pointsUpdateEvent) {
        socketClientConnection.send(new PointsUpdateMessage("updating scores", pointsUpdateEvent.getUsername(), pointsUpdateEvent.getPoints()));

    }

    @Override
    public void handle(CurrentPlayerUpdateEvent currentPlayerUpdateEvent) {
        socketClientConnection.send(new StartTurnMessage(currentPlayerUpdateEvent.getCurrentPlayer()));
        if (Objects.equals(currentPlayerUpdateEvent.getCurrentPlayer().getValue0(), username)) {
            socketClientConnection.send(new MoveRequest());
        }
    }

    @Override
    public void handle(FirstFullLibraryEvent firstFullLibraryEvent) {
        socketClientConnection.send(new FirstFullLibraryMessage(firstFullLibraryEvent.getUsername()));
    }

    @Override
    public void handle(CommonCardsSetEvent commonCardsSetEvent) {
        socketClientConnection.send(new CommonCardsSetMessage(commonCardsSetEvent));
    }

    @Override
    public void handle(CommonCardReachEvent commonCardReachEvent) {
        socketClientConnection.send(new CommonCardReachMessage(commonCardReachEvent));
    }

    @Override
    public void handle(PersonalCardSetEvent personalCardSetEvent) {
        socketClientConnection.send(new PersonalCardSetMessage(personalCardSetEvent.getPersonalGoalCard()));
    }
}