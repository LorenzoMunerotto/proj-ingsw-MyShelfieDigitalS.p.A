package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing the common goal card 12.
 */
public class CommonCard12 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard12() {
        super(12, """
                Five columns with increasing/decreasing height. Next column must be exactly one tile higher/lower.""");
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the Library
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        int counter = 0;

        for (int row = 1; row < library.getLibraryGrid().length; row++) {
            if (library.getItemTile(row - 1, row - 1) != ItemTileType.EMPTY || library.getItemTile(row, row - 1) == ItemTileType.EMPTY) {
                break;
            }
            counter++;
            if (counter == 5) {
                return true;
            }
        }
        counter = 0;
        for (int row = 1; row < library.getLibraryGrid().length; row++) {
            if (library.getItemTile(row - 1, 5 - row) != ItemTileType.EMPTY || library.getItemTile(row, 5 - row) == ItemTileType.EMPTY) {
                break;
            }
            counter++;
            if (counter == 5) {
                return true;
            }
        }
        return false;
    }
}
