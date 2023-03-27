package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard1;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard1Test {

    CommonGoalCard card1;
    Library library;
    ItemTile[][] libraryGrid;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card1 = new CommonCard1(1, points);
        library = new Library();
        libraryGrid = library.getGrid();
    }

    @Test
    @DisplayName("Test check rules for card 1")
    void checkRules() {
        assertFalse(card1.checkRules(libraryGrid));
        for(int i = libraryGrid.length - 1; i >= 3; i--){
            library.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
        }
        for(int i = libraryGrid.length - 1; i >= 3; i--){
            library.setItemTile(i, libraryGrid[i].length - 2, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(i, libraryGrid[i].length - 1, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card1.checkRules(libraryGrid));

        library.setItemTile(3, 4, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card1.checkRules(libraryGrid));

        library.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        assertFalse(card1.checkRules(libraryGrid));
    }
}