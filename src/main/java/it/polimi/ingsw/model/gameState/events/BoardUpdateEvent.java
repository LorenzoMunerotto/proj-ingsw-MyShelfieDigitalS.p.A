package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.Coordinate;

import java.util.List;

public class BoardUpdateEvent implements ModelEvent {

    private final List<Coordinate> coordinates;
    private final long checksum;

    public BoardUpdateEvent(List<Coordinate> coordinates, long checksum) {
        this.coordinates = coordinates;
        this.checksum = checksum;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public long getChecksum() {
        return this.checksum;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
