package it.polimi.ingsw.model.logic;

import it.polimi.ingsw.model.data.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;

import java.util.List;

public class LibraryManager {

    private final GameData gameData;

    public LibraryManager(GameData gameData) {
        this.gameData = gameData;
    }

    public void insertItemTiles(int column, List<ItemTile> itemTileList, String username){
        ItemTile[][] libraryGrid = this.gameData.getPlayerDashboards().get(username).getLibrary().getGrid();
        int counter = 0;
        for (ItemTile[] itemTiles : libraryGrid) {
            if (itemTiles[column].getItemTileType() == ItemTileType.EMPTY) counter++;
        }
        if(counter > itemTileList.size()) throw new IllegalArgumentException("The column is already full");

    }
}
