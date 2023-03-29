package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.common_cards.CommonCard5;
import it.polimi.ingsw.model.data.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard5Test {

    CommonGoalCard card5;
    GameData gameData;
    List<String> players;
    Library libraryP1;
    ItemTile[][] gridP1;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card5 = new CommonCard5(5, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary().getGrid();
    }

    @Test
    @DisplayName("Test check rules for card 5")
    void checkRules() {
        assertFalse(card5.checkRules(gameData, players.get(0)));

        for(int col = 0; col < 3; col++){
            for(int row = 0; row < gridP1.length; row++) {
                libraryP1.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card5.checkRules(gameData, players.get(0)));

        for (int col = 0; col < 3; col++) {
            libraryP1.setItemTile(3, col, new ItemTile(ItemTileType.PLANT));
        }
        assertTrue(card5.checkRules(gameData, players.get(0)));

        for (int col = 0; col < 3; col++) {
            libraryP1.setItemTile(4, col, new ItemTile(ItemTileType.TROPHY));
        }
        assertTrue(card5.checkRules(gameData, players.get(0)));

        for (int col = 0; col < 3; col++) {
            libraryP1.setItemTile(2, col, new ItemTile(ItemTileType.GAME));
        }
        assertFalse(card5.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(2, 2, new ItemTile(ItemTileType.CAT));
        for (int col = 0; col < 2; col++) {
            for (int row = 0; row < gridP1.length; row++) {
                libraryP1.setItemTile(row, col, new ItemTile(ItemTileType.EMPTY));
            }
        }
        assertFalse(card5.checkRules(gameData, players.get(0)));
    }

    @Test
    @DisplayName("Test check rules for card 5 with less than 6 tiles")
    void testCheckRulesLessThan6Tiles(){
        for(int col = 0; col < 3; col++){
            for(int row = 0; row < gridP1.length - 1; row++) {
                libraryP1.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertFalse(card5.checkRules(gameData, players.get(0)));
    }
}