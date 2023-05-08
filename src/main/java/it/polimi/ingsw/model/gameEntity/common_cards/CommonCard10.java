package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing the common goal card 10.
 */
public class CommonCard10 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard10() {
        super(10, """
                Two lines, each with 5 different tile types. Lines may have different combinations.""");
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

        firstLoop:
        for (int row = 0; row < library.getLibraryGrid().length; row++) {
            Set<ItemTileType> distinctTypes = new HashSet<>();
            for (int col = 0; col < library.getLibraryGrid()[0].length; col++) {
                ItemTileType currentType = library.getItemTile(row, col);
                if (currentType == ItemTileType.EMPTY) {
                    continue firstLoop;
                }
                distinctTypes.add(currentType);
            }
            if (distinctTypes.size() == library.getLibraryGrid()[0].length) {
                counter++;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }
}
