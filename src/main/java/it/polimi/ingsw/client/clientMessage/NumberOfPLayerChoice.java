package it.polimi.ingsw.client.clientMessage;

/**
 * This class represents the player's choice about the number of player.
 */
public class NumberOfPLayerChoice implements ClientMessage {

    /**
     * The number of player chose by the player.
     */
    private final Integer numOfPlayer;

    /**
     * The constructor of the class.
     *
     * @param numOfPlayer the number of player chose by the player
     */
    public NumberOfPLayerChoice(Integer numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    /**
     * Get the number of player chose by the player.
     *
     * @return the number of player chose by the player
     */
    public Integer getNumOfPlayer() {
        return numOfPlayer;
    }

    /**
     * Get the message.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return null;
    }
}