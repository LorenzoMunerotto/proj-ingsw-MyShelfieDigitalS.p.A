package it.polimi.ingsw.server.serverMessage;

public class UsernameRequest implements ServerMessage {

    private final String message;

    public UsernameRequest() {
        message = "Please select your username: ";
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
