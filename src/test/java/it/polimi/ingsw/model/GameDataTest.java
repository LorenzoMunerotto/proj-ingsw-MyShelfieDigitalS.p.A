package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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