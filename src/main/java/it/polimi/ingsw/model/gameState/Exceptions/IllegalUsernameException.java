package it.polimi.ingsw.model.gameState.Exceptions;

public class IllegalUsernameException extends Exception {

    @Override
    public String getMessage() {
        return "The username is not valid";
    }
}
