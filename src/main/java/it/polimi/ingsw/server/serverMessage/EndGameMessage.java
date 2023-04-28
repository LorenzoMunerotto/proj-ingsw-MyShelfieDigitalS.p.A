package it.polimi.ingsw.server.serverMessage;


public class EndGameMessage implements ServerMessage {


    private final String message;

    public EndGameMessage() {
        this.message = "The match is over";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
