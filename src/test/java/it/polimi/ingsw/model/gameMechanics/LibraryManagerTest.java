package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.ItemTile;
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
        libraryManager = new LibraryManager(library);
    }

    @Test
    @DisplayName("Test the insertion of item tiles in the library")
    public void testInsertItemTiles() {
        List<ItemTile> itemTileList = Arrays.asList(
                new ItemTile(ItemTileType.CAT),
                new ItemTile(ItemTileType.PLANT)
        );

        libraryManager.insertItemTiles(0, itemTileList);
        assertEquals(ItemTileType.CAT, library.getItemTile(5, 0).getItemTileType());
        assertEquals(ItemTileType.PLANT, library.getItemTile(4, 0).getItemTileType());

        assertThrows(IllegalArgumentException.class, () -> libraryManager.insertItemTiles(5, itemTileList));
    }

    @Test
    @DisplayName("Test the exception thrown when there is not enough space in the library")
    public void testHasEnoughSpace() throws BreakRulesException {
        libraryManager.hasEnoughSpace(0, 3);

        List<ItemTile> itemTileList = Arrays.asList(
                new ItemTile(ItemTileType.CAT),
                new ItemTile(ItemTileType.PLANT),
                new ItemTile(ItemTileType.BOOK),
                new ItemTile(ItemTileType.CAT)
        );

        libraryManager.insertItemTiles(0, itemTileList);
        assertThrows(BreakRulesException.class, () -> libraryManager.hasEnoughSpace(0, 3));
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

        LibraryManager libraryManager = new LibraryManager(library);
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

        for (int col = 0; col < library.getCOLUMNS(); col++) {
            List<ItemTile> itemTileList = new ArrayList<>();
            for (int row = 0; row < library.getROWS(); row++) {
                itemTileList.add(new ItemTile(ItemTileType.CAT));
            }
            libraryManager.insertItemTiles(col, itemTileList);
        }
        assertTrue(libraryManager.isFull());
    }
}
