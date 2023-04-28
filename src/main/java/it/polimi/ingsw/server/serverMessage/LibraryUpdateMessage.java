package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class LibraryUpdateMessage implements ServerMessage {

    private final String message;
    private final String libraryOwnerUsername;
    private final ItemTileType[][] libraryGrid;

    public LibraryUpdateMessage(String message, String libraryOwnerUsername, Library library) {
        this.message = message;
        this.libraryOwnerUsername = libraryOwnerUsername;
        this.libraryGrid = new ItemTileType[library.getROWS()][library.getCOLUMNS()];

        for (int row = 0; row < library.getROWS(); row++) {
            for (int col = 0; col < library.getCOLUMNS(); col++) {
                this.libraryGrid[row][col] = library.getItemTile(row, col).getItemTileType();
            }
        }
    }

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
}
