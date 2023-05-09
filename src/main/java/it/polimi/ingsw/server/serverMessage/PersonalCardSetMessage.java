package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class PersonalCardSetMessage implements ServerMessage{

    private final ItemTileType[][] libraryGrid;
    private final int index;

    public PersonalCardSetMessage(ItemTileType[][] libraryGrid, int index) {
        this.libraryGrid = libraryGrid;
        this.index = index;
    }

    public ItemTileType[][] getLibraryGrid() {
        return libraryGrid;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
