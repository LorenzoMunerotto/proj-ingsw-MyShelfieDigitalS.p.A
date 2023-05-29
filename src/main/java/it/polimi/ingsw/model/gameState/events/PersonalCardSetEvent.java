package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.personal_cards.Goal;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;

/**
 * Class defining the event of the personal goal card set.
 */
public class PersonalCardSetEvent implements ModelEvent {

    /**
     * The grid of the personal goal card.
     */
    private final ItemTileType[][] personalCardGrid;
    /**
     * The index of the personal goal card.
     */
    private final int index;

    /**
     * Default constructor.
     *
     * @param personalGoalCard the personal goal card.
     */
    public PersonalCardSetEvent(PersonalGoalCard personalGoalCard) {
        personalCardGrid = new ItemTileType[6][5];
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                personalCardGrid[row][col] = ItemTileType.EMPTY;
            }
        }
        for (Goal goal : personalGoalCard.getGoals()) {
            personalCardGrid[goal.getRow()][goal.getColumn()] = goal.getItemTileType();
        }
        this.index = personalGoalCard.getNumber();
    }

    /**
     * Get the grid of the personal goal card.
     *
     * @return the grid of the personal goal card.
     */
    public ItemTileType[][] getPersonalGoalCard() {
        return personalCardGrid;
    }

    /**
     * Get the index of the personal goal card.
     *
     * @return the index of the personal goal card.
     */
    public int getIndex() {
        return index;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}