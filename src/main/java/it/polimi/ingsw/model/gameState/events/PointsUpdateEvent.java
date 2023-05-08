package it.polimi.ingsw.model.gameState.events;

public class PointsUpdateEvent implements ModelEvent {

    private final int points;
    private final String username;

    public PointsUpdateEvent(int points, String username) {
        this.points = points;
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public String getUsername() {
        return username;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
