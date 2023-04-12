package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Get the grid of the library.
     *
     * @return the gird of the library
     */

    public ItemTile[][] getGrid() {
        return this.grid;
    }

    
    public static int getROWS(){
        return ROWS;
    }

    public static int getCOLUMNS(){
        return COLUMNS;
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


    /**
     * Get the ItemTile in pos[row][column]
     */
    public ItemTile getItemTile(int row, int column){
        if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        return grid[row][column];
    }



    /**
     * Return True if the tile in pos[row][col] has Upper tile
     *
     */

    public static boolean hasUpperItemTile(Integer row, Integer col){
        return row>0 && row < ROWS && col>=0 && col<COLUMNS;
    }

    /**
     * Return True if the tile in pos[row][col] has Lower tile
     *
     */
    public static boolean hasLowerItemTile(Integer row, Integer col){
        return row >= 0 && row < ROWS-1 && col>=0 && col < COLUMNS ;
    }

    /**
     * Return True if the tile in pos[row][col] has Right tile
     */
    public static boolean hasRightItemTile(Integer row, Integer col){
        return row >= 0 && row < ROWS && col>=0 && col < COLUMNS-1 ;
    }

    /**
     * Return True if the tile in pos[row][col] has Left tile

     */
    public static boolean hasLeftItemTile(Integer row, Integer col){
        return row >= 0 && row < ROWS && col>0 && col < COLUMNS ;
    }

    /**
     * Return the Upper tile of the tile in pos[row][col]

     */
    public ItemTile getUpperItemTile(Integer row, Integer col){
        return getItemTile(row-1, col);
    }

    /**
     * Return the Lower tile of the tile in pos[row][col]

     */
    public ItemTile getLowerItemTile(Integer row, Integer col){
        return getItemTile(row+1, col);
    }

    /**
     * Return the Right tile of the tile in pos[row][col]
     */
    public ItemTile getRightItemTile(Integer row, Integer col){
        return getItemTile(row, col+1);
    }

    /**
     * Return the Left tile of the tile in pos[row][col]
     *
     */
    public ItemTile getLeftItemTile(Integer row, Integer col){
        return getItemTile(row, col-1);
    }

    public void insertItemTile(Integer col, ItemTile itemTile){
        if (grid[0][col].getItemTileType()!=ItemTileType.EMPTY) throw new IllegalArgumentException("La colonna è già piena");

        for(int row= ROWS-1; row>=0; row--){
            if (grid[row][col].getItemTileType()==ItemTileType.EMPTY){
                grid[row][col]=itemTile;
                break;
            }
        }
    }


}
