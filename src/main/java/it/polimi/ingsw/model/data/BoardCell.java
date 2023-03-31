package it.polimi.ingsw.model.data;

import it.polimi.ingsw.model.data.enums.ItemTileType;

/**
 * Class representing a single cell on the game board.
 */
public class BoardCell {

    /**
     * The item tile located on the board cell.
     */
    private ItemTile itemTile;
    /**
     * The row number of the board cell.
     */
    private final int row;
    /**
     * The column number of the board cell.
     */
    private final int column;
    /**
     * An attribute indicating whether the cell is selectable.
     */
    private boolean valid;

    /**
     * Constructor for the BoardCell class, initializes the board cell with the given row and column numbers.
     *
     * @param row      the row number of the board cell
     * @param column   the column number of the board cell
     */
    public BoardCell(int row, int column) {
        this.itemTile = new ItemTile(ItemTileType.EMPTY);
        this.row = row;
        this.column = column;
        this.valid = false;
    }

    /**
     * Get the item tile located on the board cell.
     *
     * @return the item tile
     */
    public ItemTile getItemTile() {
        return this.itemTile;
    }

    /**
     * Set the item tile located on the board cell.
     *
     * @param itemTile the new item tile
     */
    public void setItemTile(ItemTile itemTile) {
        this.itemTile = itemTile;
    }

    /**
     * Get the row number of the board cell.
     *
     * @return the row number
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Get the column number of the board cell.
     *
     * @return the column number
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Get the attribute indicating whether the cell is selectable.
     *
     * @return true if the cell is selectable, false otherwise
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * Set the attribute indicating whether the cell is selectable.
     *
     * @param valid true if the cell is selectable, false otherwise
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
