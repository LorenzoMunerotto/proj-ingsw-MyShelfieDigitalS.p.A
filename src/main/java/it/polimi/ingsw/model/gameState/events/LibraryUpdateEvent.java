package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import java.util.List;

/**
 * Class defining the event of a library update
 */
public class LibraryUpdateEvent implements ModelEvent {

    /**
     * List of the new tiles to be added to the library.
     */
    private final List<ItemTileType> itemTileTypeList;
    /**
     * Column of the library to be updated.
     */
    private final int column;
    /**
     * Username of the owner of the library.
     */
    private final String username;
    /**
     * Checksum of the library.
     */
    private final long checksum;

    /**
     * Default constructor.
     *
     * @param itemTileTypeList is the list of the new tiles to be added to the library
     * @param column           is the column of the library to be updated
     * @param username         is the username of the owner of the library
     * @param checksum         is the checksum of the library
     */
    public LibraryUpdateEvent(List<ItemTileType> itemTileTypeList, int column, String username, long checksum) {
        this.itemTileTypeList = itemTileTypeList;
        this.column = column;
        this.username = username;
        this.checksum = checksum;
    }

    /**
     * Get the list of the new tiles to be added to the library.
     *
     * @return the list of the new tiles to be added to the library
     */
    public List<ItemTileType> getItemTileTypeList() {
        return itemTileTypeList;
    }

    /**
     * Get the column of the library to be updated.
     *
     * @return the column of the library to be updated
     */
    public int getColumn() {
        return column;
    }

    /**
     * Get the username of the owner of the library.
     *
     * @return the username of the owner of the library
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the checksum of the library.
     *
     * @return the checksum of the library
     */
    public long getChecksum() {
        return checksum;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}