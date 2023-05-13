package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing the board of the game.
 */
public class Board {

    /**
     * Data structure to represent the board.
     */
    private final ItemTileType[][] boardGrid;

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
        boolean createCell3 = (numPlayers == 3 || numPlayers == 4);
        boolean createCell4 = (numPlayers == 4);

        this.boardGrid = new ItemTileType[][]{
                {ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL, (createCell3 ? ItemTileType.EMPTY : ItemTileType.NULL), (createCell4 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL},
                {ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL, ItemTileType.EMPTY, ItemTileType.EMPTY, (createCell4 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL},
                {ItemTileType.NULL, ItemTileType.NULL, (createCell3 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, (createCell3 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.NULL, ItemTileType.NULL},
                {ItemTileType.NULL, (createCell4 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, (createCell3 ? ItemTileType.EMPTY : ItemTileType.NULL)},
                {(createCell4 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, (createCell4 ? ItemTileType.EMPTY : ItemTileType.NULL)},
                {(createCell3 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, (createCell4 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.NULL},
                {ItemTileType.NULL, ItemTileType.NULL, (createCell3 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, (createCell3 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.NULL, ItemTileType.NULL},
                {ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL, (createCell4 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL},
                {ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL, (createCell4 ? ItemTileType.EMPTY : ItemTileType.NULL), (createCell3 ? ItemTileType.EMPTY : ItemTileType.NULL), ItemTileType.NULL, ItemTileType.NULL, ItemTileType.NULL},
        };
    }

    /**
     * Get the board grid.
     *
     * @return the board grid
     */
    public ItemTileType[][] getBoardGrid() {
        return this.boardGrid;
    }

    /**
     * Get the itemTile in the grid.
     *
     * @param row    is the row of the grid
     * @param column is the column of the grid
     * @return the itemTile in the grid
     */
    public ItemTileType getItemTile(int row, int column) {
        return this.boardGrid[row][column];
    }

    /**
     * Set the new itemTile in the grid.
     *
     * @param row      is the row of the grid
     * @param column   is the column of the grid
     * @param itemTile is the new itemTile to set
     */
    public void setItemTile(int row, int column, ItemTileType itemTile) {
        this.boardGrid[row][column] = itemTile;
    }
}