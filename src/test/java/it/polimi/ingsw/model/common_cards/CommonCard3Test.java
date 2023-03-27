package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard3;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard3Test {

    CommonGoalCard card3;
    Library library;
    ItemTile[][] libraryGrid;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card3 = new CommonCard3(3, points);
        library = new Library();
        libraryGrid = library.getGrid();
    }
    @Test
    @DisplayName("Test check rules for card 3 in horizontal case")
    void checkRulesHorizontal() {
        assertFalse(card3.checkRules(libraryGrid));
        for(int i = libraryGrid.length - 1; i >= 2; i--){
            library.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(i, 2, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(i, 3, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card3.checkRules(libraryGrid));

        library.setItemTile(2, 0, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card3.checkRules(libraryGrid));
    }

    @Test
    @DisplayName("Test check rules for card 3 in vertical case")
    void checkRulesVertical() {
        assertFalse(card3.checkRules(libraryGrid));
        for(int i = 0; i < 4; i++){
            library.setItemTile(5, i, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(4, i, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(3, i, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(2, i, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card3.checkRules(libraryGrid));

        library.setItemTile(2, 0, new ItemTile(ItemTileType.values()[3]));
        assertFalse(card3.checkRules(libraryGrid));
    }

    @Test
    @DisplayName("Test check rules for card 3 in square case")
    void checkRulesSquare() {
        assertFalse(card3.checkRules(libraryGrid));
        library.setItemTile(5, 0, new ItemTile(ItemTileType.values()[0]));
        library.setItemTile(5, 1, new ItemTile(ItemTileType.values()[0]));
        library.setItemTile(4, 0, new ItemTile(ItemTileType.values()[0]));
        library.setItemTile(4, 1, new ItemTile(ItemTileType.values()[0]));
        library.setItemTile(3, 0, new ItemTile(ItemTileType.values()[1]));
        library.setItemTile(3, 1, new ItemTile(ItemTileType.values()[1]));
        library.setItemTile(2, 0, new ItemTile(ItemTileType.values()[1]));
        library.setItemTile(2, 1, new ItemTile(ItemTileType.values()[1]));
        library.setItemTile(5, 2, new ItemTile(ItemTileType.values()[2]));
        library.setItemTile(5, 3, new ItemTile(ItemTileType.values()[2]));
        library.setItemTile(4, 2, new ItemTile(ItemTileType.values()[2]));
        library.setItemTile(4, 3, new ItemTile(ItemTileType.values()[2]));
        library.setItemTile(3, 2, new ItemTile(ItemTileType.values()[3]));
        library.setItemTile(3, 3, new ItemTile(ItemTileType.values()[3]));
        library.setItemTile(2, 2, new ItemTile(ItemTileType.values()[3]));
        library.setItemTile(2, 3, new ItemTile(ItemTileType.values()[3]));
        assertTrue(card3.checkRules(libraryGrid));

        library.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        assertFalse(card3.checkRules(libraryGrid));
    }

    @Test
    @DisplayName("Test check rules for card 3 in mixed case")
    void checkRulesMixed(){
        assertFalse(card3.checkRules(libraryGrid));
        for(int i = libraryGrid.length - 1; i >= 4; i--){
            library.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(i, 2, new ItemTile(ItemTileType.values()[i]));
            library.setItemTile(i, 3, new ItemTile(ItemTileType.values()[i]));
        }
        for (int i = libraryGrid.length - 1; i >= 2; i--) {
            library.setItemTile(i, 4, new ItemTile(ItemTileType.values()[3]));
        }
        library.setItemTile(3, 0, new ItemTile(ItemTileType.values()[2]));
        library.setItemTile(3, 1, new ItemTile(ItemTileType.values()[2]));
        library.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        library.setItemTile(2, 1, new ItemTile(ItemTileType.values()[2]));
        assertTrue(card3.checkRules(libraryGrid));

        library.setItemTile(2, 0, new ItemTile(ItemTileType.values()[3]));
        assertFalse(card3.checkRules(libraryGrid));
    }
}