package it.polimi.ingsw.server.serverMessage;

/**
 * This class represents the message that server sends to
 * client for communicate the end of the match
 */
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
