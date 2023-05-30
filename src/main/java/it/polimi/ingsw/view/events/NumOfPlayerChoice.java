package it.polimi.ingsw.view.events;

import it.polimi.ingsw.client.clientMessage.ClientMessage;
import it.polimi.ingsw.client.clientMessage.ClientMessageHandler;

/**
 * This class represents the player's choice about the number of players.
 */
public class NumOfPlayerChoice implements ClientMessage, ViewEvent {

    /**
     * The number of players.
     */
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

    @Override
    public void accept(ClientMessageHandler clientMessageHandler) {
        clientMessageHandler.handle(this);
    }
}