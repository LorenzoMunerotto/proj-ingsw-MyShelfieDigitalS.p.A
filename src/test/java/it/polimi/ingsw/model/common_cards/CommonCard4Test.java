package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.library_test.LibraryTestHelper;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard4;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard4Test {

    CommonGoalCard card4;
    LibraryTestHelper libraryTestHelper;
    ItemTile[][] libraryGrid;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card4 = new CommonCard4(4, points);
        libraryTestHelper = new LibraryTestHelper();
        libraryGrid = libraryTestHelper.getGrid();
    }

    @Test
    @DisplayName("Test check rules for common card 4")
    void checkRules() {
        assertFalse(card4.checkRules(libraryGrid));
        libraryTestHelper.setItemTile(5, 0, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(5, 1, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(4, 0, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(4, 1, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(3, 0, new ItemTile(ItemTileType.values()[1]));
        libraryTestHelper.setItemTile(3, 1, new ItemTile(ItemTileType.values()[1]));
        libraryTestHelper.setItemTile(2, 0, new ItemTile(ItemTileType.values()[1]));
        libraryTestHelper.setItemTile(2, 1, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card4.checkRules(libraryGrid));

        libraryTestHelper.setItemTile(5, 2, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(5, 3, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(4, 2, new ItemTile(ItemTileType.values()[0]));
        libraryTestHelper.setItemTile(4, 3, new ItemTile(ItemTileType.values()[0]));
        assertTrue(card4.checkRules(libraryGrid));

        libraryTestHelper.setItemTile(5, 2, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card4.checkRules(libraryGrid));
    }
}