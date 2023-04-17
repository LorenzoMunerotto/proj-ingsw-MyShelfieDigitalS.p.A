package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.EventHandler;
import it.polimi.ingsw.Event;

public class NumOfPlayerChoice implements Event {

    private Integer numOfPlayer;

    public NumOfPlayerChoice(Integer numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public Integer getNumOfPlayer() {
        return numOfPlayer;
    }

    @Override
    public void accept(EventHandler eventHandler) {
        eventHandler.handle(this);
    }



}
