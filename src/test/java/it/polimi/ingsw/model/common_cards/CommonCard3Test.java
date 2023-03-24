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
    GameData gameData;
    List<String> players;
    Library libraryP1;
    ItemTile[][] gridP1;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card3 = new CommonCard3(3, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = libraryP1.getGrid();
    }
    @Test
    @DisplayName("Test check rules for card 3 in horizontal case")
    void checkRulesHorizontal() {
        assertFalse(card3.checkRules(gameData, players.get(0)));
        for(int i = gridP1.length - 1; i >= 2; i--){
            libraryP1.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(i, 2, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(i, 3, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card3.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(2, 0, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card3.checkRules(gameData, players.get(0)));
    }

    @Test
    @DisplayName("Test check rules for card 3 in vertical case")
    void checkRulesVertical() {
        assertFalse(card3.checkRules(gameData, players.get(0)));
        for(int i = 0; i < 4; i++){
            libraryP1.setItemTile(5, i, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(4, i, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(3, i, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(2, i, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card3.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(2, 0, new ItemTile(ItemTileType.values()[3]));
        assertFalse(card3.checkRules(gameData, players.get(0)));
    }

    @Test
    @DisplayName("Test check rules for card 3 in square case")
    void checkRulesSquare() {
        assertFalse(card3.checkRules(gameData, players.get(0)));
        libraryP1.setItemTile(5, 0, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(5, 1, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(4, 0, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(4, 1, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(3, 0, new ItemTile(ItemTileType.values()[1]));
        libraryP1.setItemTile(3, 1, new ItemTile(ItemTileType.values()[1]));
        libraryP1.setItemTile(2, 0, new ItemTile(ItemTileType.values()[1]));
        libraryP1.setItemTile(2, 1, new ItemTile(ItemTileType.values()[1]));
        libraryP1.setItemTile(5, 2, new ItemTile(ItemTileType.values()[2]));
        libraryP1.setItemTile(5, 3, new ItemTile(ItemTileType.values()[2]));
        libraryP1.setItemTile(4, 2, new ItemTile(ItemTileType.values()[2]));
        libraryP1.setItemTile(4, 3, new ItemTile(ItemTileType.values()[2]));
        libraryP1.setItemTile(3, 2, new ItemTile(ItemTileType.values()[3]));
        libraryP1.setItemTile(3, 3, new ItemTile(ItemTileType.values()[3]));
        libraryP1.setItemTile(2, 2, new ItemTile(ItemTileType.values()[3]));
        libraryP1.setItemTile(2, 3, new ItemTile(ItemTileType.values()[3]));
        assertTrue(card3.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        assertFalse(card3.checkRules(gameData, players.get(0)));
    }

    @Test
    @DisplayName("Test check rules for card 3 in mixed case")
    void checkRulesMixed(){
        assertFalse(card3.checkRules(gameData, players.get(0)));
        for(int i = gridP1.length - 1; i >= 4; i--){
            libraryP1.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(i, 2, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(i, 3, new ItemTile(ItemTileType.values()[i]));
        }
        for (int i = gridP1.length - 1; i >= 2; i--) {
            libraryP1.setItemTile(i, 4, new ItemTile(ItemTileType.values()[3]));
        }
        libraryP1.setItemTile(3, 0, new ItemTile(ItemTileType.values()[2]));
        libraryP1.setItemTile(3, 1, new ItemTile(ItemTileType.values()[2]));
        libraryP1.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        libraryP1.setItemTile(2, 1, new ItemTile(ItemTileType.values()[2]));
        assertTrue(card3.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(2, 0, new ItemTile(ItemTileType.values()[3]));
        assertFalse(card3.checkRules(gameData, players.get(0)));
    }
}