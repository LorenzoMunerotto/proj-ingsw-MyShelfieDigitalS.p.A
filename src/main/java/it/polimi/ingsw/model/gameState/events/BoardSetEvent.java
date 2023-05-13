package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class defining the event of the setting of the board.
 */
public class BoardSetEvent implements ModelEvent {

    /**
     * The board grid after the setting.
     */
    private final ItemTileType[][] boardGrid;

    /**
     * Default constructor.
     *
     * @param board is the board after the setting
     */
    public BoardSetEvent(Board board) {
        this.boardGrid = new ItemTileType[board.getBoardGrid().length][board.getBoardGrid()[0].length];
        for (int row = 0; row < board.getBoardGrid().length; row++) {
            for (int col = 0; col < board.getBoardGrid()[0].length; col++) {
                this.boardGrid[row][col] = board.getItemTile(row, col);
            }
        }
    }

    /**
     * Get the board grid after the setting.
     *
     * @return the board grid after the setting
     */
    public ItemTileType[][] getBoardGrid() {
        return this.boardGrid;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}