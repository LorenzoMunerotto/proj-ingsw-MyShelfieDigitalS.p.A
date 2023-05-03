package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameState.events.CommonCardReachEvent;

public class CommonCardReachMessage  implements ServerMessage {

    private final String message;
    private final Integer commonCardIndex;
    private final Integer pointsAvailable;

    public CommonCardReachMessage(CommonCardReachEvent commonCardReachEvent) {
        this.message = commonCardReachEvent.getUsername() +
                " has achieved the goal of Common Card numb. " +
                commonCardReachEvent.getCommonCardIndex() +
                ", earns " + commonCardReachEvent.getPointsTaken() +
                " points! ";

        this.commonCardIndex=commonCardReachEvent.getCommonCardIndex();
        this.pointsAvailable=commonCardReachEvent.getPointsAvailable();
    }

    public Integer getCommonCardIndex() {
        return commonCardIndex;
    }

    public Integer getPointsAvailable() {
        return pointsAvailable;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
