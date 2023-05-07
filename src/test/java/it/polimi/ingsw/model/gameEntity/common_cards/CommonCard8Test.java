package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard8Test {

    CommonGoalCard card8;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {

        card8 = new CommonCard8();
        libraryTestHelper = new LibraryTestHelper();

    }

    @Test
    @DisplayName("Test check rules for card 8")
    void checkRules() {
        assertFalse(card8.checkRules( libraryTestHelper));

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                libraryTestHelper.setItemTile(row, col, ItemTileType.CAT);
            }
        }
        assertTrue(card8.checkRules(libraryTestHelper));

        for (int row = 0; row < 4; row++) {
            libraryTestHelper.setItemTile(row, 1, ItemTileType.PLANT);
        }
        assertTrue(card8.checkRules(libraryTestHelper));

        for (int row = 0; row < 4; row++) {
            libraryTestHelper.setItemTile(row, 2, ItemTileType.TROPHY);
        }
        assertTrue(card8.checkRules(libraryTestHelper));

        for (int row = 0; row < 4; row++) {
            libraryTestHelper.setItemTile(row, 3, ItemTileType.GAME);
        }
        assertFalse(card8.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 8 with less than 5 tiles")
    void testCheckRulesLessThan5Tiles(){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                libraryTestHelper.setItemTile(row, col, ItemTileType.CAT);
            }
        }
        assertFalse(card8.checkRules(libraryTestHelper));
    }
}