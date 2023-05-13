package it.polimi.ingsw.model.gameState.exceptions;

/**
 * Exception thrown when the number of players is not valid.
 */
public class IllegalNumOfPlayersException extends Exception {

    /**
     * Get the message of the exception.
     *
     * @return the message of the exception
     */
    @Override
    public String getMessage() {
        return "The number of players is not valid";
    }
}
