package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    @DisplayName("Test that the constructor initializes the grid with empty item tiles")
    void testConstructor() {
        ItemTile[][] grid = library.getGrid();

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
        library.setItemTile(0, 0, itemTile);

        assertAll(
                () -> assertEquals(itemTile, library.getGrid()[0][0]),
                () -> assertThrows(IllegalArgumentException.class, () -> library.setItemTile(library.getGrid().length + 1, library.getGrid()[0].length + 1, itemTile))
        );
    }
}