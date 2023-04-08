package it.polimi.ingsw.model.gameState.Exceptions;

public class UsernameAlreadyExistsException extends Exception {

    public UsernameAlreadyExistsException(){
        super("Username Already Exists");
    }

    @Override
    public String getMessage() {
        return "Username Already Exists";
    }
}
