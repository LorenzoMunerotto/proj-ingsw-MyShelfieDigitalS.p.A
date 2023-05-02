package it.polimi.ingsw.model.gameState.exceptions;

import it.polimi.ingsw.server.Error;

public class IllegalMoveException extends Exception {

    private final Error type;

    public IllegalMoveException(Error type) {
        this.type = type;
    }

    public Error getType() {
        return type;
    }
}
