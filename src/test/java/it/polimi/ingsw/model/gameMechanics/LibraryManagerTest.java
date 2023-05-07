package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;

import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagerTest {
    private LibraryManager libraryManager;
    private LibraryTestHelper library;

    @BeforeEach
    public void setUp() {
        library = new LibraryTestHelper();
        libraryManager = new LibraryManager();
        libraryManager.setLibrary(library);
    }

    @Test
    @DisplayName("Test the insertion of item tiles in the library")
    public void testInsertItemTiles() {
        List<ItemTileType> itemTileList = Arrays.asList(
                ItemTileType.CAT,
                ItemTileType.PLANT,
                ItemTileType.CAT,
                ItemTileType.FRAME
        );

        libraryManager.insertItemTiles(0, itemTileList);
        assertEquals(ItemTileType.CAT, library.getItemTile(5, 0));
        assertEquals(ItemTileType.PLANT, library.getItemTile(4, 0));
        assertEquals(ItemTileType.CAT, library.getItemTile(3, 0));
    }

    @Test
    @DisplayName("Test that the method that returns the number of empty tiles in a column works correctly")
    public void testEmptyTilesCounter() {
        assertEquals(6, libraryManager.emptyTilesCounter(0));
        List<ItemTileType> itemTileList = Arrays.asList(
                ItemTileType.CAT,
                ItemTileType.PLANT,
                ItemTileType.CAT
        );

        libraryManager.insertItemTiles(0, itemTileList);
        assertEquals(3, libraryManager.emptyTilesCounter(0));
    }

    @Test
    @DisplayName("Test the method that returns the largest groups of adjacent tiles")
    public void testGetLargestGroupsMap() {

        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = Arrays.asList(
                new Pair<>(ItemTileType.TROPHY, 3),
                new Pair<>(ItemTileType.CAT, 2),
                new Pair<>(ItemTileType.FRAME, 1),
                new Pair<>(ItemTileType.GAME, 4),
                new Pair<>(ItemTileType.PLANT, 2),
                new Pair<>(ItemTileType.CAT, 4),
                new Pair<>(ItemTileType.FRAME, 5),
                new Pair<>(ItemTileType.GAME, 2),
                new Pair<>(ItemTileType.PLANT, 3)
        );

        Map<ItemTileType, Integer> expectedLargestGroups = new HashMap<>();
        expectedLargestGroups.put(ItemTileType.TROPHY, 3);
        expectedLargestGroups.put(ItemTileType.CAT, 4);
        expectedLargestGroups.put(ItemTileType.FRAME, 5);
        expectedLargestGroups.put(ItemTileType.GAME, 4);
        expectedLargestGroups.put(ItemTileType.PLANT, 3);

        LibraryManager libraryManager = new LibraryManager();
        libraryManager.setLibrary(library);
        Map<ItemTileType, Integer> largestGroupsMap = libraryManager.getLargestGroupsMap(listGroupsAdjacentTiles);

        for (Map.Entry<ItemTileType, Integer> entry : largestGroupsMap.entrySet()) {
            ItemTileType type = entry.getKey();
            int size = entry.getValue();
            assertTrue(expectedLargestGroups.containsKey(type));
            assertEquals(expectedLargestGroups.get(type), size);
        }
    }

    @Test
    @DisplayName("Test the method that checks if the adjacent groups are calculated correctly")
    public void testGetListGroupsAdjacentTiles() {
        ItemTileType[][] libraryGrid = {
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.FRAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.PLANT, ItemTileType.EMPTY},
                {ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.PLANT, ItemTileType.FRAME},
        };
        library.setLibrary(libraryGrid);

        Map<ItemTileType, Integer> expectedLargestGroups = new HashMap<>();
        expectedLargestGroups.put(ItemTileType.TROPHY, 2);
        expectedLargestGroups.put(ItemTileType.CAT, 5);
        expectedLargestGroups.put(ItemTileType.FRAME, 2);
        expectedLargestGroups.put(ItemTileType.GAME, 2);
        expectedLargestGroups.put(ItemTileType.PLANT, 3);

        List<Pair<ItemTileType, Integer>> listGroupsAdjacentTiles = libraryManager.getListGroupsAdjacentTiles();
        Map<ItemTileType, Integer> largestGroupsMap = libraryManager.getLargestGroupsMap(listGroupsAdjacentTiles);

        for (Map.Entry<ItemTileType, Integer> entry : largestGroupsMap.entrySet()) {
            ItemTileType type = entry.getKey();
            int size = entry.getValue();
            assertTrue(expectedLargestGroups.containsKey(type));
            assertEquals(expectedLargestGroups.get(type), size);
        }
    }

    @Test
    @DisplayName("Test the method that checks if the library is full")
    public void testIsFull() {
        assertFalse(libraryManager.isFull());

        for (int col = 0; col < library.getLibraryGrid()[0].length; col++) {
            List<ItemTileType> itemTileList = new ArrayList<>();
            for (int row = 0; row < library.getLibraryGrid().length; row++) {
                itemTileList.add(ItemTileType.CAT);
            }
            libraryManager.insertItemTiles(col, itemTileList);
        }
        assertTrue(libraryManager.isFull());
    }
}
