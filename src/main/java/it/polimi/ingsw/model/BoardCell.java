package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ItemTileType;

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
    private boolean grey;
    /**
     * An attribute indicating whether the cell has no other adjacent cells.
     */
    private boolean alone;

    /**
     * Constructor for the BoardCell class.
     *
     * @param row      the row number of the board cell
     * @param column   the column number of the board cell
     */
    public BoardCell(int row, int column) {
        this.itemTile = new ItemTile(ItemTileType.EMPTY);
        this.row = row;
        this.column = column;
        this.grey = false;
        this.alone = false;
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
     * Remove the item tile from the cell and replace it with an empty one.
     *
     * @return the item tile removed
     */
    public ItemTile removeItemTile() {
        ItemTile removedTile = getItemTile();
        setItemTile(new ItemTile(ItemTileType.EMPTY));
        return removedTile;
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
     * Check whether the cell is grey-colored.
     *
     * @return true if the cell is grey-colored, false otherwise
     */
    public boolean getGrey() {
        return this.grey;
    }

    /**
     * Set the flag indicating whether the cell is grey-colored.
     *
     * @param grey the new value of the flag
     */
    public void setGrey(boolean grey) {
        this.grey = grey;
    }

    /**
     * Check whether the cell is alone.
     *
     * @return true if the cell is alone, false otherwise
     */
    public boolean getAlone() {
        return this.alone;
    }

    /**
     * Set the flag indicating whether the cell is alone.
     *
     * @param alone the new value of the flag
     */
    public void setAlone(boolean alone) {
        this.alone = alone;
    }
}
