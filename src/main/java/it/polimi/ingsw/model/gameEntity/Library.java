package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.stream.IntStream;

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
    private final ItemTileType[][] grid;

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
        this.grid = new ItemTileType[ROWS][COLUMNS];
        IntStream.range(0, ROWS).forEach(row -> IntStream.range(0, COLUMNS).forEach(column -> grid[row][column] = ItemTileType.EMPTY));
    }

    /**
     * Get the grid of the library.
     *
     * @return the gird of the library
     */
    public ItemTileType[][] getLibraryGrid() {
        return this.grid;
    }

    /**
     * Set the item tile in the given row and column.
     *
     * @param row      the row of the grid
     * @param column   the column of the grid
     * @param itemTile the new item tile
     */
    public void setItemTile(int row, int column, ItemTileType itemTile) {
        this.grid[row][column] = itemTile;
    }

    /**
     * Get the item tile in the given row and column.
     *
     * @param row    the row of the grid
     * @param column the column of the grid
     * @return the item tile
     */
    public ItemTileType getItemTile(int row, int column) {
        return this.grid[row][column];
    }
}