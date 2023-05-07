package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing the common goal card 4.
 */
public class CommonCard4 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard4() {
        super(4, """
                Two groups each containing 4 tiles of the same type in a 2x2 square.
                The tiles of one square can be different from those of the other square.""");
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the Library grid
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        Map<ItemTileType, Integer> itemTileTypeCounter = new HashMap<>();
        List<Pair<Integer, Integer>> usedTiles = new ArrayList<>();

        for (int row = library.getLibraryGrid().length - 2; row >= 0; row--) {
            for (int column = 0; column < library.getLibraryGrid()[0].length - 1; column++) {
                ItemTileType currentItemTileType = library.getItemTile(row, column);
                Pair<Integer, Integer> currentItemTile = new Pair<>(row, column);
                if (currentItemTileType == ItemTileType.EMPTY || usedTiles.contains(currentItemTile)) {
                    continue;
                }
                if (library.getItemTile(row + 1, column) == currentItemTileType
                        && library.getItemTile(row, column + 1) == currentItemTileType
                        && library.getItemTile(row + 1, column + 1) == currentItemTileType) {
                    itemTileTypeCounter.put(currentItemTileType, itemTileTypeCounter.getOrDefault(currentItemTileType, 0) + 1);
                    if (itemTileTypeCounter.get(currentItemTileType) >= 2) {
                        return true;
                    }
                    usedTiles.add(currentItemTile);
                    usedTiles.add(new Pair<>(row + 1, column));
                    usedTiles.add(new Pair<>(row, column + 1));
                    usedTiles.add(new Pair<>(row + 1, column + 1));
                    column++;
                }
            }
        }
        return false;
    }
}
