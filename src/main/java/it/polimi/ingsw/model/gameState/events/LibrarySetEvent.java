package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class defining the event of setting the library.
 */
public class LibrarySetEvent implements ModelEvent {

    /**
     * The library grid.
     */
    private final ItemTileType[][] libraryGrid;
    /**
     * The username of the owner of the library.
     */
    private final String username;

    /**
     * Default constructor.
     *
     * @param library is the library
     * @param username is the username of the owner of the library
     */
    public LibrarySetEvent(Library library, String username) {
        this.libraryGrid = new ItemTileType[library.getLibraryGrid().length][library.getLibraryGrid()[0].length];

        for (int row = 0; row < library.getLibraryGrid().length; row++) {
            for (int col = 0; col < library.getLibraryGrid()[0].length; col++) {
                this.libraryGrid[row][col] = library.getItemTile(row, col);
            }
        }
        this.username = username;
    }

    /**
     * Get the library grid.
     *
     * @return the library grid
     */
    public ItemTileType[][] getLibraryGrid() {
        return libraryGrid;
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
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}