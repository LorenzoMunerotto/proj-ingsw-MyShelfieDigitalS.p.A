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

class CommonCard2Test {

    CommonGoalCard card2;
    GameData gameData;
    List<String> players;
    Library libraryP1;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card2 = new CommonCard2(2, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
    }

    @Test
    @DisplayName("Test check rules for card 2")
    void checkRulesTrue() {
        libraryP1.setItemTile(0,0, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(0,4, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(5,0, new ItemTile(ItemTileType.CAT));
        libraryP1.setItemTile(5,4, new ItemTile(ItemTileType.CAT));
        assertTrue(card2.checkRules(gameData, players.get(0)));
        libraryP1.setItemTile(5,4,new ItemTile(ItemTileType.EMPTY));
        assertFalse(card2.checkRules(gameData, players.get(0)));
    }


}