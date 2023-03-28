package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.Library;
import it.polimi.ingsw.model.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard3Test {

    CommonGoalCard card3;
    Library library;


    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card3 = new CommonCard3(3, points);
        library = new Library();
    }
    @Test
    @DisplayName("Test check rules for card 3 in horizontal case")
    void checkRulesHorizontal() {
        assertFalse(card3.checkRules(library));

        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.GAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.EMPTY},
                {ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.EMPTY},
                {ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.CAT,ItemTileType.CAT,ItemTileType.EMPTY}};

        library.setLibrary(gridOfItemTileType);
        assertTrue(card3.checkRules(library));

        library.setItemTile(5, 0, new ItemTile(ItemTileType.TROPHY));
        assertFalse(card3.checkRules(library));
    }

    @Test
    @DisplayName("Test check rules for card 3 in vertical case")
    void checkRulesVertical() {
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.PLANT,ItemTileType.GAME,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.PLANT,ItemTileType.GAME,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.PLANT,ItemTileType.GAME,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.PLANT,ItemTileType.GAME,ItemTileType.EMPTY}};

        library.setLibrary(gridOfItemTileType);
        assertTrue(card3.checkRules(library));

        library.setItemTile(5, 0, new ItemTile(ItemTileType.TROPHY));
        assertFalse(card3.checkRules(library));

    }



    @Test
    @DisplayName("Test check rules for card 3 in square case")
    void checkRulesSquare() {
          ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.EMPTY},
                {ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.EMPTY}};

        library.setLibrary(gridOfItemTileType);
        assertTrue(card3.checkRules(library));

        library.setItemTile(5, 0, new ItemTile(ItemTileType.TROPHY));
        assertFalse(card3.checkRules(library));
    }

    @Test
    @DisplayName("Test check rules for card 3 in mixed case")
    void checkRulesMixed(){
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.GAME},
                {ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.GAME},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.GAME},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.GAME}};

        library.setLibrary(gridOfItemTileType);
        assertTrue(card3.checkRules(library));

        library.setItemTile(5, 0, new ItemTile(ItemTileType.TROPHY));
        assertFalse(card3.checkRules(library));

    }

    @Test
    @DisplayName("Test check rules for L-scheme")
    void checkRulesLscheme(){
        ItemTileType[][] gridOfItemTileType = {
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.FRAME,ItemTileType.FRAME,ItemTileType.EMPTY,ItemTileType.EMPTY,ItemTileType.EMPTY},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.GAME,ItemTileType.GAME,ItemTileType.GAME},
                {ItemTileType.CAT,ItemTileType.FRAME,ItemTileType.PLANT,ItemTileType.TROPHY,ItemTileType.GAME},
                {ItemTileType.CAT,ItemTileType.CAT,ItemTileType.PLANT,ItemTileType.PLANT,ItemTileType.PLANT}};

        library.setLibrary(gridOfItemTileType);
        assertTrue(card3.checkRules(library));


    }



}