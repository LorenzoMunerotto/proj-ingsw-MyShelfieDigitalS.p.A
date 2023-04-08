package it.polimi.ingsw.model.gameState.Exceptions;

public class IllegalUsernameException extends Exception {

    @Override
    public String getMessage() {
        return "l'username inserito non è accettabile";
    }
}
