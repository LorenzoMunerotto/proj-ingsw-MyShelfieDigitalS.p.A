package it.polimi.ingsw.model.gameState.Exceptions;

/**
 * Exception thrown when the username is not valid.
 */
public class IllegalUsernameException extends Exception {

    @Override
    public String getMessage() {
        return "The username is not valid";
    }
}
