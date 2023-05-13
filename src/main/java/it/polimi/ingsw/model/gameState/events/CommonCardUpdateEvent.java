package it.polimi.ingsw.model.gameState.events;

/**
 * Class defining the event of a common card update.
 */
public class CommonCardUpdateEvent implements ModelEvent {

    /**
     * Number of points of the common card.
     */
    private final int points;

    /**
     * Default constructor.
     *
     * @param points number of points of the common card
     */
    public CommonCardUpdateEvent(int points) {
        this.points = points;
    }

    /**
     * Get the number of points of the common card.
     *
     * @return number of points of the common card
     */
    public int getPoints() {
        return points;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}