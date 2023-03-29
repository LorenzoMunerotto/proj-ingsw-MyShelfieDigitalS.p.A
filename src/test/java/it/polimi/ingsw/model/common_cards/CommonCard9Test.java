package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.logic.GameData;
import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.common_cards.CommonCard9;
import it.polimi.ingsw.model.data.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard9Test {

    CommonGoalCard card9;
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
        card9 = new CommonCard9(9, points);
        players = new ArrayList<>();
        players.add("Pippo");
        players.add("Pluto");
        gameData = new GameData(players, 2);
        libraryP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary();
        gridP1 = gameData.getPlayerDashboards().get(players.get(0)).getLibrary().getGrid();
        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 9")
    void checkRules() {
        assertFalse(card9.checkRules(gameData, players.get(0)));

        int firstColumn = random.nextInt(gridP1[0].length);
        int secondColumn;
        do {
            secondColumn = random.nextInt(gridP1[0].length);
        } while (secondColumn == firstColumn);

        for (int row = 0; row < gridP1.length; row++) {
            libraryP1.setItemTile(row, firstColumn, new ItemTile(ItemTileType.values()[row]));
            libraryP1.setItemTile(row, secondColumn, new ItemTile(ItemTileType.values()[row]));
        }
        assertTrue(card9.checkRules(gameData, players.get(0)));

        int rowToChange = random.nextInt(gridP1.length);
        ItemTileType newType = gridP1[rowToChange][firstColumn].getItemTileType() == ItemTileType.CAT ? ItemTileType.PLANT : ItemTileType.CAT;
        libraryP1.setItemTile(rowToChange, firstColumn, new ItemTile(newType));
        assertFalse(card9.checkRules(gameData, players.get(0)));
    }
}