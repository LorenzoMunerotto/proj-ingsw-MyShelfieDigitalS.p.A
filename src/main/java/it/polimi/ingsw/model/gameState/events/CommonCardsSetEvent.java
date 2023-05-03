package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;

import java.util.List;

public class CommonCardsSetEvent implements ModelEvent {

    private final List<CommonGoalCard> commonGoalCardList;

    public CommonCardsSetEvent(List<CommonGoalCard> commonGoalCardList) {
        this.commonGoalCardList = commonGoalCardList;
    }

    public List<CommonGoalCard> getCommonGoalCardList() {
        return commonGoalCardList;
    }



    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
