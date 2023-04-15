package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameMechanics.LibraryManager;
import org.javatuples.Pair;

import java.util.List;
import java.util.Map;

/**
 * Class representing the common goal card 3.
 */
public class CommonCard3 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard3() {
        super(3, "Two groups each containing 4 tiles of\n" +
                "the same type in a 2x2 square. The tiles\n" +
                "of one square can be different from\n" +
                "those of the other square.");
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the Library
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {
        LibraryManager libraryManager = new LibraryManager(library);
        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = libraryManager.getListGroupsAdjacentTiles();
        int counter = 0;

        for (Pair<ItemTileType, Integer> group : listGroupsAdjacentTiles) {
            if (group.getValue0() != ItemTileType.EMPTY && group.getValue1() == 4) {
                counter++;
            }
        }

        return counter >= 4;
    }
}
