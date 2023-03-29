package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.library_test.LibraryTestHelper;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard11;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonCard11Test {

    CommonGoalCard card11;
    LibraryTestHelper libraryTestHelper;
    ItemTile[][] libraryGrid;
    Random random;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card11 = new CommonCard11(11, points);
        libraryTestHelper = new LibraryTestHelper();
        libraryGrid = libraryTestHelper.getGrid();
        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 11")
    void checkRules() {
        assertFalse(card11.checkRules(libraryGrid));

        int row = random.nextInt(libraryGrid.length - 2);
        int col = random.nextInt(libraryGrid[0].length - 2);
        libraryTestHelper.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
        libraryTestHelper.setItemTile(row, col + 2, new ItemTile(ItemTileType.CAT));
        libraryTestHelper.setItemTile(row + 1, col + 1, new ItemTile(ItemTileType.CAT));
        libraryTestHelper.setItemTile(row + 2, col, new ItemTile(ItemTileType.CAT));
        libraryTestHelper.setItemTile(row + 2, col + 2, new ItemTile(ItemTileType.CAT));
        assertTrue(card11.checkRules(libraryGrid));

        ItemTileType newType = ItemTileType.PLANT;
        libraryTestHelper.setItemTile(row, col, new ItemTile(newType));
        assertFalse(card11.checkRules(libraryGrid));
    }
}