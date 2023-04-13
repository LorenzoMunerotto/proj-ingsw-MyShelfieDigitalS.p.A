package it.polimi.ingsw.model.gameState.Exceptions;

/**
 * Exception thrown when the number of players is not valid.
 */
public class InvalidNumOfPlayers extends Exception {

    @Override
    public String getMessage() {
        return "The number of players is not valid";
    }
}