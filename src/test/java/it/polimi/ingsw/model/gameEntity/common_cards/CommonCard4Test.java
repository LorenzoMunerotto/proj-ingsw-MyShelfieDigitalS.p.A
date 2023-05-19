package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard4Test {

    CommonGoalCard card4;
    LibraryTestHelper libraryTestHelper;


    @BeforeEach
    void setUp() {

        card4 = new CommonCard4();
        libraryTestHelper = new LibraryTestHelper();

    }

    @Test
    @DisplayName("Test check rules for common card 4")
    void checkRules() {
        assertFalse(card4.checkRules(libraryTestHelper));
        libraryTestHelper.setItemTile(5, 0, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(5, 1, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(4, 0, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(4, 1, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(3, 0, ItemTileType.values()[1]);
        libraryTestHelper.setItemTile(3, 1, ItemTileType.values()[1]);
        libraryTestHelper.setItemTile(2, 0, ItemTileType.values()[1]);
        libraryTestHelper.setItemTile(2, 1, ItemTileType.values()[1]);
        assertFalse(card4.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(5, 2, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(5, 3, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(4, 2, ItemTileType.values()[0]);
        libraryTestHelper.setItemTile(4, 3, ItemTileType.values()[0]);
        assertTrue(card4.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(5, 2, ItemTileType.values()[1]);
        assertFalse(card4.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("check rules n.2")
    void checkRules2() {
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.BOOK, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.CAT, ItemTileType.CAT},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.TROPHY, ItemTileType.CAT, ItemTileType.CAT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.BOOK, ItemTileType.GAME, ItemTileType.TROPHY},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.BOOK, ItemTileType.TROPHY, ItemTileType.PLANT},
                {ItemTileType.PLANT, ItemTileType.GAME, ItemTileType.BOOK, ItemTileType.CAT, ItemTileType.CAT},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertTrue(card4.checkRules(libraryTestHelper));


    }
}