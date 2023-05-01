package it.polimi.ingsw.server;


import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.model.gameState.events.*;
import it.polimi.ingsw.server.serverMessage.BoardUpdateMessage;
import it.polimi.ingsw.server.serverMessage.ServerMessage;
import it.polimi.ingsw.server.serverMessage.StartTurnMessage;

/**
 * This class represents the Client in the Server.
 * For each client connected the server has an instance of this object
 * Every VirtualClients is a listeners of his GameData and his Managers,
 * so when they notify the virtual client of an Event [ModelEvent] virtualClient
 * manages it with the method handle(ModelEvent modelEvent)
 */
public class VirtualClient implements ModelChangeEventHandler {

    private final SocketClientConnection socketClientConnection;
    private final Integer clientID;
    private final String username;
    private final GameHandler gameHandler;

    public VirtualClient(SocketClientConnection socketClientConnection, String username, Integer clientID, GameHandler gameHandler) {
        this.socketClientConnection = socketClientConnection;
        this.username=username;
        this.clientID = clientID;
        this.gameHandler= gameHandler;
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

    public void send(ServerMessage serverMessage){
        socketClientConnection.send(serverMessage);
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    @Override
    public void handle(BoardUpdateEvent boardUpdateEvent) {
          socketClientConnection.send(new BoardUpdateMessage("aggiornamento board", boardUpdateEvent.getBoardGrid()));
    }

    @Override
    public void handle(LibraryUpdateEvent libraryUpdateEvent) {

    }

    @Override
    public void handle(PointsUpdateEvent pointsUpdateEvent) {

    }

    @Override
    public void handle(CurrentPlayerUpdateEvent currentPlayerUpdateEvent) {
        socketClientConnection.send(new StartTurnMessage(currentPlayerUpdateEvent.getUsername()));
    }

    @Override
    public void handle(FirstFullLibraryEvent firstFullLibraryEvent) {

    }



}
