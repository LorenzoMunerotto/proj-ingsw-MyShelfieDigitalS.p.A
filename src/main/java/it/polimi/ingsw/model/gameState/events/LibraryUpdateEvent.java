package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class LibraryUpdateEvent extends ModelEvent {

    private final ItemTileType[][] libraryGrid;
    private final String username;

    public LibraryUpdateEvent(Library library, String username) {
        this.libraryGrid = new ItemTileType[library.getROWS()][library.getCOLUMNS()];

        for (int row = 0; row < library.getROWS(); row++) {
            for (int col = 0; col < library.getCOLUMNS(); col++) {
                this.libraryGrid[row][col] = library.getItemTile(row, col).getItemTileType();
            }
        }
        this.username=username;
    }
}