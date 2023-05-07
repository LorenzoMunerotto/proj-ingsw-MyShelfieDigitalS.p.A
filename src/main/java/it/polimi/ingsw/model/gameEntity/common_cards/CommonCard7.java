package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing the common goal card 7.
 */
public class CommonCard7 extends CommonGoalCard {

    /**
     * Creates a new CommonCard7.
     */
    public CommonCard7() {
        super(7, """
                Five tiles of the same type forming a diagonal.""");
    }


    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the Library
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {

        for (int row = 0; row <= 1; row++) {
            int column = 0;
            ItemTileType currentItemTile = library.getItemTile(row, column);
            if (currentItemTile == ItemTileType.EMPTY) {
                continue;
            }
            if (currentItemTile != library.getItemTile(row + 1, column + 1)) {
                continue;
            }
            if (currentItemTile != library.getItemTile(row + 2, column + 2)) {
                continue;
            }
            if (currentItemTile != library.getItemTile(row + 3, column + 3)) {
                continue;
            }
            if (currentItemTile != library.getItemTile(row + 4, column + 4)) {
                continue;
            }
            return true;
        }
        for (int row = 0; row <= 1; row++) {
            int col = 4;
            ItemTileType currentItemTile = library.getItemTile(row, col);
            if (currentItemTile == ItemTileType.EMPTY) {
                continue;
            }
            if (currentItemTile != library.getItemTile(row + 1, col - 1)) {
                continue;
            }
            if (currentItemTile != library.getItemTile(row + 2, col - 2)) {
                continue;
            }
            if (currentItemTile != library.getItemTile(row + 3, col - 3)) {
                continue;
            }
            if (currentItemTile != library.getItemTile(row + 4, 0)) {
                continue;
            }
            return true;
        }
        return false;
    }
}
