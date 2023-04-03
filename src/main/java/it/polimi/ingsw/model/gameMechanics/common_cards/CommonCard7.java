package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.List;

/**
 * Class representing the common goal card 7.
 */
public class CommonCard7 extends CommonGoalCard {


    public CommonCard7() {
        super(7);
    }


    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the Library
     * @return true if the rules are respected, false otherwise
     */
    @Override
    public boolean checkRules(Library library) {

        for (int row = 0; row <=1; row++) {
            int col = 0;
            ItemTile currentItemTile = library.getItemTile(row,col);
            if (currentItemTile.getItemTileType() == ItemTileType.EMPTY) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+1,col+1).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+2,col+2).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+3,col+3).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+4,col+4).getItemTileType()) {
                continue;
            }
            return true;
        }
        for (int row = 0; row <= 1; row++) {
            int col = 4;
            ItemTile currentItemTile = library.getItemTile(row,col);
            if (currentItemTile.getItemTileType() == ItemTileType.EMPTY) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+1,col-1).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+2,col-2).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+3,col-3).getItemTileType()) {
                continue;
            }
            if (currentItemTile.getItemTileType() != library.getItemTile(row+4,col-4).getItemTileType()) {
                continue;
            }
            return true;
        }
        return false;
    }
}