package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonCard11Test {

    CommonGoalCard card11;
    LibraryTestHelper libraryTestHelper;
    Random random;

    @BeforeEach
    void setUp() {

        card11 = new CommonCard11();
        libraryTestHelper = new LibraryTestHelper();

        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 11")
    void checkRules() {
        assertFalse(card11.checkRules(libraryTestHelper));

        int row = random.nextInt(libraryTestHelper.getLibraryGrid().length - 2);
        int col = random.nextInt(libraryTestHelper.getLibraryGrid()[0].length - 2);
        libraryTestHelper.setItemTile(row, col, ItemTileType.CAT);
        libraryTestHelper.setItemTile(row, col + 2, ItemTileType.CAT);
        libraryTestHelper.setItemTile(row + 1, col + 1, ItemTileType.CAT);
        libraryTestHelper.setItemTile(row + 2, col, ItemTileType.CAT);
        libraryTestHelper.setItemTile(row + 2, col + 2, ItemTileType.CAT);
        assertTrue(card11.checkRules(libraryTestHelper));

        ItemTileType newType = ItemTileType.PLANT;
        libraryTestHelper.setItemTile(row, col, newType);
        assertFalse(card11.checkRules(libraryTestHelper));
    }
}