package it.polimi.ingsw.model.gameState.exceptions;

public class EmptyBagException extends Exception{

    @Override
    public String getMessage() {
        return "The bag is empty, the refill of the board is over";
    }
}