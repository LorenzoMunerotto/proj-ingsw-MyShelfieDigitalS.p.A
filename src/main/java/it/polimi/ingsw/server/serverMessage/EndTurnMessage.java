package it.polimi.ingsw.server.serverMessage;

public class EndTurnMessage implements ServerMessage {

    private final String message;


    public EndTurnMessage(String message) {
        this.message = message;

    }

    @Override
    public String getMessage() {
        return null;
    }
}
