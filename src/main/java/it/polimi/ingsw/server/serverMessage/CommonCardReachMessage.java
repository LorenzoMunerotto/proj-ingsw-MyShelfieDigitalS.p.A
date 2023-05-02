package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameState.events.CommonCardReachEvent;

public class CommonCardReachMessage  implements ServerMessage {

    private final String message;
    private final CommonCardReachEvent  commonCardReachEvent;

    public CommonCardReachMessage(CommonCardReachEvent commonCardReachEvent) {
        this.message = commonCardReachEvent.getUsername() +
                " has achieved the goal of Common Card numb. " +
                commonCardReachEvent.getCommonCardIndex() +
                ", earns " + commonCardReachEvent.getPoints() +
                " points! ";

        this.commonCardReachEvent = commonCardReachEvent;
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
