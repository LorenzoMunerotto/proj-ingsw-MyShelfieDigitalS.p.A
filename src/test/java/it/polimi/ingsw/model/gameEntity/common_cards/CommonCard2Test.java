package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonCard2;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard2Test {

    CommonGoalCard card2;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {

        card2 = new CommonCard2();
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