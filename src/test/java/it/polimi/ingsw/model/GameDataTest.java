package it.polimi.ingsw.model;

import it.polimi.ingsw.model.logic.GameData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameDataTest {

    GameData gameData;
    List<String> players;
    @Test
    public void testConstructor(){
        players = new ArrayList<>();
        players.add("Pluto");
        players.add("Pippo");
        players.add("Paperino");
        gameData = new GameData(players, 3);
    }

}