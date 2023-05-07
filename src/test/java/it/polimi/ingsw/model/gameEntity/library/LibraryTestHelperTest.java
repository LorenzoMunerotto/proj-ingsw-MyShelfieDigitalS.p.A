package it.polimi.ingsw.model.gameEntity.library;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LibraryTestHelperTest {

    LibraryTestHelper libraryTestHelper;

    @BeforeEach
    void setUp() {
        libraryTestHelper = new LibraryTestHelper();
    }

    @Test
    @DisplayName("Test set item tile")
    void setItemTile() {
        ItemTileType itemTile = ItemTileType.CAT;
        assertNotEquals(itemTile, libraryTestHelper.getItemTile(3,2));
        libraryTestHelper.setItemTile(3, 2, itemTile);
        assertEquals(itemTile, libraryTestHelper.getItemTile(3,2));
    }

    @Test
    @DisplayName("Test set library")
    void setLibrary() {
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.PLANT, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.PLANT, ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        for(int row = 0; row < libraryTestHelper.getLibraryGrid().length; row++){
            for(int col = 0; col < libraryTestHelper.getLibraryGrid()[0].length; col++){
                assertEquals(libraryGrid[row][col], libraryTestHelper.getItemTile(row,col));
            }
        }
    }
}