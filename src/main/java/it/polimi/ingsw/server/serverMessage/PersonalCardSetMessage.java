package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * This class represents the message that server sends to
 * client for set the personal goal card in virtual model
 */
public class PersonalCardSetMessage implements ServerMessage{

    private final int index;
    private final ItemTileType[][] libraryGrid;

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

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return null;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
