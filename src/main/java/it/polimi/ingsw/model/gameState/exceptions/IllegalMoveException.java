package it.polimi.ingsw.model.gameState.exceptions;

import it.polimi.ingsw.server.GameCreationErrors;

public class IllegalMoveException extends Exception {

    private final GameCreationErrors type;

    public IllegalMoveException(GameCreationErrors type) {
        this.type = type;
    }

    public GameCreationErrors getType() {
        return type;
    }
}
