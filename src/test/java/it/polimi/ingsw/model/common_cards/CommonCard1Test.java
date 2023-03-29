package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.common_cards.CommonCard1;
import it.polimi.ingsw.model.data.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard1Test {

    CommonGoalCard card1;

    Library library;


    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card1 = new CommonCard1(1, points);
        library = new Library();
    }

    @Test
    @DisplayName("Test check rules for card 1")
    void checkRules() {
        assertFalse(card1.checkRules(library));
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.PLANT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.PLANT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.FRAME,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.FRAME,ItemTileType.TROPHY},
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.BOOK,ItemTileType.BOOK,ItemTileType.TROPHY}};

        library.setLibrary(gridOfItemTileType);
        assertTrue(card1.checkRules(library));
    }


    @Test
    @DisplayName("Invalid group - 3 cat")
    void checkRules1() {
        assertFalse(card1.checkRules(library));
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.PLANT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.PLANT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.FRAME,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.FRAME,ItemTileType.TROPHY},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.BOOK,ItemTileType.BOOK,ItemTileType.TROPHY}};

        library.setLibrary(gridOfItemTileType);
        assertFalse(card1.checkRules(library));
    }

    @Test
    @DisplayName("Only 5 groups")
    void checkRules2() {
        assertFalse(card1.checkRules(library));
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.PLANT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.PLANT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.FRAME,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.FRAME,ItemTileType.CAT},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.BOOK,ItemTileType.BOOK,ItemTileType.TROPHY}};

        library.setLibrary(gridOfItemTileType);
        assertFalse(card1.checkRules(library));
    }



}