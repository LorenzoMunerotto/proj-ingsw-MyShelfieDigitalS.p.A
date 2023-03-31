package it.polimi.ingsw.model;

import it.polimi.ingsw.model.data.GameData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class GameDataTest {

    GameData gameData;
    List<String> players;
    @Test
    public void testConstructor(){
        players = Arrays.asList("Pluto", "Pippo", "Paperino");
        gameData = new GameData(players, 3);
        assertNotNull(gameData);

        assertNotEquals(gameData.getPlayerDashboards().get(players.get(0)), gameData.getPlayerDashboards().get(players.get(1)));
    }

}