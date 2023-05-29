package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

/**
 * Class defining the event of setting the common goal cards.
 */
public class CommonCardsSetEvent implements ModelEvent {

    /**
     * List of triplets containing the index, the number of points and the description of the common goal cards.
     */
    private final List<Triplet<Integer, Integer, String>> commonGoalCardList;

    /**
     * Default constructor.
     *
     * @param commonGoalCardList it is the list of common goal cards.
     */
    public CommonCardsSetEvent(List<CommonGoalCard> commonGoalCardList) {
        this.commonGoalCardList = new ArrayList<>();
        for (CommonGoalCard commonGoalCard : commonGoalCardList) {
            this.commonGoalCardList.add(new Triplet<>(commonGoalCard.getIndex(), 8, commonGoalCard.getDescription()));
        }
    }

    /**
     * Get the list of triplets containing the index, the number of points and the description of the common goal cards.
     *
     * @return the list of triplets containing the index, the number of points and the description of the common goal cards.
     */
    public List<Triplet<Integer, Integer, String>> getCommonGoalCardList() {
        return this.commonGoalCardList;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}