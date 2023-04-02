package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameMechanics.common_cards.CommonCard8;
import it.polimi.ingsw.model.gameMechanics.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard8Test {

    CommonGoalCard card8;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card8 = new CommonCard8(8, points);
        libraryTestHelper = new LibraryTestHelper();

    }

    @Test
    @DisplayName("Test check rules for card 8")
    void checkRules() {
        assertFalse(card8.checkRules( libraryTestHelper));

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                libraryTestHelper.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card8.checkRules(libraryTestHelper));

        for (int row = 0; row < 4; row++) {
            libraryTestHelper.setItemTile(row, 1, new ItemTile(ItemTileType.PLANT));
        }
        assertTrue(card8.checkRules(libraryTestHelper));

        for (int row = 0; row < 4; row++) {
            libraryTestHelper.setItemTile(row, 2, new ItemTile(ItemTileType.TROPHY));
        }
        assertTrue(card8.checkRules(libraryTestHelper));

        for (int row = 0; row < 4; row++) {
            libraryTestHelper.setItemTile(row, 3, new ItemTile(ItemTileType.GAME));
        }
        assertFalse(card8.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 8 with less than 5 tiles")
    void testCheckRulesLessThan5Tiles(){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                libraryTestHelper.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertFalse(card8.checkRules(libraryTestHelper));
    }
}