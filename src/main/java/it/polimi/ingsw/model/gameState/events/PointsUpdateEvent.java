package it.polimi.ingsw.model.gameState.events;

/**
 * Class defining the event of a player's points update.
 */
public class PointsUpdateEvent implements ModelEvent {

    /**
     * The points of the player.
     */
    private final int points;
    /**
     * The username of the player.
     */
    private final String username;

    /**
     * Default constructor.
     *
     * @param points   the points of the player
     * @param username the username of the player
     */
    public PointsUpdateEvent(int points, String username) {
        this.points = points;
        this.username = username;
    }

    /**
     * Get the points of the player.
     *
     * @return the points of the player
     */
    public int getPoints() {
        return points;
    }

    /**
     * Get the username of the player.
     *
     * @return the username of the player
     */
    public String getUsername() {
        return username;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}