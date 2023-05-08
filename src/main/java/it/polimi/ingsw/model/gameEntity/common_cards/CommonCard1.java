package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameMechanics.LibraryManager;
import org.javatuples.Pair;

import java.util.List;

/**
 * Class representing the common goal card 1.
 */
public class CommonCard1 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard1() {
        super(1, """
                Six groups, each with at least 2 same-type tiles. Groups may have different tiles.""");
    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the Library
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {

        LibraryManager libraryManager = new LibraryManager();
        libraryManager.setLibrary(library);
        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = libraryManager.getListGroupsAdjacentTiles();
        int counter = 0;
        for (Pair<ItemTileType, Integer> group : listGroupsAdjacentTiles) {
            if (group.getValue0() != ItemTileType.EMPTY && group.getValue1() == 2) {
                counter++;
            }
        }
        return counter >= 6;
    }
}
