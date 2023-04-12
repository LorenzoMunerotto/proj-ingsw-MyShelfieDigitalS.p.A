package it.polimi.ingsw.model.gameState.Exceptions;

/**
 * Exception thrown when the username is already taken.
 */
public class UsernameAlreadyExistsException extends Exception {

    /**
     * Constructor of the class.
     */
    public UsernameAlreadyExistsException(){
        super("Username Already Exists");
    }

    @Override
    public String getMessage() {
        return "Username Already Exists";
    }
}
