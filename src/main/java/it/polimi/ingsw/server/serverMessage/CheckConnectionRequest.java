package it.polimi.ingsw.server.serverMessage;

public class CheckConnectionRequest implements ServerMessage {

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
