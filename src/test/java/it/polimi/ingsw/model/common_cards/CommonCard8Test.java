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

class CommonCard8Test {

    CommonGoalCard card8;
    GameData gameData;
    List<String> players;
    Library libraryP1;
    ItemTile[][] gridP1;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card8 = new CommonCard8(8, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary().getGrid();
    }

    @Test
    @DisplayName("Test check rules for card 8")
    void checkRules() {
        assertFalse(card8.checkRules(gameData, players.get(0)));

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                libraryP1.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card8.checkRules(gameData, players.get(0)));

        for (int row = 0; row < 4; row++) {
            libraryP1.setItemTile(row, 1, new ItemTile(ItemTileType.PLANT));
        }
        assertTrue(card8.checkRules(gameData, players.get(0)));

        for (int row = 0; row < 4; row++) {
            libraryP1.setItemTile(row, 2, new ItemTile(ItemTileType.TROPHY));
        }
        assertTrue(card8.checkRules(gameData, players.get(0)));

        for (int row = 0; row < 4; row++) {
            libraryP1.setItemTile(row, 3, new ItemTile(ItemTileType.GAME));
        }
        assertFalse(card8.checkRules(gameData, players.get(0)));
    }

    @Test
    @DisplayName("Test check rules for card 8 with less than 5 tiles")
    void testCheckRulesLessThan5Tiles(){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                libraryP1.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertFalse(card8.checkRules(gameData, players.get(0)));
    }
}