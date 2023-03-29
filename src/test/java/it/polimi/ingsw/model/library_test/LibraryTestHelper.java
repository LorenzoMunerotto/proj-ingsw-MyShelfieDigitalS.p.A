package it.polimi.ingsw.model.library_test;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;

public class LibraryTestHelper extends it.polimi.ingsw.model.data.Library {

    /**
     * Set the item tile in the given row and column.
     * Used only for testing purposes.
     *
     * @param row the row of the grid
     * @param column the column of the grid
     * @param itemTile the new item tile
     */
    public void setItemTile(int row, int column, ItemTile itemTile) {
        if (row < 0 || row >= getROWS() || column < 0 || column >= getCOLUMNS()) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        this.getGrid()[row][column] = itemTile;
    }

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
