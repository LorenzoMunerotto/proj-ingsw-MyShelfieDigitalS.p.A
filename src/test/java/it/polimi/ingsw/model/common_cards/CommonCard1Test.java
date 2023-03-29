package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.library_test.LibraryTestHelper;
import it.polimi.ingsw.model.data.ItemTile;
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
    LibraryTestHelper libraryTestHelper;
    ItemTile[][] libraryGrid;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card1 = new CommonCard1(1, points);
        libraryTestHelper = new LibraryTestHelper();
        libraryGrid = libraryTestHelper.getGrid();
    }

    @Test
    @DisplayName("Test check rules for card 1")
    void checkRules() {
        assertFalse(card1.checkRules(libraryGrid));
        for(int i = libraryGrid.length - 1; i >= 3; i--){
            libraryTestHelper.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
        }
        for(int i = libraryGrid.length - 1; i >= 3; i--){
            libraryTestHelper.setItemTile(i, libraryGrid[i].length - 2, new ItemTile(ItemTileType.values()[i]));
            libraryTestHelper.setItemTile(i, libraryGrid[i].length - 1, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card1.checkRules(libraryGrid));

        libraryTestHelper.setItemTile(3, 4, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card1.checkRules(libraryGrid));

        libraryTestHelper.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        assertFalse(card1.checkRules(libraryGrid));
    }

    @Test
    void checkRulesWithSetLibraryMethod(){
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.PLANT},
                {ItemTileType.CAT, ItemTileType.CAT, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.GAME},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertTrue(card1.checkRules(libraryTestHelper.getGrid()));

        libraryGrid = new ItemTileType[][]{
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.CAT, ItemTileType.PLANT, ItemTileType.PLANT},
                {ItemTileType.CAT, ItemTileType.CAT, ItemTileType.GAME, ItemTileType.GAME, ItemTileType.GAME},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertFalse(card1.checkRules(libraryTestHelper.getGrid()));

    }
}