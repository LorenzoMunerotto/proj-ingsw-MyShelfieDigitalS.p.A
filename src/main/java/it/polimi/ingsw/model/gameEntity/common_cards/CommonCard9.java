package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing the common goal card 9.
 */
public class CommonCard9 extends CommonGoalCard {

    /**
     * Constructor of the class.
     */
    public CommonCard9() {
        super(9, """
                Two columns each formed by 6 different types of tiles.""");
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
        for (int col = 0; col < library.getLibraryGrid()[0].length; col++) {
            Set<ItemTileType> distinctTypes = new HashSet<>();

            for (int row = 0; row < library.getLibraryGrid().length; row++) {
                ItemTileType currentType = library.getItemTile(row, col);
                if (currentType == ItemTileType.EMPTY) {
                    continue firstLoop;
                }
                distinctTypes.add(currentType);
            }
            if (distinctTypes.size() == library.getLibraryGrid().length) {
                counter++;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }
}
