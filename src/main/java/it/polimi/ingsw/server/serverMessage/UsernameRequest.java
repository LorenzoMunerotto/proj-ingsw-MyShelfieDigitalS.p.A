package it.polimi.ingsw.server.serverMessage;

/**
 * This class represent the message that contains the request for the username
 */
public class UsernameRequest implements ServerMessage {

    private final String message;

    public UsernameRequest() {
        message = "Please select your username: ";
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
