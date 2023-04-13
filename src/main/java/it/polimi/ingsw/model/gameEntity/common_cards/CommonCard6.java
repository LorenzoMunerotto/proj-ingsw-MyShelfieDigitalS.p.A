package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.HashMap;
import java.util.Map;

/**
 * A specific type of card that has a unique rule.
 */
public class CommonCard6 extends CommonGoalCard {

    /**
     * Creates a new CommonCard6.
     */
    public CommonCard6() {
       super(6, "Eight tiles of the same type. There’s no\n" +
               "restriction about the position of these\n" +
               "tiles.");
    }

    /**
     * Checks if the rules of the card are respected.
     *
     * @param library is the Library
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        Map<ItemTileType, Integer> itemTileTypeCounter = new HashMap<>();

        for (int row=0; row< library.getROWS(); row++) {
            for (int col = 0; col < library.getCOLUMNS(); col++) {
                ItemTileType currentItemTileType = library.getItemTile(row,col).getItemTileType();
                if (currentItemTileType != ItemTileType.EMPTY) {
                    itemTileTypeCounter.put(currentItemTileType, itemTileTypeCounter.getOrDefault(currentItemTileType, 0) + 1);
                    if (itemTileTypeCounter.get(currentItemTileType) >= 8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}