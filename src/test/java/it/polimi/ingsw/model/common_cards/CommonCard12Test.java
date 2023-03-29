package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.common_cards.CommonCard12;
import it.polimi.ingsw.model.data.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard12Test {

    CommonGoalCard card12;
    GameData gameData;
    List<String> players;
    Library libraryP1;
    ItemTile[][] gridP1;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card12 = new CommonCard12(12, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary().getGrid();
    }
    @Test
    @DisplayName("Test check rules for card 12 in descending order")
    void checkRulesDescending() {
        assertFalse(card12.checkRules(gameData, players.get(0)));
        for (int row = 5; row >= 1; row--) {
            libraryP1.setItemTile(row, 0, new ItemTile(ItemTileType.CAT));
        }
        for (int col = 1; col < 5; col++) {
            for (int row = 5; row >= 1 + col; row--) {
                libraryP1.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card12.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(0, 0, new ItemTile(ItemTileType.CAT));
        assertFalse(card12.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(0, 0, new ItemTile(ItemTileType.EMPTY));
        libraryP1.setItemTile(1, 0, new ItemTile(ItemTileType.EMPTY));
        assertFalse(card12.checkRules(gameData, players.get(0)));
    }

    @Test
    @DisplayName("Test check rules for card 12 in ascending order")
    void checkRulesAscending() {
        assertFalse(card12.checkRules(gameData, players.get(0)));
        for (int col = 0; col < 5; col++) {
            for (int row = 5 - col; row < 6; row++) {
                libraryP1.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
            }
        }
        assertTrue(card12.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(0, 4, new ItemTile(ItemTileType.CAT));
        assertFalse(card12.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(0, 4, new ItemTile(ItemTileType.EMPTY));
        libraryP1.setItemTile(1, 4, new ItemTile(ItemTileType.EMPTY));
        assertFalse(card12.checkRules(gameData, players.get(0)));
    }
}