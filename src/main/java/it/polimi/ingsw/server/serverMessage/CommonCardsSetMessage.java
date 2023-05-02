package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;

import java.util.List;

public class CommonCardsSetMessage implements ServerMessage{

    private final List<CommonGoalCard>  commonGoalCardList;

    public CommonCardsSetMessage(List<CommonGoalCard> commonGoalCardList) {
        this.commonGoalCardList = commonGoalCardList;
    }

    public List<CommonGoalCard> getCommonGoalCardList() {
        return commonGoalCardList;
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
