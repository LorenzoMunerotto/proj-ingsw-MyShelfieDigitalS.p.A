package it.polimi.ingsw.model.gameEntity.library;

import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class LibraryTestHelper extends it.polimi.ingsw.model.gameEntity.Library {



    /**
     * Set the library with the given grid of item tile types.
     * It creates a snapshot of the grid of item tile types.
     * Used only for testing purposes.
     *
     * @param gridOfItemTileType the grid of item tile types
     */
    public void setLibrary(ItemTileType[][] gridOfItemTileType){
        for (int row =0; row<getROWS(); row++){
            for (int col = 0; col<getCOLUMNS(); col++){
                this.setItemTile(row, col, new ItemTile(gridOfItemTileType[row][col]));
            }
        }
    }
}
