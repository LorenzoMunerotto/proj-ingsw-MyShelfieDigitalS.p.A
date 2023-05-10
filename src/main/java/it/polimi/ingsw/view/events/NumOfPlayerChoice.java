package it.polimi.ingsw.view.events;

import it.polimi.ingsw.client.clientMessage.ClientMessage;

public class NumOfPlayerChoice implements ClientMessage, ViewEvent {

    private final Integer numOfPlayer;

    public NumOfPlayerChoice(Integer numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public Integer getNumOfPlayer() {
        return numOfPlayer;
    }


    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ViewChangeEventHandler viewChangeEventHandler) {
        viewChangeEventHandler.handle(this);
    }
}
