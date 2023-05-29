package it.polimi.ingsw.server.serverMessage;

public class CustomMessage implements ServerMessage {

    private final String message;

    public CustomMessage(String message) {
        this.message = message;
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
