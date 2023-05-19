package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing the common goal card 4.
 */
public class CommonCard4 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard4() {
        super(4, """
                Two groups, each with a 2x2 square of 4 same-type tiles. Squares may have different tiles.""");
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the Library grid
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        int counter = 0;
        boolean[][] usedCells = new boolean[library.getLibraryGrid().length][library.getLibraryGrid()[0].length];

        for (int row = library.getLibraryGrid().length - 1; row > 0; row--) {
            for (int column = 0; column < library.getLibraryGrid()[0].length - 1; column++) {
                ItemTileType currentItemTileType = library.getItemTile(row, column);
                if (currentItemTileType == ItemTileType.EMPTY || usedCells[row][column]) {
                    continue;
                }
                boolean top = currentItemTileType == library.getItemTile(row - 1, column);
                boolean right = currentItemTileType == library.getItemTile(row, column + 1);
                boolean cross = currentItemTileType == library.getItemTile(row - 1, column + 1);
                if (top && right && cross) {
                    if (usedCells[row][column + 1] || usedCells[row - 1][column] || usedCells[row - 1][column + 1]) {
                        continue;
                    }
                    usedCells[row][column] = true;
                    usedCells[row][column + 1] = true;
                    usedCells[row - 1][column] = true;
                    usedCells[row - 1][column + 1] = true;

                    counter++;
                }
                if (counter >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
}