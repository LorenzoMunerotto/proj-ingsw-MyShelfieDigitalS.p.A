package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameState.events.CommonCardReachEvent;

public class CommonCardReachMessage implements ServerMessage {

    private final String message;
    private final int commonCardIndex;
    private final int points;

    public CommonCardReachMessage(CommonCardReachEvent commonCardReachEvent) {
        this.message = commonCardReachEvent.getUsername() +
                " has achieved the goal of Common Card number " +
                commonCardReachEvent.getCommonCardIndex() +
                ", earns " + commonCardReachEvent.getPoint() +
                " points! ";

        this.commonCardIndex = commonCardReachEvent.getCommonCardIndex();
        this.points = commonCardReachEvent.getPoint();
    }

    public int getCommonCardIndex() {
        return commonCardIndex;
    }

    public int getPoint() {
        return points;
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
