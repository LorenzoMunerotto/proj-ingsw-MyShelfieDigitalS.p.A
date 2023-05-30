package it.polimi.ingsw.server.serverMessage;

import java.util.List;

/**
 * This class represents the message that server sends to
 * client for set the player order in virtual model
 */
public class PlayerOrderSetMessage implements ServerMessage{

    final List<String> playerOrder;

    public PlayerOrderSetMessage(List<String> playerOrder) {
        this.playerOrder = playerOrder;
    }

    public List<String> getPlayerOrder() {
        return playerOrder;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return null;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
