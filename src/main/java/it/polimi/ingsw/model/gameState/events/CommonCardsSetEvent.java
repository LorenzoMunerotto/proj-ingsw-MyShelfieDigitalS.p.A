package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

public class CommonCardsSetEvent implements ModelEvent {

    private final List<Triplet<Integer, Integer, String>> commonGoalCardList;

    public CommonCardsSetEvent(List<CommonGoalCard> commonGoalCardList) {
        this.commonGoalCardList = new ArrayList<>();
        for(CommonGoalCard commonGoalCard : commonGoalCardList) {
            this.commonGoalCardList.add(new Triplet<>(commonGoalCard.getIndex(), 8, commonGoalCard.getDescription()));
        }
    }

    public List<Triplet<Integer, Integer, String>> getCommonGoalCardList() {
        return this.commonGoalCardList;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
