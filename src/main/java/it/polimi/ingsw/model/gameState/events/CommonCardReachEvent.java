package it.polimi.ingsw.model.gameState.events;

/**
 * Class defining the event of a player reaching a common card
 */
public class CommonCardReachEvent implements ModelEvent {

    /**
     * The username of the player who reached the common card.
     */
    private final String username;
    /**
     * The points earned by the player.
     */
    private final int pointsEarned;
    /**
     * The points available to the player.
     */
    private final int pointsAvailable;
    /**
     * The index of the common card reached.
     */
    private final Integer commonCardIndex;

    /**
     * Default constructor.
     *
     * @param username        is the username of the player who reached the common card
     * @param pointsEarned    are the points earned by the player
     * @param pointsAvailable are the points available to the player
     * @param commonCardIndex is the index of the common card reached
     */
    public CommonCardReachEvent(String username, int pointsEarned, int pointsAvailable, int commonCardIndex) {
        this.username = username;
        this.pointsAvailable = pointsAvailable;
        this.pointsEarned = pointsEarned;
        this.commonCardIndex = commonCardIndex;
    }

    /**
     * Get the username of the player who reached the common card.
     *
     * @return the username of the player who reached the common card
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the points earned by the player.
     *
     * @return the points earned by the player
     */
    public int getPointsEarned() {
        return pointsEarned;
    }

    /**
     * Get the points available to the player.
     *
     * @return the points available to the player
     */
    public int getPointsAvailable() {
        return pointsAvailable;
    }

    /**
     * Get the index of the common card reached.
     *
     * @return the index of the common card reached
     */
    public int getCommonCardIndex() {
        return commonCardIndex;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}