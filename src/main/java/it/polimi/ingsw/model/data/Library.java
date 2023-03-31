package it.polimi.ingsw.model.data;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.javatuples.Pair;

import java.util.ArrayList;
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
     * Constructor for library, initializing the library gird with empty item tiles.
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
     *
     */
    public ItemTile getItemTile(int row, int column){
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[row].length) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        ItemTile itemTile = grid[row][column];
        return itemTile;
    }

    public int getRows(){
        return ROWS;
    }

    public int getColumns(){
        return COLUMNS;
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
        ItemTile itemTile = getItemTile(row-1, col);
        return itemTile;
    }

    /**
     * Return the Lower tile of the tile in pos[row][col]

     */
    public ItemTile getLowerItemTile(Integer row, Integer col){
        ItemTile itemTile = getItemTile(row+1, col);
        return itemTile;
    }

    /**
     * Return the Right tile of the tile in pos[row][col]

     */
    public ItemTile getRightItemTile(Integer row, Integer col){
        ItemTile itemTile = getItemTile(row, col+1);
        return itemTile;
    }

    /**
     * Return the Left tile of the tile in pos[row][col]
     *
     */
    public ItemTile getLeftItemTile(Integer row, Integer col){
        ItemTile itemTile = getItemTile(row, col-1);
        return itemTile;
    }

    /**
     * <p>This method find groups of Adjacent item tiles of the same type on the library <br>
     * This method can be used for calculate points from the groups of Adjacent tiles and for check CommonCards3 and CC1 <br>
     * <i>Da valutare in che classe mettere questo metodo</i>
     * </p>
     *
     * @return List of pair (ItemTileType, numberOfTile)
     *
     */
    public List<Pair<ItemTileType, Integer>> getListGroupsAdjacentTiles(){
        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = new ArrayList<>();

        Character[][] helpgrid = new Character[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                helpgrid[i][j] = Character.valueOf('W');
            }
        }
        List<Pair<Integer, Integer>> sameTileList = new ArrayList<>();
        List<Pair<Integer, Integer>> differentTileTypeTail = new ArrayList<>();
        List<Pair<Integer, Integer>> sameTileTypeTail = new ArrayList<>();
        differentTileTypeTail.add(new Pair<>(0,0));

        while(differentTileTypeTail.size()!=0) {

            Integer row1;
            Integer col1;
            Pair start1;
            do {
                start1 = differentTileTypeTail.remove(0);
                row1 = (Integer) start1.getValue0();
                col1 = (Integer) start1.getValue1();
            }while(helpgrid[row1][col1]=='B' && differentTileTypeTail.size()>0);

            ItemTileType currentItemTileType = this.getItemTile(row1, col1).getItemTileType();
            Integer counter = 1;
            Boolean ok = true;
            if (differentTileTypeTail.size()==0 && helpgrid[row1][col1]=='B'){
                ok=false;
            }
            if(helpgrid[row1][col1]!='B') {

                sameTileTypeTail.add(start1);

            }

            while (sameTileTypeTail.size() != 0) {


                Pair start = sameTileTypeTail.remove(0);
                Integer row = (Integer) start.getValue0();
                Integer col = (Integer) start.getValue1();
                sameTileList.add(start);

                helpgrid[row][col]='G';

                if (hasLowerItemTile(row, col) && helpgrid[row + 1][col] != 'B'&& helpgrid[row + 1][col] != 'P') {

                    if (getLowerItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row + 1, col));
                        counter++;
                        helpgrid[row + 1][col] = 'P';

                    } else {
                        if (helpgrid[row + 1][col] == 'W') {
                            helpgrid[row + 1][col] = 'G';
                            differentTileTypeTail.add(new Pair<>(row + 1, col));
                        }

                    }
                }
                if (hasUpperItemTile(row, col) && helpgrid[row - 1][col] != 'B' && helpgrid[row - 1][col] != 'P') {

                    if (this.getUpperItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row - 1, col));
                        counter++;
                        helpgrid[row - 1][col] = 'P';

                    } else {
                        if (helpgrid[row - 1][col] == 'W') {
                            helpgrid[row - 1][col] = 'G';
                            differentTileTypeTail.add(new Pair<>(row - 1, col));
                        }
                    }
                }
                if (hasRightItemTile(row, col) && helpgrid[row][col + 1] != 'B' && helpgrid[row][col + 1] != 'P') {

                    if (this.getRightItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row, col + 1));
                        counter++;
                        helpgrid[row][col + 1] = 'P';

                    } else {
                        if (helpgrid[row][col + 1] == 'W') {
                            helpgrid[row][col + 1] = 'G';
                            differentTileTypeTail.add(new Pair<>(row, col + 1));
                        }

                    }
                }
                if (hasLeftItemTile(row, col) && helpgrid[row][col - 1] != 'B' && helpgrid[row][col - 1] != 'P') {

                    if (this.getLeftItemTile(row, col).getItemTileType() == currentItemTileType) {

                        sameTileTypeTail.add(new Pair<>(row, col - 1));
                        counter++;
                        helpgrid[row][col - 1] = 'P';
                    } else {
                        if (helpgrid[row][col - 1] == 'W') {
                            helpgrid[row][col - 1] = 'G';
                            differentTileTypeTail.add(new Pair<>(row, col - 1));
                        }

                    }
                }
                helpgrid[row][col] = 'B';


            }
            if(ok) {

                listGroupsAdjacentTiles.add(new Pair<>(currentItemTileType, counter));

            }

        }


        return listGroupsAdjacentTiles;
    }

}
