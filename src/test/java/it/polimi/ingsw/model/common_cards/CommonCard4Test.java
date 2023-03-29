package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.common_cards.CommonCard4;
import it.polimi.ingsw.model.data.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard4Test {

    CommonGoalCard card4;
    GameData gameData;
    List<String> players;
    Library libraryP1;
    ItemTile[][] gridP1;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card4 = new CommonCard4(4, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = libraryP1.getGrid();
    }

    @Test
    @DisplayName("Test check rules for common card 4")
    void checkRules() {
        assertFalse(card4.checkRules(gameData, players.get(0)));
        libraryP1.setItemTile(5, 0, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(5, 1, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(4, 0, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(4, 1, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(3, 0, new ItemTile(ItemTileType.values()[1]));
        libraryP1.setItemTile(3, 1, new ItemTile(ItemTileType.values()[1]));
        libraryP1.setItemTile(2, 0, new ItemTile(ItemTileType.values()[1]));
        libraryP1.setItemTile(2, 1, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card4.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(5, 2, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(5, 3, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(4, 2, new ItemTile(ItemTileType.values()[0]));
        libraryP1.setItemTile(4, 3, new ItemTile(ItemTileType.values()[0]));
        assertTrue(card4.checkRules(gameData, players.get(0)));

        libraryP1.setItemTile(5, 2, new ItemTile(ItemTileType.values()[1]));
        assertFalse(card4.checkRules(gameData, players.get(0)));
    }
}