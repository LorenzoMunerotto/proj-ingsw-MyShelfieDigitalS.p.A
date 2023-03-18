package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ItemTileType;

/**
 * Class representing the Library owned by each player.
 */
public class Library {

    /**
     * The data structure for the library.
     */
    private final ItemTile[][] grid;

    /**
     * Constructor for library, initializing the library gird with empty item tiles.
     */
    public Library() {
        this.grid = new ItemTile[5][5];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new ItemTile(ItemTileType.EMPTY);
            }
        }
    }

    /**
     * Get the grid of the library.
     *
     * @return the gird of the library
     */
    public ItemTile[][] getGrid() {
        return this.grid;
    }

    /**
     * Set the item tile in the given row and column.
     *
     * @param row the row of the grid
     * @param column the column of the grid
     * @param itemTile the new item tile
     */
    public void setItemTile(int row, int column, ItemTile itemTile) {
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[row].length) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        this.grid[row][column] = itemTile;
    }

    /* Prob not there
    public void insertItemTile(int column, ItemTile itemTile) {
        if(column < 0 || column >= grid.length){
            throw new IllegalArgumentException("Invalid column number");
        }
        for(int row = grid.length - 1; row >= 0; row--){
            if(grid[row][column].getItemTileType() == ItemTileType.EMPTY){
                grid[row][column] = itemTile;
                return;
            }
        }
        throw new IllegalStateException("Column " + column + " is already full");
    }*/

}
