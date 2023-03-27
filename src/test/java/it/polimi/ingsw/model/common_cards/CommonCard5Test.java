package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard5;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard5Test {

    CommonGoalCard card5;
    Library library;
    ItemTile[][] libraryGrid;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card5 = new CommonCard5(5, points);
        library = new Library();
        libraryGrid = library.getGrid();
    }

    @Test
    @DisplayName("Test check rules for card 5")
    void checkRules() {
        assertFalse(card5.checkRules(libraryGrid));

        for(int col = 0; col < 3; col++){
            for(int row = 0; row < libraryGrid.length; row++) {
                library.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card5.checkRules(libraryGrid));

        for (int col = 0; col < 3; col++) {
            library.setItemTile(3, col, new ItemTile(ItemTileType.PLANT));
        }
        assertTrue(card5.checkRules(libraryGrid));

        for (int col = 0; col < 3; col++) {
            library.setItemTile(4, col, new ItemTile(ItemTileType.TROPHY));
        }
        assertTrue(card5.checkRules(libraryGrid));

        for (int col = 0; col < 3; col++) {
            library.setItemTile(2, col, new ItemTile(ItemTileType.GAME));
        }
        assertFalse(card5.checkRules(libraryGrid));

        library.setItemTile(2, 2, new ItemTile(ItemTileType.CAT));
        for (int col = 0; col < 2; col++) {
            for (int row = 0; row < libraryGrid.length; row++) {
                library.setItemTile(row, col, new ItemTile(ItemTileType.EMPTY));
            }
        }
        assertFalse(card5.checkRules(libraryGrid));
    }

    @Test
    @DisplayName("Test check rules for card 5 with less than 6 tiles")
    void testCheckRulesLessThan6Tiles(){
        for(int col = 0; col < 3; col++){
            for(int row = 0; row < libraryGrid.length - 1; row++) {
                library.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertFalse(card5.checkRules(libraryGrid));
    }
}