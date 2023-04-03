package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class representing the common goal card 9.
 */
public class CommonCard9 extends CommonGoalCard {


    public CommonCard9() {
       super(9);
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
        for (int col=0; col< library.getCOLUMNS(); col++) {
            Set<ItemTileType> distinctTypes = new HashSet<>();

            for (int row=0; row< library.getROWS(); row++) {
                ItemTileType currentType = library.getItemTile(row,col).getItemTileType();
                if (currentType == ItemTileType.EMPTY) {
                    continue firstLoop;
                }
                distinctTypes.add(currentType);
            }
            if (distinctTypes.size() == library.getROWS()) {
                counter++;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }
}