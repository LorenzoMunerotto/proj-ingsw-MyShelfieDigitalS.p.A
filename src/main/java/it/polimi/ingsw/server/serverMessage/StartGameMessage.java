package it.polimi.ingsw.server.serverMessage;

public class StartGameMessage implements ServerMessage {

    private final String message;

    public StartGameMessage(){
        this.message = "The match has started!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
