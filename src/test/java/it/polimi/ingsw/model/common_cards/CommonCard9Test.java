package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.library_test.LibraryTestHelper;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard9;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard9Test {

    CommonGoalCard card9;
    LibraryTestHelper libraryTestHelper;
    ItemTile[][] libraryGrid;
    Random random;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card9 = new CommonCard9(9, points);
        libraryTestHelper = new LibraryTestHelper();
        libraryGrid = libraryTestHelper.getGrid();
        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 9")
    void checkRules() {
        assertFalse(card9.checkRules(libraryGrid));

        int firstColumn = random.nextInt(libraryGrid[0].length);
        int secondColumn;
        do {
            secondColumn = random.nextInt(libraryGrid[0].length);
        } while (secondColumn == firstColumn);

        for (int row = 0; row < libraryGrid.length; row++) {
            libraryTestHelper.setItemTile(row, firstColumn, new ItemTile(ItemTileType.values()[row]));
            libraryTestHelper.setItemTile(row, secondColumn, new ItemTile(ItemTileType.values()[row]));
        }
        assertTrue(card9.checkRules(libraryGrid));

        int rowToChange = random.nextInt(libraryGrid.length);
        ItemTileType newType = libraryGrid[rowToChange][firstColumn].getItemTileType() == ItemTileType.CAT ? ItemTileType.PLANT : ItemTileType.CAT;
        libraryTestHelper.setItemTile(rowToChange, firstColumn, new ItemTile(newType));
        assertFalse(card9.checkRules(libraryGrid));
    }
}