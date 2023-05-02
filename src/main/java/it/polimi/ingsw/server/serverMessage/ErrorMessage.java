package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.server.Error;

public class ErrorMessage implements ServerMessage {

    private final Error type;


    public ErrorMessage(Error type) {
        this.type = type;
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
