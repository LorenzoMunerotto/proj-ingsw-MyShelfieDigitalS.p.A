package it.polimi.ingsw.model.library_test;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
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
        ItemTile[][] grid = libraryTestHelper.getGrid();

        assertAll(
                () -> assertNotNull(grid),
                () -> assertEquals(6, grid.length),
                () -> assertEquals(5, grid[0].length)
        );

        for (ItemTile[] itemTiles : grid) {
            for (ItemTile itemTile : itemTiles) {
                assertNotNull(itemTile);
                assertEquals(ItemTileType.EMPTY, itemTile.getItemTileType());
            }
        }
    }

    @Test
    @DisplayName("Test setItemTile for right and wrong parameters")
    void setItemTile() {
        ItemTile itemTile = new ItemTile(ItemTileType.CAT);
        libraryTestHelper.setItemTile(0, 0, itemTile);

        assertAll(
                () -> assertEquals(itemTile, libraryTestHelper.getGrid()[0][0]),
                () -> assertThrows(IllegalArgumentException.class, () -> libraryTestHelper.setItemTile(libraryTestHelper.getGrid().length + 1, libraryTestHelper.getGrid()[0].length + 1, itemTile))
        );
    }
}
