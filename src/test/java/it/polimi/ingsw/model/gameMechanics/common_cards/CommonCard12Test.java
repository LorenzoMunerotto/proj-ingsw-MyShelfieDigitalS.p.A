package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameMechanics.common_cards.CommonCard12;
import it.polimi.ingsw.model.gameMechanics.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard12Test {

    CommonGoalCard card12;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {

        card12 = new CommonCard12();
        libraryTestHelper = new LibraryTestHelper();

    }
    @Test
    @DisplayName("Test check rules for card 12 in descending order")
    void checkRulesDescending() {
        assertFalse(card12.checkRules(libraryTestHelper));
        for (int row = 5; row >= 1; row--) {
            libraryTestHelper.setItemTile(row, 0, new ItemTile(ItemTileType.CAT));
        }
        for (int col = 1; col < 5; col++) {
            for (int row = 5; row >= 1 + col; row--) {
                libraryTestHelper.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card12.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(0, 0, new ItemTile(ItemTileType.CAT));
        assertFalse(card12.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(0, 0, new ItemTile(ItemTileType.EMPTY));
        libraryTestHelper.setItemTile(1, 0, new ItemTile(ItemTileType.EMPTY));
        assertFalse(card12.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for card 12 in ascending order")
    void checkRulesAscending() {
        assertFalse(card12.checkRules(libraryTestHelper));
        for (int col = 0; col < 5; col++) {
            for (int row = 5 - col; row < 6; row++) {
                libraryTestHelper.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card12.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(0, 4, new ItemTile(ItemTileType.CAT));
        assertFalse(card12.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(0, 4, new ItemTile(ItemTileType.EMPTY));
        libraryTestHelper.setItemTile(1, 4, new ItemTile(ItemTileType.EMPTY));
        assertFalse(card12.checkRules(libraryTestHelper));
    }
}