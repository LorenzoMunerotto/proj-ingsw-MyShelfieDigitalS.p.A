package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class PersonalCardSetMessage implements ServerMessage{

    private final ItemTileType[][] libraryGrid;

    public PersonalCardSetMessage(ItemTileType[][] libraryGrid) {
        this.libraryGrid = libraryGrid;
    }

    public ItemTileType[][] getLibraryGrid() {
        return libraryGrid;
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
