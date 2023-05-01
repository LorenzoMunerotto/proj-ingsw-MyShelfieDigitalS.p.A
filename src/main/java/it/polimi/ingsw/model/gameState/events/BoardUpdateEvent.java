package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.Listener;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.server.ModelChangeEventHandler;

public class BoardUpdateEvent extends ModelEvent {

    private final ItemTileType[][] boardGrid;
    private final boolean refill;

    public BoardUpdateEvent(Board board, boolean isRefill) {
        this.boardGrid = new ItemTileType[board.getROWS()][board.getCOLUMNS()];

        for (int row = 0; row < board.getROWS(); row++) {
            for (int col = 0; col < board.getCOLUMNS(); col++) {
                this.boardGrid[row][col] = board.getBoardCell(row, col).getItemTile().getItemTileType();
            }
        }
        this.refill = isRefill;
    }

    public ItemTileType[][] getBoardGrid() {
        return boardGrid;
    }

    public boolean isRefill() {
        return refill;
    }

    public void accept(Listener listener) {

        if (listener instanceof ModelChangeEventHandler) {
            ModelChangeEventHandler virtualClient = (ModelChangeEventHandler) listener;
            virtualClient.handle(this);
        }

    }

}
