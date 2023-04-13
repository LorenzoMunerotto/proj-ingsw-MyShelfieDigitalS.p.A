package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing the common goal card 1.
 */
public class CommonCard2 extends CommonGoalCard{

    /**
     * Constructor of the class.
     */
    public CommonCard2(){
        super(2, "Four groups each containing at least\n" +
                "4 tiles of the same type (not necessarily\n" +
                "in the depicted shape).\n" +
                "The tiles of one group can be different\n" +
                "from those of another group.");
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