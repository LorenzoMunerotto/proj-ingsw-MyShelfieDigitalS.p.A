package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.common_cards.CommonCard6;
import it.polimi.ingsw.model.data.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard6Test {

    CommonGoalCard card6;
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
        card6 = new CommonCard6(6, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = libraryP1.getGrid();
        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 6")
    void checkRules() {
        assertFalse(card6.checkRules(gameData, players.get(0)));
        for (int i = 0; i < 8; i++) {
            int row = random.nextInt(6);
            int column = random.nextInt(5);
            ItemTile catItemTile = new ItemTile(ItemTileType.CAT);
            while (gridP1[row][column].getItemTileType() == ItemTileType.CAT || gridP1[row][column].getItemTileType() != ItemTileType.EMPTY) {
                row = random.nextInt(6);
                column = random.nextInt(5);
            }
            libraryP1.setItemTile(row, column, catItemTile);
        }
        for (int i = 0; i < 6; i++) {
            int row = random.nextInt(6);
            int column = random.nextInt(5);
            ItemTile plantItemTile = new ItemTile(ItemTileType.PLANT);
            while (gridP1[row][column].getItemTileType() == ItemTileType.PLANT || gridP1[row][column].getItemTileType() != ItemTileType.EMPTY) {
                row = random.nextInt(6);
                column = random.nextInt(5);
            }
            libraryP1.setItemTile(row, column, plantItemTile);
        }
        assertTrue(card6.checkRules(gameData, players.get(0)));

        for(int i = 0; i < gridP1.length; i++){
            for(int j = 0; j < gridP1[0].length; j++){
                if(gridP1[i][j].getItemTileType() == ItemTileType.CAT){
                    libraryP1.setItemTile(i, j, new ItemTile(ItemTileType.EMPTY));
                    break;
                }
            }
        }
        assertFalse(card6.checkRules(gameData, players.get(0)));
    }
}