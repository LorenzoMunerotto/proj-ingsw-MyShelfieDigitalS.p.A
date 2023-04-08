package it.polimi.ingsw.model.gameEntity;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class representing a single cell on the game board.
 */
public class BoardCell {

    /**
     * The item tile located on the board cell.
     */
    private ItemTile itemTile;

    private boolean playable;

    /**
     * Constructor for the BoardCell class, initializes the board cell with the given row and column numbers.
     *

     */
    public BoardCell(boolean playable) {
        this.itemTile = new ItemTile();
        this.playable = playable;
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
     * Get if the board cell is playable.
     *
     * @return true if the board cell is playable, false otherwise
     */
    public boolean isPlayable() {
        return playable;
    }

    /**
     * Set the board cell as playable or not.
     *
     * @param playable true if the board cell is playable, false otherwise
     */
    public void setPlayable(boolean playable) {
        this.playable = playable;
    }
}
