package it.polimi.ingsw.server.serverMessage;

import java.io.IOException;
import java.io.Serializable;

public class CheckConnectionMessage implements ServerMessage {

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) throws IOException {

    }
}
