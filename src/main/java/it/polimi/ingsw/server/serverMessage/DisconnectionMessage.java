package it.polimi.ingsw.server.serverMessage;

/**
 * This class represents the message that server sends to
 * client for notify the end of the match because one client lost connection
 */
public class DisconnectionMessage implements ServerMessage{

    private final String username;
    private final String message;

    public DisconnectionMessage(String username) {
        this.username = username;
        this.message = "The game suddenly ended because "+ username + " has lost connection with the server";
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
