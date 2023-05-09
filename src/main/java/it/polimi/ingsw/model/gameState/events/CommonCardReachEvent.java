package it.polimi.ingsw.model.gameState.events;

public class CommonCardReachEvent implements ModelEvent {

    private final String username;
    private final int pointsEarned;
    private final int pointsAvailable;
    private final Integer commonCardIndex;

    public CommonCardReachEvent(String username, int pointsEarned, int pointsAvailable, int commonCardIndex) {
        this.username = username;
        this.pointsAvailable = pointsAvailable;
        this.pointsEarned = pointsEarned;
        this.commonCardIndex = commonCardIndex;
    }

    public String getUsername() {
        return username;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public int getPointsAvailable() {
        return pointsAvailable;
    }

    public int getCommonCardIndex() {
        return commonCardIndex;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
