package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.Listener;
import it.polimi.ingsw.server.ModelChangeEventHandler;

public class MoveRequest implements ServerMessage{

    private final String message;
    public MoveRequest() {
        this.message="Insert move input";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
