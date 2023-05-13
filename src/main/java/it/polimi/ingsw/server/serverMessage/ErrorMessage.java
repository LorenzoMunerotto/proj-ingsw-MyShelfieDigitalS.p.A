package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.server.GameCreationErrors;

public class ErrorMessage implements ServerMessage {

    private final GameCreationErrors type;

    public ErrorMessage(GameCreationErrors type) {
        this.type = type;
    }

    public GameCreationErrors getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return String.valueOf(type);
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
