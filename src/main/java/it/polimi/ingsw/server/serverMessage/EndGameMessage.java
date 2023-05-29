package it.polimi.ingsw.server.serverMessage;


public class EndGameMessage implements ServerMessage {


    private final String message;

    public EndGameMessage() {
        this.message = "The match is over";
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
