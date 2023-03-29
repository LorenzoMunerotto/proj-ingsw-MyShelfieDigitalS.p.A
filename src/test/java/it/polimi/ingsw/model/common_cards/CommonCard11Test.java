package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.common_cards.CommonCard11;
import it.polimi.ingsw.model.data.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("Test check rules for card 11")
    void checkRules() {
        assertFalse(card11.checkRules(gameData, players.get(0)));

        int row = random.nextInt(gridP1.length - 2);
        int col = random.nextInt(gridP1[0].length - 2);
        libraryP1.setItemTile(row, col, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(row, col + 2, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(row + 1, col + 1, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(row + 2, col, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(row + 2, col + 2, new ItemTile(ItemTileType.CAT));
        assertTrue(card11.checkRules(gameData, players.get(0)));

        ItemTileType newType = ItemTileType.PLANT;
        libraryP1.setItemTile(row, col, new ItemTile(newType));
        assertFalse(card11.checkRules(gameData, players.get(0)));
    }
}