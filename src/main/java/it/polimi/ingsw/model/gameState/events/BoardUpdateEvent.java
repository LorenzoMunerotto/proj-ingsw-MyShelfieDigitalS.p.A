package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.Coordinate;

import java.util.List;

/**
 * Class defining the event of a board update.
 */
public class BoardUpdateEvent implements ModelEvent {

    /**
     * List of coordinates of the cells that have been updated.
     */
    private final List<Coordinate> coordinates;
    /**
     * Checksum of the board.
     */
    private final long checksum;

    /**
     * Default constructor.
     *
     * @param coordinates the list of coordinates of the cells that have been updated
     * @param checksum   the checksum of the board
     */
    public BoardUpdateEvent(List<Coordinate> coordinates, long checksum) {
        this.coordinates = coordinates;
        this.checksum = checksum;
    }

    /**
     * Get the list of coordinates of the cells that have been updated.
     *
     * @return the list of coordinates of the cells that have been updated
     */
    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    /**
     * Get the checksum of the board.
     *
     * @return the checksum of the board
     */
    public long getChecksum() {
        return this.checksum;
    }


    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}