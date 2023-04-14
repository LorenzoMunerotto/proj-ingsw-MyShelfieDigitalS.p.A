package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard1Test {

    CommonGoalCard card1;
    LibraryTestHelper libraryTestHelper;

    @BeforeEach
    void setUp() {

        card1 = new CommonCard1();
        libraryTestHelper = new LibraryTestHelper();

    }

    @Test
    @DisplayName("Test check rules for card 1")
    void checkRules() {
        assertFalse(card1.checkRules(libraryTestHelper));
        for(int i = libraryTestHelper.getROWS() - 1; i >= 3; i--){
            libraryTestHelper.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
        }
        for(int i = libraryTestHelper.getROWS() - 1; i >= 3; i--){
            libraryTestHelper.setItemTile(i, libraryTestHelper.getCOLUMNS() - 2, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, libraryTestHelper.getCOLUMNS() - 1, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card1.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(3, 4, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card1.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        assertFalse(card1.checkRules(libraryTestHelper));
    }

    @Test
    void checkRulesWithSetLibraryMethod(){
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.PLANT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.GAME},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertTrue(card1.checkRules(libraryTestHelper));

        libraryGrid = new ItemTileType[][]{
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.PLANT},
                {ItemTileType.CAT, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.GAME, ItemTileType.GAME},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertFalse(card1.checkRules(libraryTestHelper));

    }
    @Test
    @DisplayName("A group of 7 Cat count for 1 group")
    void checkSideCase(){
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.CAT, ItemTileType.EMPTY, ItemTileType.PLANT, ItemTileType.EMPTY},
                {ItemTileType.CAT, ItemTileType.CAT, ItemTileType.EMPTY, ItemTileType.PLANT, ItemTileType.PLANT},
                {ItemTileType.CAT, ItemTileType.CAT, ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.GAME},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertFalse(card1.checkRules(libraryTestHelper));



    }

    @Test
    @DisplayName("A group of 4 CAT in a specific disposition 'L'")
    void checkSideCase2(){
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.BOOK, ItemTileType.BOOK},
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.BOOK, ItemTileType.BOOK},
                {ItemTileType.CAT, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.TROPHY},
                {ItemTileType.CAT, ItemTileType.CAT, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.GAME},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertFalse(card1.checkRules(libraryTestHelper));

    }





    @Test
    @DisplayName("A group of 3 BOOK recognised as 1 group of 2 BOOK ")
    void checkSideCase4(){
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.PLANT, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.PLANT, ItemTileType.GAME, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.TROPHY, ItemTileType.TROPHY, ItemTileType.FRAME, ItemTileType.BOOK, ItemTileType.BOOK},
                {ItemTileType.CAT, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.FRAME, ItemTileType.BOOK},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertFalse(card1.checkRules(libraryTestHelper));

    }

}