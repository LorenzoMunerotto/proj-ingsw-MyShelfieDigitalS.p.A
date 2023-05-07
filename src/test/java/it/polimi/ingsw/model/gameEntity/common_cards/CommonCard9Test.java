package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard9Test {

    CommonGoalCard card9;
    LibraryTestHelper libraryTestHelper;

    Random random;

    @BeforeEach
    void setUp() {

        card9 = new CommonCard9();
        libraryTestHelper = new LibraryTestHelper();

        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 9")
    void checkRules() {
        assertFalse(card9.checkRules(libraryTestHelper));

        int firstColumn = random.nextInt(libraryTestHelper.getLibraryGrid()[0].length);
        int secondColumn;
        do {
            secondColumn = random.nextInt(libraryTestHelper.getLibraryGrid()[0].length);
        } while (secondColumn == firstColumn);

        for (int row = 0; row < libraryTestHelper.getLibraryGrid().length; row++) {
            libraryTestHelper.setItemTile(row, firstColumn, ItemTileType.values()[row]);
            libraryTestHelper.setItemTile(row, secondColumn, ItemTileType.values()[row]);
        }
        assertTrue(card9.checkRules(libraryTestHelper));

        int rowToChange = random.nextInt(libraryTestHelper.getLibraryGrid().length);
        ItemTileType newType = libraryTestHelper.getItemTile(rowToChange,firstColumn) == ItemTileType.CAT ? ItemTileType.PLANT : ItemTileType.CAT;
        libraryTestHelper.setItemTile(rowToChange, firstColumn, newType);
        assertFalse(card9.checkRules(libraryTestHelper));
    }
}