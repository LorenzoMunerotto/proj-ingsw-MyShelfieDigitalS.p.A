package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.List;

/**
 * Class representing the common goal card 1.
 */
public class CommonCard2 extends CommonGoalCard{

    public CommonCard2(){
        super(2);

    }

    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the library
     * @return true if the rules are respected, false otherwise
     */

    @Override
    public boolean checkRules(Library library) {
        ItemTileType itemTile1 = library.getItemTile(0,0).getItemTileType();
        ItemTileType itemTile2 = library.getItemTile(0,4).getItemTileType();
        ItemTileType itemTile3 = library.getItemTile(5,0).getItemTileType();
        ItemTileType itemTile4 = library.getItemTile(5,4).getItemTileType();
        return itemTile1 == itemTile2 && itemTile2 == itemTile3 && itemTile3 == itemTile4;
    }
}