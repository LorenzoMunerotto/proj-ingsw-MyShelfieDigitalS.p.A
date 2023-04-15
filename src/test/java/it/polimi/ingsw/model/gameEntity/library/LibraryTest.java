package it.polimi.ingsw.model.gameEntity.library;

import it.polimi.ingsw.model.gameEntity.ItemTile;


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
                () -> assertEquals(itemTile, libraryTestHelper.getItemTile(0,0)),
                () -> assertThrows(IllegalArgumentException.class, () -> libraryTestHelper.setItemTile(libraryTestHelper.getROWS(), libraryTestHelper.getCOLUMNS() , itemTile))
        );
    }

    @Test
    @DisplayName("Test insert item tile for right and wrong parameters")
    public void testInsertItemTile() {

        libraryTestHelper.insertItemTile(0, new ItemTile(ItemTileType.PLANT));
        assertEquals(ItemTileType.PLANT, libraryTestHelper.getItemTile(5, 0).getItemTileType());

        libraryTestHelper.insertItemTile(0, new ItemTile(ItemTileType.CAT));
        assertEquals(ItemTileType.CAT, libraryTestHelper.getItemTile(4, 0).getItemTileType());

        libraryTestHelper.insertItemTile(1, new ItemTile(ItemTileType.FRAME));
        assertEquals(ItemTileType.FRAME, libraryTestHelper.getItemTile(5, 1).getItemTileType());

        libraryTestHelper.insertItemTile(1, new ItemTile(ItemTileType.TROPHY));
        assertEquals(ItemTileType.TROPHY, libraryTestHelper.getItemTile(4, 1).getItemTileType());

        for (int i = 0; i < 6; i++) {
            libraryTestHelper.insertItemTile(2, new ItemTile(ItemTileType.BOOK));
        }
        assertEquals(ItemTileType.BOOK, libraryTestHelper.getItemTile(0, 2).getItemTileType());
        assertThrows(IllegalArgumentException.class, () -> libraryTestHelper.insertItemTile(2, new ItemTile(ItemTileType.PLANT)));

        assertThrows(IllegalArgumentException.class, () -> libraryTestHelper.insertItemTile(-1, new ItemTile(ItemTileType.PLANT)));
        assertThrows(IllegalArgumentException.class, () -> libraryTestHelper.insertItemTile(5, new ItemTile(ItemTileType.PLANT)));
    }
}

