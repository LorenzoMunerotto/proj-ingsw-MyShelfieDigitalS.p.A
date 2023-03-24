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

class CommonCard1Test {

    CommonGoalCard card1;
    GameData gameData;
    List<String> players;
    Library libraryP1;
    ItemTile[][] gridP1;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card1 = new CommonCard1(1, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = libraryP1.getGrid();
    }

    @Test
    @DisplayName("Test check rules for card 1")
    void checkRules() {
        assertFalse(card1.checkRules(gameData, players.get(0)));
        for(int i = gridP1.length - 1; i >= 3; i--){
            libraryP1.setItemTile(i, 0, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(i, 1, new ItemTile(ItemTileType.values()[i]));
        }
        for(int i = gridP1.length - 1; i >= 3; i--){
            libraryP1.setItemTile(i, gridP1[i].length - 2, new ItemTile(ItemTileType.values()[i]));
            libraryP1.setItemTile(i, gridP1[i].length - 1, new ItemTile(ItemTileType.values()[i]));
        }
        assertTrue(card1.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(3, 4, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card1.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(2, 0, new ItemTile(ItemTileType.values()[2]));
        assertFalse(card1.checkRules(gameData, players.get(0)));
    }
}