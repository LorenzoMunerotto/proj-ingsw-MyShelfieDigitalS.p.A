package it.polimi.ingsw.model.gameState.events;

public class PointsUpdateEvent extends ModelEvent {

    private final Integer points;
    private final String username;

    public PointsUpdateEvent(Integer points, String username) {
        this.points = points;
        this.username = username;
    }
}
