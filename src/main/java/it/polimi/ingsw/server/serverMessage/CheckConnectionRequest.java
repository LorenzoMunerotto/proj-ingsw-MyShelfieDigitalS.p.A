package it.polimi.ingsw.server.serverMessage;


/**
 * This class represents the message that server sends to
 * client for verify the connection
 */
public class CheckConnectionRequest implements ServerMessage {

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return null;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
