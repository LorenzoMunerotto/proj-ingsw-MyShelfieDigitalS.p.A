package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class defining the event of the refill of the board.
 */
public class BoardRefillEvent implements ModelEvent{

    /**
     * The board grid after the refill.
     */
    private final ItemTileType[][] boardGrid;
    /**
     * The checksum of the board grid after the refill.
     */
    private final long checksum;

    /**
     * Default constructor.
     *
     * @param boardGrid is the board grid after the refill
     * @param checksum is the checksum of the board grid after the refill
     */
    public BoardRefillEvent(ItemTileType[][] boardGrid, long checksum) {
        this.boardGrid = new ItemTileType[boardGrid.length][boardGrid[0].length];
        for(int row = 0; row < boardGrid.length; row++)
            System.arraycopy(boardGrid[row], 0, this.boardGrid[row], 0, boardGrid[row].length);
        this.checksum = checksum;
    }

    /**
     * Ge the board grid after the refill.
     *
     * @return the board grid after the refill
     */
    public ItemTileType[][] getBoardGrid() {
        return boardGrid;
    }

    /**
     * Get the checksum of the board grid after the refill.
     *
     * @return the checksum of the board grid after the refill
     */
    public long getChecksum() {
        return checksum;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}