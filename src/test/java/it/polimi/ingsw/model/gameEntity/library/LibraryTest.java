package it.polimi.ingsw.model.gameEntity.library;


import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    LibraryTestHelper libraryTestHelper;

    @BeforeEach
    void setUp() {
        libraryTestHelper = new LibraryTestHelper();
    }

    @Test
    @DisplayName("Test that the constructor initializes the grid with empty item tiles")
    void testConstructor() {
        ItemTileType[][] grid = libraryTestHelper.getLibraryGrid();

        assertAll(
                () -> assertNotNull(grid),
                () -> assertEquals(6, grid.length),
                () -> assertEquals(5, grid[0].length)
        );

        for (ItemTileType[] itemTiles : grid) {
            for (ItemTileType itemTile : itemTiles) {
                assertNotNull(itemTile);
                assertEquals(ItemTileType.EMPTY, itemTile);
            }
        }
    }

    @Test
    @DisplayName("Test setItemTile for right and wrong parameters")
    void setItemTile() {
        ItemTileType itemTile = ItemTileType.CAT;
        libraryTestHelper.setItemTile(0, 0, itemTile);
        assertEquals(itemTile, libraryTestHelper.getItemTile(0, 0));
    }
}

