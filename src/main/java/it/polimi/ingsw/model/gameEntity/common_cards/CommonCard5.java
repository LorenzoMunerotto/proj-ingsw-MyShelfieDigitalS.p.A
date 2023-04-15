package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing the common goal card 5.
 */
public class CommonCard5 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard5(){
        super(5, "Three columns each formed by 6 tiles Five tiles of the same type forming an X.\n" +
                "of maximum three different types. One\n" +
                "column can show the same or a different\n" +
                "combination of another column.");
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

        for (int col = 0; col < library.getCOLUMNS(); col++) {
            Set<ItemTileType> uniqueItemTileTypes = new HashSet<>();
            int validItemTiles = 0;
            for (int row = 0; row < library.getROWS(); row++) {
                ItemTileType currentItemTileType = library.getItemTile(row,col).getItemTileType();
                if (currentItemTileType != ItemTileType.EMPTY) {
                    uniqueItemTileTypes.add(currentItemTileType);
                    validItemTiles++;
                }
            }
            if (validItemTiles == 6 && uniqueItemTileTypes.size() >= 1 && uniqueItemTileTypes.size() <= 3) {
                counter++;
                if (counter == 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
