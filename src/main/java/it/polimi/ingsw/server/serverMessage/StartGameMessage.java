package it.polimi.ingsw.server.serverMessage;

import java.io.IOException;

public class StartGameMessage implements ServerMessage {

    private final String message;

    public StartGameMessage(){
        this.message = "The match has started!";
    }

    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) throws IOException {
        serverMessageHandler.handle(this);
    }
}
