package it.polimi.ingsw.server.serverMessage;

public class CustomMessage implements ServerMessage {

    private final String message;

    public CustomMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
