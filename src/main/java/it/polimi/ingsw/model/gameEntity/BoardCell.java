package it.polimi.ingsw.model.gameEntity;

/**
 * Class representing a single cell on the game board.
 */
public class BoardCell {

    /**
     * The item tile located on the board cell.
     */
    private ItemTile itemTile;
    /**
     * Playable is true if the BoardCell participates in the games.
     * For example the boardCell in boardGrid[0,0] has playable=false always,
     * boardCell in boardGrid[4,4] has playable=true always,
     * boardCell in boardGrid[4,0] has playable=true when numOfPlayers=4 and has playable=false when numOfPlayers<4
     * It's final because this attribute depends on the numOfPlayers and it cannot be changed during the game
     */
    private final boolean playable;

    /**
     * Constructor for the BoardCell class, initializes the board cell with the given row and column numbers.
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


}
