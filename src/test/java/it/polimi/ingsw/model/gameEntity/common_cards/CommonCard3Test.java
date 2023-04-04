package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonCard3;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard3Test {

    CommonGoalCard card3;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {

        card3 = new CommonCard3();
        libraryTestHelper = new LibraryTestHelper();

    }
    @Test
    @DisplayName("Test check rules for card 3 in horizontal case")
    void checkRulesHorizontal() {
        assertFalse(card3.checkRules(libraryTestHelper));
        for(int i = libraryTestHelper.getROWS() - 1; i >= 2; i--){
            libraryTestHelper.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, 2, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, 3, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card3.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 0, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card3.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 3 in vertical case")
    void checkRulesVertical() {
        assertFalse(card3.checkRules(libraryTestHelper));
        for(int i = 0; i < 4; i++){
            libraryTestHelper.setItemTile(5, i, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(4, i, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(3, i, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(2, i, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card3.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 0, new ItemTile(ItemTileType.values()[3]));
        assertFalse(card3.checkRules(libraryTestHelper));
    }



    @Test
    @DisplayName("Test check rules for card 3 in square case")
    void checkRulesSquare() {
        assertFalse(card3.checkRules(libraryTestHelper));
        libraryTestHelper.setItemTile(5, 0, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(5, 1, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(4, 0, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(4, 1, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(3, 0, new ItemTile(ItemTileType.values()[1]));
        libraryTestHelper.setItemTile(3, 1, new ItemTile(ItemTileType.values()[1]));
        libraryTestHelper.setItemTile(2, 0, new ItemTile(ItemTileType.values()[1]));
        libraryTestHelper.setItemTile(2, 1, new ItemTile(ItemTileType.values()[1]));
        libraryTestHelper.setItemTile(5, 2, new ItemTile(ItemTileType.values()[2]));
        libraryTestHelper.setItemTile(5, 3, new ItemTile(ItemTileType.values()[2]));
        libraryTestHelper.setItemTile(4, 2, new ItemTile(ItemTileType.values()[2]));
        libraryTestHelper.setItemTile(4, 3, new ItemTile(ItemTileType.values()[2]));
        libraryTestHelper.setItemTile(3, 2, new ItemTile(ItemTileType.values()[3]));
        libraryTestHelper.setItemTile(3, 3, new ItemTile(ItemTileType.values()[3]));
        libraryTestHelper.setItemTile(2, 2, new ItemTile(ItemTileType.values()[3]));
        libraryTestHelper.setItemTile(2, 3, new ItemTile(ItemTileType.values()[3]));
        assertTrue(card3.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        assertFalse(card3.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 3 in mixed case")
    void checkRulesMixed(){
        assertFalse(card3.checkRules(libraryTestHelper));
        for(int i = libraryTestHelper.getROWS() - 1; i >= 4; i--){
            libraryTestHelper.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, 2, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, 3, new ItemTile(ItemTileType.values()[i]));
        }
        for (int i = libraryTestHelper.getROWS() - 1; i >= 2; i--) {
            libraryTestHelper.setItemTile(i, 4, new ItemTile(ItemTileType.values()[3]));
        }
        libraryTestHelper.setItemTile(3, 0, new ItemTile(ItemTileType.values()[2]));
        libraryTestHelper.setItemTile(3, 1, new ItemTile(ItemTileType.values()[2]));
        libraryTestHelper.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        libraryTestHelper.setItemTile(2, 1, new ItemTile(ItemTileType.values()[2]));
        assertTrue(card3.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 0, new ItemTile(ItemTileType.values()[3]));
        assertFalse(card3.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for L-scheme")
    void checkRulesLscheme(){
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.GAME},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.PLANT,ItemTileType.TROPHY,ItemTileType.GAME},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT}};

        libraryTestHelper.setLibrary(gridOfItemTileType);
        assertTrue(card3.checkRules(libraryTestHelper));
        libraryTestHelper.setItemTile(1, 0, new ItemTile(ItemTileType.FRAME));
        assertFalse(card3.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for T-scheme")
    void checkRulesTscheme(){
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.GAME,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.EMPTY},
                {ItemTileType.GAME,ItemTileType.GAME,ItemTileType.PLANT,ItemTileType.TROPHY,ItemTileType.EMPTY},
                {ItemTileType.GAME,ItemTileType.CAT,ItemTileType.TROPHY,ItemTileType.TROPHY,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.CAT,ItemTileType.TROPHY,ItemTileType.EMPTY}};

        libraryTestHelper.setLibrary(gridOfItemTileType);
        assertTrue(card3.checkRules(libraryTestHelper));
        libraryTestHelper.setItemTile(1, 2, new ItemTile(ItemTileType.PLANT));
        assertFalse(card3.checkRules(libraryTestHelper));
    }


}