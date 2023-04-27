package it.polimi.ingsw.server.serverMessage;

public class ErrorMessage implements ServerMessage {

    private final Error type;


    public ErrorMessage(Error type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return String.valueOf(type);
    }
}
