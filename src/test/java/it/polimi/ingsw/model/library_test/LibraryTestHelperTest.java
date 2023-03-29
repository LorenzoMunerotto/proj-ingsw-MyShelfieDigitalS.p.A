package it.polimi.ingsw.model.library_test;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTestHelperTest {

    LibraryTestHelper libraryTestHelper;

    @BeforeEach
    void setUp() {
        libraryTestHelper = new LibraryTestHelper();
    }

    @Test
    @DisplayName("Test set item tile")
    void setItemTile() {
        ItemTile itemTile = new ItemTile(ItemTileType.CAT);
        assertNotEquals(itemTile, libraryTestHelper.getGrid()[3][2]);
        libraryTestHelper.setItemTile(3, 2, itemTile);
        assertEquals(itemTile, libraryTestHelper.getGrid()[3][2]);
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
        for(int i = 0; i < libraryGrid.length; i++){
            for(int j = 0; j < libraryGrid[i].length; j++){
                assertEquals(libraryGrid[i][j], libraryTestHelper.getGrid()[i][j].getItemTileType());
            }
        }
    }
}