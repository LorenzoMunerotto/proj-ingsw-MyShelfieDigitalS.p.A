package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class BoardSetEvent implements ModelEvent{
    private final ItemTileType[][] boardGrid;

    public BoardSetEvent(Board board) {
        this.boardGrid = new ItemTileType[board.getBoardGrid().length][board.getBoardGrid()[0].length];
        for (int row = 0; row < board.getBoardGrid().length; row++) {
            for (int col = 0; col < board.getBoardGrid()[0].length; col++) {
                this.boardGrid[row][col] = board.getItemTile(row, col);
            }
        }
    }

    public ItemTileType[][] getBoardGrid() {
        return this.boardGrid;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
