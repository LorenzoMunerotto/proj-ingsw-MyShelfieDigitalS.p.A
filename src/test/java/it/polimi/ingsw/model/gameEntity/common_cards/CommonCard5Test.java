package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard5Test {

    CommonGoalCard card5;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {

        card5 = new CommonCard5();
        libraryTestHelper = new LibraryTestHelper();

    }

    @Test
    @DisplayName("Test check rules for card 5")
    void checkRules() {
        assertFalse(card5.checkRules(libraryTestHelper));

        for(int col = 0; col < 3; col++){
            for(int row = 0; row < libraryTestHelper.getLibraryGrid().length; row++) {
                libraryTestHelper.setItemTile(row, col, ItemTileType.CAT);
            }
        }
        assertTrue(card5.checkRules(libraryTestHelper));

        for (int col = 0; col < 3; col++) {
            libraryTestHelper.setItemTile(3, col, ItemTileType.PLANT);
        }
        assertTrue(card5.checkRules(libraryTestHelper));

        for (int col = 0; col < 3; col++) {
            libraryTestHelper.setItemTile(4, col, ItemTileType.TROPHY);
        }
        assertTrue(card5.checkRules(libraryTestHelper));

        for (int col = 0; col < 3; col++) {
            libraryTestHelper.setItemTile(2, col, ItemTileType.GAME);
        }
        assertFalse(card5.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(2, 2, ItemTileType.CAT);
        for (int col = 0; col < 2; col++) {
            for (int row = 0; row < libraryTestHelper.getLibraryGrid().length; row++) {
                libraryTestHelper.setItemTile(row, col, ItemTileType.EMPTY);
            }
        }
        assertFalse(card5.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 5 with less than 6 tiles")
    void testCheckRulesLessThan6Tiles(){
        for(int col = 0; col < 3; col++){
            for(int row = 0; row < libraryTestHelper.getLibraryGrid().length - 1; row++) {
                libraryTestHelper.setItemTile(row, col, ItemTileType.CAT);
            }
        }
        assertFalse(card5.checkRules(libraryTestHelper));
    }
}