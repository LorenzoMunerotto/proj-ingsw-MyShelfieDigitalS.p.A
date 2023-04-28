package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.Listener;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.server.ModelChangeEventHandler;

import java.util.List;

public class CommonCardEvent extends ModelEvent {

    private final List<CommonGoalCard> commonGoalCardList;

    public CommonCardEvent(List<CommonGoalCard> commonGoalCardList) {
        this.commonGoalCardList = commonGoalCardList;
    }

    public List<CommonGoalCard> getCommonGoalCardList() {
        return commonGoalCardList;
    }

    public void accept(Listener listener) {

        if (listener instanceof ModelChangeEventHandler) {
            ModelChangeEventHandler virualClient = (ModelChangeEventHandler) listener;
            virualClient.handle(this);
        }

    }
}
