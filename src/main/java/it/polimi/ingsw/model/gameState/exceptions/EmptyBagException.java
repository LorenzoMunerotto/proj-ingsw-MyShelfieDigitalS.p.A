package it.polimi.ingsw.model.gameState.exceptions;

/**
 * Exception thrown when the bag is empty.
 */
public class EmptyBagException extends Exception{

    /**
     * Get the message of the exception.
     *
     * @return the message of the exception
     */
    @Override
    public String getMessage() {
        return "The bag is empty, the refill of the board is over";
    }
}