package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameState.events.CommonCardReachEvent;

/**
 * This class represent the message which is sent by the server
 * when someone has achieved the goal of Common Card
 */
public class CommonCardReachMessage implements ServerMessage {

    private final String message;
    private final int commonCardIndex;
    private final int points;

    public CommonCardReachMessage(CommonCardReachEvent commonCardReachEvent) {
        this.message = commonCardReachEvent.getUsername() +
                " has achieved the goal of Common Card number " +
                commonCardReachEvent.getCommonCardIndex() +
                ", earns " + commonCardReachEvent.getPointsEarned() +
                " points! ";

        this.commonCardIndex = commonCardReachEvent.getCommonCardIndex();
        this.points = commonCardReachEvent.getPointsAvailable();
    }

    public int getCommonCardIndex() {
        return commonCardIndex;
    }

    public int getPoint() {
        return points;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
