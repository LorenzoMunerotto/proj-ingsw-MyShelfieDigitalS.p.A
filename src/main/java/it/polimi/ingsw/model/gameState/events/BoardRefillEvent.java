package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class BoardRefillEvent implements ModelEvent{
    private final ItemTileType[][] boardGrid;
    private final long checksum;

    public BoardRefillEvent(ItemTileType[][] boardGrid, long checksum) {
        this.boardGrid = boardGrid;
        this.checksum = checksum;
    }

    public ItemTileType[][] getBoardGrid() {
        return boardGrid;
    }

    public long getChecksum() {
        return checksum;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
