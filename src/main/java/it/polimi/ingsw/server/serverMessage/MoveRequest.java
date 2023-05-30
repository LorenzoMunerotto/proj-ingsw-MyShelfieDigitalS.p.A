package it.polimi.ingsw.server.serverMessage;

/**
 * This class represents the message that server sends to
 * client for ask a move
 */
public class MoveRequest implements ServerMessage {

    private final String message;

    public MoveRequest() {
        this.message = "Insert move input";
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
