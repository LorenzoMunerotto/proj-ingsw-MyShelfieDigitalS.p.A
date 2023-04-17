package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.EventHandler;
import it.polimi.ingsw.Event;
import it.polimi.ingsw.model.gameEntity.Coordinate;

import java.util.List;


/**
 * This class represents the player's choices/play
 * It's an event for the Observer-Observable pattern
 */
public class Move implements Event {


    /**
     * The list of Board's coordinate selected by the player
     */
    private final List<Coordinate> coordinateList;

    /**
     * The column chose by the player
     */
    private final int column;


    public Move(List<Coordinate> coordinateList, int column) {
        this.coordinateList = coordinateList;
        this.column = column;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public int getColumn() {
        return column;
    }

    /**
     * @see Event
     * @param eventHandler
     */
    @Override
    public void accept(EventHandler eventHandler) {
        eventHandler.handle(this);
    }
}
