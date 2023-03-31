package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.library_test.LibraryTestHelper;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard7;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard7Test {

    CommonGoalCard card7;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card7 = new CommonCard7(7, points);
        libraryTestHelper = new LibraryTestHelper();

    }

    @Test
    @DisplayName("Test check rules for card 7 from left")
    void checkRulesFromLeft() {
        assertFalse(card7.checkRules(libraryTestHelper));

        for (int i = 0; i < 5; i++) {
            libraryTestHelper.setItemTile(i, i, new ItemTile(ItemTileType.CAT));
        }
        assertTrue(card7.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(4, 4, new ItemTile(ItemTileType.PLANT));
        assertFalse(card7.checkRules(libraryTestHelper));

        for (int i = 1; i < 6; i++) {
            libraryTestHelper.setItemTile(i, i - 1, new ItemTile(ItemTileType.TROPHY));
        }
        assertTrue(card7.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(5, 4, new ItemTile(ItemTileType.PLANT));
        assertFalse(card7.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 7 from right")
    void checkRulesFromRight() {
        assertFalse(card7.checkRules(libraryTestHelper));
        for(int i = 0; i < 5; i++) {
            libraryTestHelper.setItemTile(i, 4 - i, new ItemTile(ItemTileType.CAT));
        }
        assertTrue(card7.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(4, 0, new ItemTile(ItemTileType.PLANT));
        assertFalse(card7.checkRules(libraryTestHelper));

        for (int i = 1; i < 6; i++) {
            libraryTestHelper.setItemTile(i, 5 - i, new ItemTile(ItemTileType.TROPHY));
        }
        assertTrue(card7.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(5, 0, new ItemTile(ItemTileType.PLANT));
        assertFalse(card7.checkRules(libraryTestHelper));
    }
}