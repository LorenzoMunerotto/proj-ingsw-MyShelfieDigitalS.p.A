package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class representing the board of the game.
 */
public class Board {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;

    /**
     * Data structure to represent the board.
     */
    private final BoardCell[][] boardGrid;


    /**
     * Index of the cell of the board [row][column].
     * [0,0] [0,1] [0,2] [0,3] [0,4] [0,5] [0,6] [0,7] [0,8]
     * [1,0] [1,1] [1,2] [1,3] [1,4] [1,5] [1,6] [1,7] [1,8]
     * [2,0] [2,1] [2,2] [2,3] [2,4] [2,5] [2,6] [2,7] [2,8]
     * [3,0] [3,1] [3,2] [3,3] [3,4] [3,5] [3,6] [3,7] [3,8]
     * [4,0] [4,1] [4,2] [4,3] [4,4] [4,5] [4,6] [4,7] [4,8]
     * [5,0] [5,1] [5,2] [5,3] [5,4] [5,5] [5,6] [5,7] [5,8]
     * [6,0] [6,1] [6,2] [6,3] [6,4] [6,5] [6,6] [6,7] [6,8]
     * [7,0] [7,1] [7,2] [7,3] [7,4] [7,5] [7,6] [7,7] [7,8]
     * [8,0] [8,1] [8,2] [8,3] [8,4] [8,5] [8,6] [8,7] [8,8]
     */

    /**
     * Number of players required to use the cell.
     * Empty cells are considered null.
     * [ ] [ ] [ ] [3] [4] [ ] [ ] [ ] [ ]
     * [ ] [ ] [ ] [2] [2] [4] [ ] [ ] [ ]
     * [ ] [ ] [3] [2] [2] [2] [3] [ ] [ ]
     * [ ] [4] [2] [2] [2] [2] [2] [2] [3]
     * [4] [2] [2] [2] [2] [2] [2] [2] [4]
     * [3] [2] [2] [2] [2] [2] [2] [4] [ ]
     * [ ] [ ] [3] [2] [2] [2] [3] [ ] [ ]
     * [ ] [ ] [ ] [4] [2] [2] [ ] [ ] [ ]
     * [ ] [ ] [ ] [ ] [4] [3] [ ] [ ] [ ]
     */

    /**
     * Constructor Board, initializes the cells in the grid, based on the number of players.
     *
     * @param numPlayers number of player in the game
     */
    public Board(int numPlayers) {
        if(numPlayers < 2 || numPlayers > 4) throw new IllegalArgumentException("Invalid number of players");
        boolean createCell3 = (numPlayers == 3 || numPlayers == 4);
        boolean createCell4 = (numPlayers == 4);


        this.boardGrid = new BoardCell[][] {
                { new BoardCell(false), new BoardCell(false), new BoardCell(false), (createCell3 ? new BoardCell(true) : new BoardCell(false)), (createCell4 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(false), new BoardCell(false), new BoardCell(false), new BoardCell(false) },
                { new BoardCell(false), new BoardCell(false), new BoardCell(false), new BoardCell(true), new BoardCell(true), (createCell4 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(false), new BoardCell(false), new BoardCell(false) },
                { new BoardCell(false), new BoardCell(false), (createCell3 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(true), new BoardCell(true), new BoardCell(true), (createCell3 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(false), new BoardCell(false) },
                { new BoardCell(false), (createCell4 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), (createCell3 ? new BoardCell(true) : new BoardCell(false)) },
                { (createCell4 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), (createCell4 ? new BoardCell(true) : new BoardCell(false)) },
                { (createCell3 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), new BoardCell(true), (createCell4 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(false) },
                { new BoardCell(false), new BoardCell(false), (createCell3 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(true), new BoardCell(true), new BoardCell(true), (createCell3 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(false), new BoardCell(false) },
                { new BoardCell(false), new BoardCell(false), new BoardCell(false), (createCell4 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(true), new BoardCell(true), new BoardCell(false), new BoardCell(false), new BoardCell(false) },
                { new BoardCell(false), new BoardCell(false), new BoardCell(false), new BoardCell(false), (createCell4 ? new BoardCell(true) : new BoardCell(false)), (createCell3 ? new BoardCell(true) : new BoardCell(false)), new BoardCell(false), new BoardCell(false), new BoardCell(false) },
        };
    }

    /**
     * Get the BoardCell in pos[row][column]
     *
     */
    public BoardCell getBoardCell(int row, int column){
        if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        BoardCell boardCell = boardGrid[row][column];
        return boardCell;
    }

    public void putItemTile(int row, int col, ItemTile itemTile){
        if (row < 0 || row >= ROWS || col < 0 || col >= COLUMNS) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        if (!boardGrid[row][col].isPlayable()) throw new IllegalArgumentException("stai cercando di mettere una tessera su una cella non in gioco");
        if (boardGrid[row][col].getItemTile().getItemTileType()!=ItemTileType.EMPTY) throw new IllegalArgumentException("stai cercando di mettere una tessera sopra ad una tessera valida");
        boardGrid[row][col].setItemTile(itemTile);
    }

    public ItemTile takeItemTile(int row, int col){
        if (row < 0 || row >= ROWS || col < 0 || col >= COLUMNS) {
            throw new IllegalArgumentException("Row or column parameter is out of bounds.");
        }
        if (!boardGrid[row][col].isPlayable()) throw new IllegalArgumentException("stai cercando di predere una tessera su una cella non in gioco");
        if (boardGrid[row][col].getItemTile().getItemTileType()==ItemTileType.EMPTY) throw new IllegalArgumentException("stai cercando di prendere una tessera su una cella vuota");
        ItemTile itemTile = boardGrid[row][col].getItemTile();
        boardGrid[row][col].setItemTile(new ItemTile());
        return itemTile;
    }


    /**
     * Return True if the tile in pos[row][col] has Upper tile
     *
     */

    public static boolean hasUpperBoardCell(Integer row, Integer col){
        return row>0 && row < ROWS && col>=0 && col<COLUMNS;
    }

    /**
     * Return True if the tile in pos[row][col] has Lower tile
     *
     */
    public static boolean hasLowerBoardCell(Integer row, Integer col){
        return row >= 0 && row < ROWS-1 && col>=0 && col < COLUMNS ;
    }

    /**
     * Return True if the tile in pos[row][col] has Right tile
     */
    public static boolean hasRightBoardCell(Integer row, Integer col){
        return row >= 0 && row < ROWS && col>=0 && col < COLUMNS-1 ;
    }

    /**
     * Return True if the tile in pos[row][col] has Left tile

     */
    public static boolean hasLeftBoardCell(Integer row, Integer col){
        return row >= 0 && row < ROWS && col>0 && col < COLUMNS ;
    }

    /**
     * Return the Upper tile of the tile in pos[row][col]

     */
    public BoardCell getUpperBoardCell(Integer row, Integer col){
        return getBoardCell(row-1, col);

    }

    /**
     * Return the Lower tile of the tile in pos[row][col]

     */
    public BoardCell getLowerBoardCell(Integer row, Integer col){
        return getBoardCell(row+1, col);

    }

    /**
     * Return the Right tile of the tile in pos[row][col]

     */
    public BoardCell getRightBoardCell(Integer row, Integer col){
        return getBoardCell(row, col+1);

    }

    /**
     * Return the Left tile of the tile in pos[row][col]
     *
     */
    public BoardCell getLeftBoardCell(Integer row, Integer col){
       return  getBoardCell(row, col-1);

    }
    public int getROWS(){
        return ROWS;
    }

    public int getCOLUMNS(){
        return COLUMNS;
    }

    public boolean isAlone (int row, int col){
        int count =0;
        if (Board.hasLeftBoardCell(row,col)){
            if (getLeftBoardCell(row,col).getItemTile().getItemTileType() == ItemTileType.EMPTY){
                count++;
            }
        }
        else count++;
        if (Board.hasUpperBoardCell(row,col)){
            if (getUpperBoardCell(row,col).getItemTile().getItemTileType() == ItemTileType.EMPTY){
                count++;
            }
        }
        else count++;
        if (Board.hasRightBoardCell(row,col)){
            if (getRightBoardCell(row,col).getItemTile().getItemTileType() == ItemTileType.EMPTY){
                count++;
            }
        }
        else count++;
        if (Board.hasLowerBoardCell(row,col)){
            if (getLowerBoardCell(row,col).getItemTile().getItemTileType() == ItemTileType.EMPTY){
                count++;
            }
        }
        else count++;

        return count==4;
    }




}
