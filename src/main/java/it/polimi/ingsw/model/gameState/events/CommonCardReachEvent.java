package it.polimi.ingsw.model.gameState.events;

public class CommonCardReachEvent extends  ModelEvent{

    private final String username;
    private final Integer points;
    private final Integer commonCardIndex;

    public CommonCardReachEvent(String username, Integer points, Integer commonCardIndex) {
        this.username = username;
        this.points = points;
        this.commonCardIndex=commonCardIndex;
    }

    public String getUsername() {
        return username;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getCommonCardIndex() {
        return commonCardIndex;
    }
}
