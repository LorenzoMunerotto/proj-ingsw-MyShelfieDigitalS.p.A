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
     * Return True if the tile in pos[row][col] has Upper tile.
     *
     * @param row is the row of the grid
     * @param col is the column of the grid
     * @return true if the tile has an upper tile
     */
    public static boolean hasUpperItemTile(Integer row, Integer col) {
        return row > 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    /**
     * Return True if the tile in pos[row][col] has Lower tile.
     *
     * @param row is the row of the grid
     * @param col is the column of the grid
     * @return true if the tile has a lower tile
     */
    public static boolean hasLowerItemTile(Integer row, Integer col) {
        return row >= 0 && row < ROWS - 1 && col >= 0 && col < COLUMNS;
    }

    /**
     * Return True if the tile in pos[row][col] has Right tile.
     *
     * @param row is the row of the grid
     * @param col is the column of the grid
     * @return true if the tile has a right tile
     */
    public static boolean hasRightItemTile(Integer row, Integer col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS - 1;
    }

    /**
     * Return True if the tile in pos[row][col] has Left tile.
     *
     * @param row is the row of the grid
     * @param col is the column of the grid
     * @return true if the tile has a left tile
     */
    public static boolean hasLeftItemTile(Integer row, Integer col) {
        return row >= 0 && row < ROWS && col > 0 && col < COLUMNS;
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
     * Get the Upper tile of the tile in pos[row][col].
     *
     * @param row is the row of the grid
     * @param col is the column of the grid
     * @return the upper tile
     */
    public ItemTile getUpperItemTile(Integer row, Integer col) {
        return getItemTile(row - 1, col);
    }

    /**
     * Get the Lower tile of the tile in pos[row][col].
     *
     * @param row is the row of the grid
     * @param col is the column of the grid
     * @return the lower tile
     */
    public ItemTile getLowerItemTile(Integer row, Integer col) {
        return getItemTile(row + 1, col);
    }

    /**
     * Get the Right tile of the tile in pos[row][col].
     *
     * @param row is the row of the grid
     * @param col is the column of the grid
     * @return the right tile
     */
    public ItemTile getRightItemTile(Integer row, Integer col) {
        return getItemTile(row, col + 1);
    }

    /**
     * Get the Left tile of the tile in pos[row][col].
     *
     * @param row is the row of the grid
     * @param col is the column of the grid
     * @return the left tile
     */
    public ItemTile getLeftItemTile(Integer row, Integer col) {
        return getItemTile(row, col - 1);
    }

    /**
     * Insert an item tile in the given column.
     *
     * @param col      is the column of the grid
     * @param itemTile is the item tile to insert
     */
    public void insertItemTile(Integer col, ItemTile itemTile) {
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
