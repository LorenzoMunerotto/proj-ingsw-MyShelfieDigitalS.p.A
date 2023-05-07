package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing the common goal card 11.
 */
public class CommonCard11 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard11() {
        super(11, """
                Five tiles of the same type forming an X.""");
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the Library
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {

        for (int row = 0; row < library.getLibraryGrid().length - 2; row++) {
            for (int col = 0; col < library.getLibraryGrid()[0].length - 2; col++) {
                ItemTileType currentType = library.getItemTile(row, col);
                if (currentType == ItemTileType.EMPTY) {
                    continue;
                }
                if (currentType != library.getItemTile(row, col + 2)) {
                    continue;
                }
                if (currentType != library.getItemTile(row + 1, col + 1)) {
                    continue;
                }
                if (currentType != library.getItemTile(row + 2, col)) {
                    continue;
                }
                if (currentType != library.getItemTile(row + 2, col + 2)) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
}
