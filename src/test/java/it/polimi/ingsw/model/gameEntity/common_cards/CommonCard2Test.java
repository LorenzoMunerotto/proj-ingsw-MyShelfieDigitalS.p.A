package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard2Test {

    CommonGoalCard card2;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {

        card2 = new CommonCard2();
        libraryTestHelper = new LibraryTestHelper();

    }

    @Test
    @DisplayName("Test check rules for card 3 in horizontal case")
    void checkRulesHorizontal() {
        assertFalse(card2.checkRules(libraryTestHelper));
        for(int i = libraryTestHelper.getLibraryGrid().length - 1; i >= 2; i--){
            libraryTestHelper.setItemTile(i, 0, ItemTileType.values()[i]);
            libraryTestHelper.setItemTile(i, 1, ItemTileType.values()[i]);
            libraryTestHelper.setItemTile(i, 2, ItemTileType.values()[i]);
            libraryTestHelper.setItemTile(i, 3, ItemTileType.values()[i]);
        }
        assertTrue(card2.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 0, ItemTileType.values()[1]);
        assertFalse(card2.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 3 in vertical case")
    void checkRulesVertical() {
        assertFalse(card2.checkRules(libraryTestHelper));
        for(int i = 0; i < 4; i++){
            libraryTestHelper.setItemTile(5, i, ItemTileType.values()[i]);
            libraryTestHelper.setItemTile(4, i, ItemTileType.values()[i]);
            libraryTestHelper.setItemTile(3, i, ItemTileType.values()[i]);
            libraryTestHelper.setItemTile(2, i, ItemTileType.values()[i]);
        }
        assertTrue(card2.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 0, ItemTileType.values()[3]);
        assertFalse(card2.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 3 in square case")
    void checkRulesSquare() {
        assertFalse(card2.checkRules(libraryTestHelper));
        libraryTestHelper.setItemTile(5, 0, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(5, 1, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(4, 0, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(4, 1, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(3, 0, ItemTileType.values()[1]);
        libraryTestHelper.setItemTile(3, 1, ItemTileType.values()[1]);
        libraryTestHelper.setItemTile(2, 0, ItemTileType.values()[1]);
        libraryTestHelper.setItemTile(2, 1, ItemTileType.values()[1]);
        libraryTestHelper.setItemTile(5, 2, ItemTileType.values()[2]);
        libraryTestHelper.setItemTile(5, 3, ItemTileType.values()[2]);
        libraryTestHelper.setItemTile(4, 2, ItemTileType.values()[2]);
        libraryTestHelper.setItemTile(4, 3, ItemTileType.values()[2]);
        libraryTestHelper.setItemTile(3, 2, ItemTileType.values()[3]);
        libraryTestHelper.setItemTile(3, 3, ItemTileType.values()[3]);
        libraryTestHelper.setItemTile(2, 2, ItemTileType.values()[3]);
        libraryTestHelper.setItemTile(2, 3, ItemTileType.values()[3]);
        assertTrue(card2.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 0, ItemTileType.values()[2]);
        assertFalse(card2.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 3 in mixed case")
    void checkRulesMixed(){
        assertFalse(card2.checkRules(libraryTestHelper));
        for(int i = libraryTestHelper.getLibraryGrid().length - 1; i >= 4; i--){
            libraryTestHelper.setItemTile(i, 0, ItemTileType.values()[i]);
            libraryTestHelper.setItemTile(i, 1, ItemTileType.values()[i]);
            libraryTestHelper.setItemTile(i, 2, ItemTileType.values()[i]);
            libraryTestHelper.setItemTile(i, 3, ItemTileType.values()[i]);
        }
        for (int i = libraryTestHelper.getLibraryGrid().length - 1; i >= 2; i--) {
            libraryTestHelper.setItemTile(i, 4, ItemTileType.values()[3]);
        }
        libraryTestHelper.setItemTile(3, 0, ItemTileType.values()[2]);
        libraryTestHelper.setItemTile(3, 1, ItemTileType.values()[2]);
        libraryTestHelper.setItemTile(2, 0, ItemTileType.values()[2]);
        libraryTestHelper.setItemTile(2, 1, ItemTileType.values()[2]);
        assertTrue(card2.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 0, ItemTileType.values()[3]);
        assertFalse(card2.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for L-scheme")
    void checkRulesLScheme(){
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.GAME},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.PLANT,ItemTileType.TROPHY,ItemTileType.GAME},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT}};

        libraryTestHelper.setLibrary(gridOfItemTileType);
        assertTrue(card2.checkRules(libraryTestHelper));
        libraryTestHelper.setItemTile(1, 0, ItemTileType.FRAME);
        assertFalse(card2.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for T-scheme")
    void checkRulesTScheme(){
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.GAME,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.EMPTY},
                {ItemTileType.GAME,ItemTileType.GAME,ItemTileType.PLANT,ItemTileType.TROPHY,ItemTileType.EMPTY},
                {ItemTileType.GAME,ItemTileType.CAT,ItemTileType.TROPHY,ItemTileType.TROPHY,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.CAT,ItemTileType.TROPHY,ItemTileType.EMPTY}};

        libraryTestHelper.setLibrary(gridOfItemTileType);
        assertTrue(card2.checkRules(libraryTestHelper));
        libraryTestHelper.setItemTile(1, 2, ItemTileType.PLANT);
        assertFalse(card2.checkRules(libraryTestHelper));
    }
}