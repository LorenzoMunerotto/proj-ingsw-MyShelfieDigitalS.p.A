package it.polimi.ingsw.server.serverMessage;

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
