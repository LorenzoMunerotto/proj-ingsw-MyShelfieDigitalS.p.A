package it.polimi.ingsw.server.serverMessage;

/**
 * This class represents the message that server sends to
 * client for communicate a custom string message
 */
public class CustomMessage implements ServerMessage {

    private final String message;

    public CustomMessage(String message) {
        this.message = message;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
