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

class CommonCard10Test {

    CommonGoalCard card10;
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
        card10 = new CommonCard10(10, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary().getGrid();
        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 10")
    void checkRules() {
        assertFalse(card10.checkRules(gameData, players.get(0)));

        int firstRow = random.nextInt(gridP1.length);
        int secondRow;
        do {
            secondRow = random.nextInt(gridP1.length);
        } while (secondRow == firstRow);
        for (int col = 0; col < gridP1[0].length; col++) {
            libraryP1.setItemTile(firstRow, col, new ItemTile(ItemTileType.values()[col]));
            libraryP1.setItemTile(secondRow, col, new ItemTile(ItemTileType.values()[col]));
        }
        assertTrue(card10.checkRules(gameData, players.get(0)));

        int columnToChange = random.nextInt(gridP1[0].length);
        libraryP1.setItemTile(firstRow, columnToChange, new ItemTile(ItemTileType.EMPTY));
        assertFalse(card10.checkRules(gameData, players.get(0)));
    }
}