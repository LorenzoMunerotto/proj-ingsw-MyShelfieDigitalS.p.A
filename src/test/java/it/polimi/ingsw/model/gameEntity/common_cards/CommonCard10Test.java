package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard10Test {

    CommonGoalCard card10;
    LibraryTestHelper libraryTestHelper;

    Random random;

    @BeforeEach
    void setUp() {

        card10 = new CommonCard10();
        libraryTestHelper = new LibraryTestHelper();

        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 10")
    void checkRules() {
        assertFalse(card10.checkRules(libraryTestHelper));

        int firstRow = random.nextInt(libraryTestHelper.getLibraryGrid().length);
        int secondRow;
        do {
            secondRow = random.nextInt(libraryTestHelper.getLibraryGrid().length);
        } while (secondRow == firstRow);
        for (int col = 0; col < libraryTestHelper.getLibraryGrid()[0].length; col++) {
            libraryTestHelper.setItemTile(firstRow, col, ItemTileType.values()[col]);
            libraryTestHelper.setItemTile(secondRow, col, ItemTileType.values()[col]);
        }
        assertTrue(card10.checkRules(libraryTestHelper));

        int columnToChange = random.nextInt(libraryTestHelper.getLibraryGrid()[0].length);
        libraryTestHelper.setItemTile(firstRow, columnToChange, ItemTileType.EMPTY);
        assertFalse(card10.checkRules(libraryTestHelper));
    }
}