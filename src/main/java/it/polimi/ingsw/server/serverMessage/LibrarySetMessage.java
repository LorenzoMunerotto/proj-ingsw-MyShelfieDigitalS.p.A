package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class LibrarySetMessage implements ServerMessage{
    private final String message;
    private final String libraryOwnerUsername;
    private final ItemTileType[][] libraryGrid;

    public LibrarySetMessage(String message, String libraryOwnerUsername, ItemTileType[][] libraryGrid) {
        this.message = message;
        this.libraryOwnerUsername = libraryOwnerUsername;
        this.libraryGrid = libraryGrid;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    public String getLibraryOwnerUsername() {
        return libraryOwnerUsername;
    }

    public ItemTileType[][] getLibraryGrid() {
        return libraryGrid;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
