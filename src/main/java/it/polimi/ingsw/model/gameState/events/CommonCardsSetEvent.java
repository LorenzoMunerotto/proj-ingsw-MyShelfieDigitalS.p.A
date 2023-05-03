package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;

import java.util.List;

public class CommonCardsSetEvent implements ModelEvent {

    private final Integer CommonCard1Index;
    private final Integer CommonCard1PointsAvailable;
    private final String CommonCard1Description;
    private final Integer CommonCard2Index;
    private final Integer CommonCard2PointsAvailable;
    private final String CommonCard2Description;

    public CommonCardsSetEvent(List<CommonGoalCard> commonGoalCardList) {
        this.CommonCard1Index=commonGoalCardList.get(0).getIndex();
        this.CommonCard1PointsAvailable=8;
        this.CommonCard1Description=commonGoalCardList.get(0).getDescription();
        this.CommonCard2Index=commonGoalCardList.get(1).getIndex();
        this.CommonCard2PointsAvailable=8;
        this.CommonCard2Description=commonGoalCardList.get(1).getDescription();
    }

    public Integer getCommonCard1Index() {
        return CommonCard1Index;
    }

    public Integer getCommonCard1PointsAvailable() {
        return CommonCard1PointsAvailable;
    }

    public String getCommonCard1Description() {
        return CommonCard1Description;
    }

    public Integer getCommonCard2Index() {
        return CommonCard2Index;
    }

    public Integer getCommonCard2PointsAvailable() {
        return CommonCard2PointsAvailable;
    }

    public String getCommonCard2Description() {
        return CommonCard2Description;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
