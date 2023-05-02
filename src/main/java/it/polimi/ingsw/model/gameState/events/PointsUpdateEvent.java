package it.polimi.ingsw.model.gameState.events;

public class PointsUpdateEvent implements ModelEvent {

    private final Integer points;
    private final String username;

    public PointsUpdateEvent(Integer points, String username) {
        this.points = points;
        this.username = username;
    }

    public Integer getPoints() {
        return points;
    }

    public String getUsername() {
        return username;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
