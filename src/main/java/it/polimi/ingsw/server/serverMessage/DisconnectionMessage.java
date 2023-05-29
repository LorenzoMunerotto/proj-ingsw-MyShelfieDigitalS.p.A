package it.polimi.ingsw.server.serverMessage;

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
