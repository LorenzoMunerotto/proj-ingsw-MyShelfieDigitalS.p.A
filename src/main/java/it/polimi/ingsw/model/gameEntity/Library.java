package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing the Library owned by each player.
 */
public class Library {

    /**
     * Number of rows of the library.
     */
    private static final int ROWS = 6;
    /**
     * Number of columns of the library.
     */
    private static final int COLUMNS = 5;
    /**
     * The data structure for the library.
     */
    private final ItemTile[][] grid;

    /**
     * Index of the cell of the library [row][column].
     * [0,0] [0,1] [0,2] [0,3] [0,4]
     * [1,0] [1,1] [1,2] [1,3] [1,4]
     * [2,0] [2,1] [2,2] [2,3] [2,4]
     * [3,0] [3,1] [3,2] [3,3] [3,4]
     * [4,0] [4,1] [4,2] [4,3] [4,4]
     * [5,0] [5,1] [5,2] [5,3] [5,4]
     */

    /**
     * Constructor for library, initializing the library grid with empty item tiles.
     */
    public Library() {
        this.grid = new ItemTile[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = new ItemTile(ItemTileType.EMPTY);
            }
        }
    }

    /**
     * Get the number of rows of the library.
     *
     * @return the number of rows of the library
     */
    public int getROWS() {
        return ROWS;
    }

    /**
     * Get the number of columns of the library.
     *
     * @return the number of columns of the library
     */
    public int getCOLUMNS() {
        return COLUMNS;
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
     * @param row      the row of the grid
     * @param column   the column of the grid
     * @param itemTile the new item tile
     */
    public void setItemTile(int row, int column, ItemTile itemTile) {
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[row].length) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        this.grid[row][column] = itemTile;
    }

    /**
     * Get the item tile in the given row and column.
     *
     * @param row    the row of the grid
     * @param column the column of the grid
     * @return the item tile
     */
    public ItemTile getItemTile(int row, int column) {
        if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        return grid[row][column];
    }

    /**
     * Insert an item tile in the given column.
     *
     * @param col      is the column of the grid
     * @param itemTile is the item tile to insert
     */
    public void insertItemTile(Integer col, ItemTile itemTile) {
        if (col < 0 || col >= COLUMNS) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        if (grid[0][col].getItemTileType() != ItemTileType.EMPTY)
            throw new IllegalArgumentException("The column is full");

        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][col].getItemTileType() == ItemTileType.EMPTY) {
                grid[row][col] = itemTile;
                break;
            }
        }
    }
}
