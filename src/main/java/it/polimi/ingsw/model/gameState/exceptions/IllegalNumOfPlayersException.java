package it.polimi.ingsw.model.gameState.exceptions;

/**
 * Exception thrown when the number of players is not valid.
 */
public class IllegalNumOfPlayersException extends Exception {

    @Override
    public String getMessage() {
        return "The number of players is not valid";
    }
}
