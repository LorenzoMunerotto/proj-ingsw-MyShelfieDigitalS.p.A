package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class LibrarySetEvent implements ModelEvent {
    private final ItemTileType[][] libraryGrid;
    private final String username;

    public LibrarySetEvent(Library library, String username) {
        this.libraryGrid = new ItemTileType[library.getLibraryGrid().length][library.getLibraryGrid()[0].length];

        for (int row = 0; row < library.getLibraryGrid().length; row++) {
            for (int col = 0; col < library.getLibraryGrid()[0].length; col++) {
                this.libraryGrid[row][col] = library.getItemTile(row, col);
            }
        }
        this.username = username;
    }

    public ItemTileType[][] getLibraryGrid() {
        return libraryGrid;
    }

    public String getUsername() {
        return username;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
