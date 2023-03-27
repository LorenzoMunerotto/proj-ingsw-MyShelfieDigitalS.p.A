package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard10;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard10Test {

    CommonGoalCard card10;
    Library library;
    ItemTile[][] libraryGrid;
    Random random;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card10 = new CommonCard10(10, points);
        library = new Library();
        libraryGrid = library.getGrid();
        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 10")
    void checkRules() {
        assertFalse(card10.checkRules(libraryGrid));

        int firstRow = random.nextInt(libraryGrid.length);
        int secondRow;
        do {
            secondRow = random.nextInt(libraryGrid.length);
        } while (secondRow == firstRow);
        for (int col = 0; col < libraryGrid[0].length; col++) {
            library.setItemTile(firstRow, col, new ItemTile(ItemTileType.values()[col]));
            library.setItemTile(secondRow, col, new ItemTile(ItemTileType.values()[col]));
        }
        assertTrue(card10.checkRules(libraryGrid));

        int columnToChange = random.nextInt(libraryGrid[0].length);
        library.setItemTile(firstRow, columnToChange, new ItemTile(ItemTileType.EMPTY));
        assertFalse(card10.checkRules(libraryGrid));
    }
}