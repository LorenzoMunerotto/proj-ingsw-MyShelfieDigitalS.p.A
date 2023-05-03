package it.polimi.ingsw.server;


import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.model.gameState.events.*;
import it.polimi.ingsw.server.serverMessage.*;

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


    public void update(ModelEvent modelEvent) {
        modelEvent.accept(this);
    }

    @Override
    public void handle(BoardUpdateEvent boardUpdateEvent) {
        if (boardUpdateEvent.isRefill()==false) {
            socketClientConnection.send(new BoardUpdateMessage("aggiornamento board", boardUpdateEvent.getBoardGrid(), boardUpdateEvent.getPlayableGrid()));
        }
        else{
            socketClientConnection.send(new BoardRefillMessage(boardUpdateEvent.getBoardGrid(), boardUpdateEvent.getPlayableGrid()));
        }


    }

    @Override
    public void handle(LibraryUpdateEvent libraryUpdateEvent) {
        if (libraryUpdateEvent.getUsername()==null) {
            socketClientConnection.send(new LibraryUpdateMessage("aggiornamento libreria", gameHandler.getCurrentPlayerUsername(), libraryUpdateEvent.getLibraryGrid()));
        }
        else{
            socketClientConnection.send(new LibraryUpdateMessage("set up iniziale della libreria", libraryUpdateEvent.getUsername(), libraryUpdateEvent.getLibraryGrid()));
        }
        }


    @Override
    public void handle(PointsUpdateEvent pointsUpdateEvent) {
        socketClientConnection.send(new PointsUpdateMessage("aggiornamento punteggi", pointsUpdateEvent.getUsername(), pointsUpdateEvent.getPoints()));

    }

    @Override
    public void handle(CurrentPlayerUpdateEvent currentPlayerUpdateEvent) {
        socketClientConnection.send(new StartTurnMessage(currentPlayerUpdateEvent.getUsername()));
        if(currentPlayerUpdateEvent.getUsername()==username){
            socketClientConnection.send(new MoveRequest());
        }
    }

    @Override
    public void handle(FirstFullLibraryEvent firstFullLibraryEvent) {
        socketClientConnection.send(new FirstFullLibraryMessage(firstFullLibraryEvent.getUsername()));
    }

    @Override
    public void handle(CommonCardsSetEvent commonCardsSetEvent){
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
