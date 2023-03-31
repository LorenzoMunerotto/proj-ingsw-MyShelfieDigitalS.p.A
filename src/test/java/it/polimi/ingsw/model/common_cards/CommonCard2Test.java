package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.library_test.LibraryTestHelper;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard2;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard2Test {

    CommonGoalCard card2;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card2 = new CommonCard2(2, points);
        libraryTestHelper = new LibraryTestHelper();

    }

    @Test
    @DisplayName("Test check rules for card 2")
    void checkRulesTrue() {
        libraryTestHelper.setItemTile(0,0, new ItemTile(ItemTileType.CAT));
        libraryTestHelper.setItemTile(0,4, new ItemTile(ItemTileType.CAT));
        libraryTestHelper.setItemTile(5,0, new ItemTile(ItemTileType.CAT));
        libraryTestHelper.setItemTile(5,4, new ItemTile(ItemTileType.CAT));
        assertTrue(card2.checkRules(libraryTestHelper));
        libraryTestHelper.setItemTile(5,4,new ItemTile(ItemTileType.EMPTY));
        assertFalse(card2.checkRules(libraryTestHelper));
    }


}