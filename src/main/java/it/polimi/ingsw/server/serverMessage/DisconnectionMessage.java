package it.polimi.ingsw.server.serverMessage;

public class DisconnectionMessage implements ServerMessage{

    private final String username;
    private final String message;

    public DisconnectionMessage(String username) {
        this.username = username;
        this.message = "The game suddenly ended because "+ username + " has lost connection with the server";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
