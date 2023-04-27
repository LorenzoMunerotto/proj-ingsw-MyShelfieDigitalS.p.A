package it.polimi.ingsw.server.serverMessage;

public class ConnectionMessage implements ServerMessage {

    private final String message;

    public ConnectionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
