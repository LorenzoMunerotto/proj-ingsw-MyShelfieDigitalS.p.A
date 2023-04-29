package it.polimi.ingsw.model.gameState.exceptions;

/**
 * Exception thrown when the game has already started.
 */
public class GameStartedException extends Exception {

    @Override
    public String getMessage() {
        return "The game has already started";
    }
}