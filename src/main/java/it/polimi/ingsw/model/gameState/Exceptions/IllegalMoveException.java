package it.polimi.ingsw.model.gameState.Exceptions;

import it.polimi.ingsw.server.serverMessage.Error;

public class IllegalMoveException extends Exception {

    private final Error type;

    public IllegalMoveException(Error type) {
        this.type = type;
    }

    public Error getType() {
        return type;
    }
}
