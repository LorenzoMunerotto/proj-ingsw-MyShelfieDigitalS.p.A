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
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.CAT},
                {ItemTileType.GAME, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.CAT, ItemTileType.CAT},
                {ItemTileType.GAME, ItemTileType.GAME, ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.TROPHY},
                {ItemTileType.PLANT, ItemTileType.PLANT, ItemTileType.BOOK, ItemTileType.TROPHY, ItemTileType.PLANT},
                {ItemTileType.PLANT, ItemTileType.PLANT, ItemTileType.BOOK, ItemTileType.CAT, ItemTileType.CAT},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertTrue(card4.checkRules(libraryTestHelper));

        libraryTestHelper.setItemTile(5, 1, ItemTileType.GAME);
        assertFalse(card4.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for overlapping items horizontally")
    void checkRulesHorizontally() {
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.BOOK, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.CAT, ItemTileType.CAT},
                {ItemTileType.EMPTY, ItemTileType.TROPHY, ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.CAT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.GAME, ItemTileType.TROPHY},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.TROPHY, ItemTileType.PLANT},
                {ItemTileType.PLANT, ItemTileType.GAME, ItemTileType.BOOK, ItemTileType.CAT, ItemTileType.CAT},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertFalse(card4.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for overlapping items vertically")
    void checkRulesVertically() {
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.BOOK, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.FRAME, ItemTileType.CAT, ItemTileType.CAT, ItemTileType.CAT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.TROPHY, ItemTileType.EMPTY, ItemTileType.CAT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.TROPHY},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.TROPHY, ItemTileType.PLANT},
                {ItemTileType.PLANT, ItemTileType.GAME, ItemTileType.BOOK, ItemTileType.CAT, ItemTileType.CAT},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertFalse(card4.checkRules(libraryTestHelper));
    }

    @Test
    @DisplayName("Test check rules for overlapping items crosswise")
    void checkRulesCrosswise() {
        ItemTileType[][] libraryGrid = {
                {ItemTileType.EMPTY, ItemTileType.GAME, ItemTileType.BOOK, ItemTileType.EMPTY, ItemTileType.EMPTY},
                {ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.EMPTY, ItemTileType.CAT, ItemTileType.CAT},
                {ItemTileType.EMPTY, ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.EMPTY, ItemTileType.CAT},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.GAME, ItemTileType.TROPHY},
                {ItemTileType.FRAME, ItemTileType.FRAME, ItemTileType.BOOK, ItemTileType.TROPHY, ItemTileType.PLANT},
                {ItemTileType.PLANT, ItemTileType.GAME, ItemTileType.BOOK, ItemTileType.CAT, ItemTileType.CAT},
        };
        libraryTestHelper.setLibrary(libraryGrid);
        assertFalse(card4.checkRules(libraryTestHelper));
    }
}