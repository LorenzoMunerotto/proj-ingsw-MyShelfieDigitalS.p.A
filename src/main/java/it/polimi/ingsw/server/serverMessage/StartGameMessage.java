package it.polimi.ingsw.server.serverMessage;

/**
 * This class represent the message that will start the game
 */
public class StartGameMessage implements ServerMessage {

    private final String message;

    public StartGameMessage(){
        this.message = "The match has started!";
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
