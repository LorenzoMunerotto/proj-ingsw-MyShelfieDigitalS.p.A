package it.polimi.ingsw;

import it.polimi.ingsw.model.gameState.events.Move;
import it.polimi.ingsw.model.gameState.events.NumOfPlayerChoice;
import it.polimi.ingsw.model.gameState.events.UsernameChoice;
import it.polimi.ingsw.model.gameState.events.VirtualGameData;

/**
 * This is the interface used for implementing visitor pattern,
 * every object that represent an event in mvc pattern must implement this interface
 * every object that handles events must implement it
 */
public interface EventHandler {

    void handle(Move move);
    void handle(UsernameChoice usernameChoice);
    void handle(NumOfPlayerChoice numOfPlayerChoice);
    void handle(VirtualGameData virtualGameData);

}
