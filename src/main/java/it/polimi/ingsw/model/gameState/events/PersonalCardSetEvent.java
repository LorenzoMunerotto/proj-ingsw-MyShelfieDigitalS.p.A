package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.personal_cards.Goal;
import it.polimi.ingsw.model.gameEntity.personal_cards.PersonalGoalCard;

public class PersonalCardSetEvent implements ModelEvent {

    private final ItemTileType[][] libraryGrid;
    private final int index;

    public PersonalCardSetEvent(PersonalGoalCard personalGoalCard) {
        libraryGrid = new ItemTileType[6][5];
        for (int row=0; row<6; row++){
            for(int col=0; col<5; col++){
                libraryGrid[row][col] = ItemTileType.EMPTY;
            }
        }
        for(Goal goal : personalGoalCard.getGoals()){
            libraryGrid[goal.getRow()][goal.getColumn()]=goal.getItemTileType();
        }
        this.index = personalGoalCard.getNumber();
    }

    public ItemTileType[][] getPersonalGoalCard() {
        return libraryGrid;
    }

    public int getIndex() {
        return index;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
