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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard11Test {

    CommonGoalCard card11;
    GameData gameData;
    List<String> players;
    Library libraryP1;
    ItemTile[][] gridP1;
    Random random;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card11 = new CommonCard11(11, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary().getGrid();
        random = new Random();
    }

    @Test
    @DisplayName("Test checkRules method")
    void checkRules() {
        libraryP1.setItemTile(0, 0, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(0, 2, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(1, 1, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(2, 0, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(2, 2, new ItemTile(ItemTileType.CAT));
        assertTrue(card11.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(1, 1, new ItemTile(ItemTileType.PLANT));
        assertFalse(card11.checkRules(gameData, players.get(0)));
    }
}