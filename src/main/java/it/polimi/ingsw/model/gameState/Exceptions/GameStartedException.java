package it.polimi.ingsw.model.gameState.Exceptions;

public class GameStartedException extends Exception {

    @Override
    public String getMessage() {
        return "The game has already started";
    }
}
