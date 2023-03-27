package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard12;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard12Test {

    CommonGoalCard card12;
    Library library;
    ItemTile[][] libraryGrid;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card12 = new CommonCard12(12, points);
        library = new Library();
        libraryGrid = library.getGrid();
    }
    @Test
    @DisplayName("Test check rules for card 12 in descending order")
    void checkRulesDescending() {
        assertFalse(card12.checkRules(libraryGrid));
        for (int row = 5; row >= 1; row--) {
            library.setItemTile(row, 0, new ItemTile(ItemTileType.CAT));
        }
        for (int col = 1; col < 5; col++) {
            for (int row = 5; row >= 1 + col; row--) {
                library.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card12.checkRules(libraryGrid));

        library.setItemTile(0, 0, new ItemTile(ItemTileType.CAT));
        assertFalse(card12.checkRules(libraryGrid));

        library.setItemTile(0, 0, new ItemTile(ItemTileType.EMPTY));
        library.setItemTile(1, 0, new ItemTile(ItemTileType.EMPTY));
        assertFalse(card12.checkRules(libraryGrid));
    }

    @Test
    @DisplayName("Test check rules for card 12 in ascending order")
    void checkRulesAscending() {
        assertFalse(card12.checkRules(libraryGrid));
        for (int col = 0; col < 5; col++) {
            for (int row = 5 - col; row < 6; row++) {
                library.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card12.checkRules(libraryGrid));

        library.setItemTile(0, 4, new ItemTile(ItemTileType.CAT));
        assertFalse(card12.checkRules(libraryGrid));

        library.setItemTile(0, 4, new ItemTile(ItemTileType.EMPTY));
        library.setItemTile(1, 4, new ItemTile(ItemTileType.EMPTY));
        assertFalse(card12.checkRules(libraryGrid));
    }
}