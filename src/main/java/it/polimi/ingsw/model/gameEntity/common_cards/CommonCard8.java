package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing the common goal card 8.
 */
public class CommonCard8 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard8() {
        super(8, """
                Four lines, each with 5 tiles, max 3 different types. Lines may have different combinations.""");
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the library grid
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        int counter = 0;

        for (int row = 0; row < library.getLibraryGrid().length; row++) {
            Set<ItemTileType> uniqueItemTileTypes = new HashSet<>();
            int validItemTiles = 0;
            for (int column = 0; column < library.getLibraryGrid()[0].length; column++) {
                ItemTileType currentItemTileType = library.getItemTile(row, column);
                if (currentItemTileType != ItemTileType.EMPTY) {
                    uniqueItemTileTypes.add(currentItemTileType);
                    validItemTiles++;
                }
            }
            if (validItemTiles == 5 && uniqueItemTileTypes.size() >= 1 && uniqueItemTileTypes.size() <= 3) {
                counter++;
                if (counter == 4) {
                    return true;
                }
            }
        }
        return false;
    }
}
