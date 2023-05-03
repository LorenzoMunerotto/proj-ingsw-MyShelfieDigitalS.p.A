package it.polimi.ingsw.model.gameState.events;

public class CommonCardReachEvent implements ModelEvent{

    private final String username;
    private final Integer pointsTaken;
    private final Integer pointsAvailable;
    private final Integer commonCardIndex;

    public CommonCardReachEvent(String username, Integer pointsTaken, Integer pointsAvailable, Integer commonCardIndex) {
        this.username = username;
        this.pointsTaken = pointsTaken;
        this.pointsAvailable = pointsAvailable;
        this.commonCardIndex = commonCardIndex;
    }

    public String getUsername() {
        return username;
    }

    public Integer getPointsTaken() {
        return pointsTaken;
    }

    public Integer getPointsAvailable() {
        return pointsAvailable;
    }

    public Integer getCommonCardIndex() {
        return commonCardIndex;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
