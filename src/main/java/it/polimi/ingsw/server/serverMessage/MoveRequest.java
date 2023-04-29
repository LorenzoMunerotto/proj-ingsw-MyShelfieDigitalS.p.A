package it.polimi.ingsw.server.serverMessage;

public class MoveRequest implements ServerMessage {

    private final String message;

    public MoveRequest() {
        this.message = "Insert move input";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
