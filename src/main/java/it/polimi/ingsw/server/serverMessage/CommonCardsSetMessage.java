package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameState.events.CommonCardsSetEvent;
import org.javatuples.Triplet;

import java.util.List;

public class CommonCardsSetMessage implements ServerMessage{

    private final List<Triplet<Integer, Integer, String>> commonGoalCardList;

    public CommonCardsSetMessage(CommonCardsSetEvent commonCardsSetEvent) {
        this.commonGoalCardList = commonCardsSetEvent.getCommonGoalCardList();
    }

    public List<Triplet<Integer, Integer, String>> getCommonGoalCardList() {
        return this.commonGoalCardList;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
