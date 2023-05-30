package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameState.events.CommonCardsSetEvent;
import org.javatuples.Triplet;

import java.util.List;

/**
 * This class represents the message that server sends to
 * client for set the commonGoalCard in virtual model
 */
public class CommonCardsSetMessage implements ServerMessage{

    private final List<Triplet<Integer, Integer, String>> commonGoalCardList;

    public CommonCardsSetMessage(CommonCardsSetEvent commonCardsSetEvent) {
        this.commonGoalCardList = commonCardsSetEvent.getCommonGoalCardList();
    }

    public List<Triplet<Integer, Integer, String>> getCommonGoalCardList() {
        return this.commonGoalCardList;
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
