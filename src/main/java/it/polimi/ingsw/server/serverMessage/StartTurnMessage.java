package it.polimi.ingsw.server.serverMessage;

public class StartTurnMessage implements ServerMessage {

    private final String message;
    private final String username;

    public StartTurnMessage(String username) {
        this.message = username+ "'s turn started";
        this.username = username;
    }

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
