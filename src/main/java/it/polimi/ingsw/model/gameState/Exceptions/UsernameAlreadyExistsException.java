package it.polimi.ingsw.model.gameState.Exceptions;

public class UsernameAlreadyExistsException extends Exception {

    public UsernameAlreadyExistsException(){
        super("Username Already Exists");
    }

    @Override
    public String getMessage() {
        return "c'è già un giocatore con questo nome";
    }
}
