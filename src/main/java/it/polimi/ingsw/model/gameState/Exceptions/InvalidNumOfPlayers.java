package it.polimi.ingsw.model.gameState.Exceptions;

public class InvalidNumOfPlayers extends Exception {

    @Override
    public String getMessage() {
        return "Sono ammissibili da 2 a 4 giocatori";
    }
}
