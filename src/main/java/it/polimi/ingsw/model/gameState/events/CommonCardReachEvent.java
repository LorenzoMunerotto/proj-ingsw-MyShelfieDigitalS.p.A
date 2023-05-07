package it.polimi.ingsw.model.gameState.events;

public class CommonCardReachEvent implements ModelEvent {

    private final String username;
    private final int points;
    private final Integer commonCardIndex;

    public CommonCardReachEvent(String username, int points, int commonCardIndex) {
        this.username = username;
        this.points = points;
        this.commonCardIndex = commonCardIndex;
    }

    public String getUsername() {
        return username;
    }

    public int getPoint() {
        return points;
    }

    public int getCommonCardIndex() {
        return commonCardIndex;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
