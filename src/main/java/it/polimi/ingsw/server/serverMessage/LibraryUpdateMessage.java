package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.List;

/**
 * This class represents the message that server sends to
 * client for update a library in virtual model
 */
public class LibraryUpdateMessage implements ServerMessage {

    private final String message;
    private final String libraryOwnerUsername;
    private final List<ItemTileType> itemTileTypeList;
    private final int column;
    private final long checksum;

    public LibraryUpdateMessage(String message, String libraryOwnerUsername, List<ItemTileType> itemTileTypeList, int column, long checksum) {
        this.message = message;
        this.libraryOwnerUsername = libraryOwnerUsername;
        this.itemTileTypeList = itemTileTypeList;
        this.column = column;
        this.checksum = checksum;
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

    public List<ItemTileType> getItemTileTypeList() {
        return itemTileTypeList;
    }

    public int getColumn() {
        return column;
    }

    public long getChecksum() {
        return checksum;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
