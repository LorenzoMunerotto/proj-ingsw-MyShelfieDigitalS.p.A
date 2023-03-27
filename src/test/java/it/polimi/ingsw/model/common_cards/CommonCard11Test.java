package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
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
    Library library;
    ItemTile[][] libraryGrid;
    Random random;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card11 = new CommonCard11(11, points);
        library = new Library();
        libraryGrid = library.getGrid();
        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 11")
    void checkRules() {
        assertFalse(card11.checkRules(libraryGrid));

        int row = random.nextInt(libraryGrid.length - 2);
        int col = random.nextInt(libraryGrid[0].length - 2);
        library.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
        library.setItemTile(row, col + 2, new ItemTile(ItemTileType.CAT));
        library.setItemTile(row + 1, col + 1, new ItemTile(ItemTileType.CAT));
        library.setItemTile(row + 2, col, new ItemTile(ItemTileType.CAT));
        library.setItemTile(row + 2, col + 2, new ItemTile(ItemTileType.CAT));
        assertTrue(card11.checkRules(libraryGrid));

        ItemTileType newType = ItemTileType.PLANT;
        library.setItemTile(row, col, new ItemTile(newType));
        assertFalse(card11.checkRules(libraryGrid));
    }
}