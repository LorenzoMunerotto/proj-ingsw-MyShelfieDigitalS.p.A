package it.polimi.ingsw.model.gameState.Exceptions;

public class InvalidNumOfPlayers extends Exception {

    @Override
    public String getMessage() {
        return "The number of players is not valid";
    }
}
