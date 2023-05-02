package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class BoardUpdateEvent implements ModelEvent {

    private final ItemTileType[][] boardGrid;
    private final boolean[][] playableGrid;
    private final boolean refill;

    public BoardUpdateEvent(Board board, boolean isRefill) {
        this.boardGrid = new ItemTileType[board.getROWS()][board.getCOLUMNS()];
        this.playableGrid = new boolean[board.getROWS()][board.getCOLUMNS()];
        for (int row = 0; row < board.getROWS(); row++) {
            for (int col = 0; col < board.getCOLUMNS(); col++) {
                this.boardGrid[row][col] = board.getBoardCell(row, col).getItemTile().getItemTileType();
                this.playableGrid[row][col] = board.getBoardCell(row,col).isPlayable();
            }
        }
        this.refill = isRefill;
    }

    public ItemTileType[][] getBoardGrid() {
        return boardGrid;
    }

    public boolean[][] getPlayableGrid() {
        return playableGrid;
    }

    public boolean isRefill() {
        return refill;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }


}
