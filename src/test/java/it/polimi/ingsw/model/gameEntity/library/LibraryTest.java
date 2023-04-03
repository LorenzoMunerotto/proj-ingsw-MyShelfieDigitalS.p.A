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


    /*
    @Test
    @DisplayName("Adjacent Groups with Empty Library")
    void testAdjacent0(){
        List<Pair<ItemTileType,Integer>> listGroupsAdjacentTiles = library.getListGroupsAdjacentTiles();

        assertEquals(1, listGroupsAdjacentTiles.size());
        assertEquals(ItemTileType.EMPTY, listGroupsAdjacentTiles.get(0).getValue0());
        assertEquals(30, listGroupsAdjacentTiles.get(0).getValue1());

    }


    @Test
    @DisplayName("Adjacent Groups 1")
    void testAdjacent1(){

        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.GAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.EMPTY},
                {ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.EMPTY},
                {ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.CAT,ItemTileType.CAT,ItemTileType.EMPTY}};

        library.setLibrary(gridOfItemTileType);
        List<Pair<ItemTileType,Integer>> listGroupsAdjacentTiles = library.getListGroupsAdjacentTiles();

        assertEquals(5, listGroupsAdjacentTiles.size());
        assertEquals(ItemTileType.EMPTY, listGroupsAdjacentTiles.get(0).getValue0());
        assertEquals(14, listGroupsAdjacentTiles.get(0).getValue1());
        assertEquals(ItemTileType.GAME, listGroupsAdjacentTiles.get(1).getValue0());
        assertEquals(4, listGroupsAdjacentTiles.get(1).getValue1());
        assertEquals(ItemTileType.PLANT, listGroupsAdjacentTiles.get(2).getValue0());
        assertEquals(4, listGroupsAdjacentTiles.get(2).getValue1());
        assertEquals(ItemTileType.FRAME, listGroupsAdjacentTiles.get(3).getValue0());
        assertEquals(4, listGroupsAdjacentTiles.get(3).getValue1());
        assertEquals(ItemTileType.CAT, listGroupsAdjacentTiles.get(4).getValue0());
        assertEquals(4, listGroupsAdjacentTiles.get(4).getValue1());

    }




    @Test
    @DisplayName("Adjacent Groups 2")
    void testAdjacent2(){

        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.BOOK,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.CAT,ItemTileType.EMPTY},
                {ItemTileType.FRAME,ItemTileType.BOOK,ItemTileType.FRAME,ItemTileType.BOOK,ItemTileType.EMPTY},
                {ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.EMPTY},
                {ItemTileType.TROPHY,ItemTileType.TROPHY,ItemTileType.CAT,ItemTileType.CAT,ItemTileType.CAT},
                {ItemTileType.TROPHY,ItemTileType.TROPHY,ItemTileType.TROPHY,ItemTileType.CAT,ItemTileType.CAT}};

        library.setLibrary(gridOfItemTileType);
        List<Pair<ItemTileType,Integer>> listGroupsAdjacentTiles = library.getListGroupsAdjacentTiles();

        assertEquals(13, listGroupsAdjacentTiles.size());
        assertTrue(listGroupsAdjacentTiles.contains(new Pair<>(ItemTileType.TROPHY,6)));
        assertTrue(listGroupsAdjacentTiles.contains(new Pair<>(ItemTileType.CAT,5)));
        assertTrue(listGroupsAdjacentTiles.contains(new Pair<>(ItemTileType.CAT,1)));
        assertTrue(listGroupsAdjacentTiles.contains(new Pair<>(ItemTileType.BOOK,1)));
        assertTrue(listGroupsAdjacentTiles.contains(new Pair<>(ItemTileType.PLANT,5)));
        assertTrue(listGroupsAdjacentTiles.contains(new Pair<>(ItemTileType.TROPHY,1)));
    }

    @Test
    @DisplayName("Adjacent Groups 3 - @")
    void testAdjacent3(){

        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.TROPHY,ItemTileType.TROPHY,ItemTileType.TROPHY,ItemTileType.TROPHY,ItemTileType.TROPHY},
                {ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.TROPHY},
                {ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.TROPHY},
                {ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.TROPHY},
                {ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.TROPHY},
                {ItemTileType.TROPHY,ItemTileType.GAME,ItemTileType.TROPHY,ItemTileType.TROPHY,ItemTileType.TROPHY}};

        library.setLibrary(gridOfItemTileType);
        List<Pair<ItemTileType,Integer>> listGroupsAdjacentTiles = library.getListGroupsAdjacentTiles();

        assertEquals(2, listGroupsAdjacentTiles.size());
        assertTrue(listGroupsAdjacentTiles.contains(new Pair<>(ItemTileType.TROPHY,20)));
        assertTrue(listGroupsAdjacentTiles.contains(new Pair<>(ItemTileType.GAME,10)));

    }


     */

}

