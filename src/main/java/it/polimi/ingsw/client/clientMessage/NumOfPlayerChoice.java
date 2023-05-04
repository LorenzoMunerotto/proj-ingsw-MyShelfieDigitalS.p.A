package it.polimi.ingsw.client.clientMessage;

public class NumOfPlayerChoice implements ClientMessage {

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
}
