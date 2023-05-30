package it.polimi.ingsw.server.serverMessage;

/**
 * This class represents the message that server sends to
 * client for communicate that a client has filled his library
 */
public class FirstFullLibraryMessage implements ServerMessage{

    private final String username;
    private final String message;

    public FirstFullLibraryMessage(String username) {
        this.username = username;
        this.message = username + " is the first to fill the library, earns 1 point";
    }

    public String getUsername() {
        return username;
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
