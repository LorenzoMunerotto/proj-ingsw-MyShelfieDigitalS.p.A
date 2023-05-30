package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.server.GameCreationErrors;

/**
 * This class represents the message that server sends to
 * client for communicate an error during the match setup
 */
public class ErrorMessage implements ServerMessage {

    private final GameCreationErrors type;

    public ErrorMessage(GameCreationErrors type) {
        this.type = type;
    }

    public GameCreationErrors getType() {
        return type;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return String.valueOf(type);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
