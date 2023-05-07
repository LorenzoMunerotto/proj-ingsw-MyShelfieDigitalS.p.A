package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.List;

public class LibraryUpdateEvent implements ModelEvent {
    private final List<ItemTileType> itemTileTypeList;
    private final int column;
    private final String username;
    private final long checksum;

    public LibraryUpdateEvent(List<ItemTileType> itemTileTypeList, int column, String username, long checksum) {
        this.itemTileTypeList = itemTileTypeList;
        this.column = column;
        this.username = username;
        this.checksum = checksum;
    }

    public List<ItemTileType> getItemTileTypeList() {
        return itemTileTypeList;
    }

    public int getColumn() {
        return column;
    }

    public String getUsername() {
        return username;
    }

    public long getChecksum() {
        return checksum;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
