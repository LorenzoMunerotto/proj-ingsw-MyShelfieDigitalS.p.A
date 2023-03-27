package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.data.ItemTile;
import it.polimi.ingsw.model.data.Library;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import it.polimi.ingsw.model.logic.common_cards.CommonCard6;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard6Test {

    CommonGoalCard card6;
    Library library;
    ItemTile[][] libraryGrid;
    Random random;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card6 = new CommonCard6(6, points);
        library = new Library();
        libraryGrid = library.getGrid();
        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 6")
    void checkRules() {
        assertFalse(card6.checkRules(libraryGrid));
        for (int i = 0; i < 8; i++) {
            int row = random.nextInt(6);
            int column = random.nextInt(5);
            ItemTile catItemTile = new ItemTile(ItemTileType.CAT);
            while (libraryGrid[row][column].getItemTileType() == ItemTileType.CAT || libraryGrid[row][column].getItemTileType() != ItemTileType.EMPTY) {
                row = random.nextInt(6);
                column = random.nextInt(5);
            }
            library.setItemTile(row, column, catItemTile);
        }
        for (int i = 0; i < 6; i++) {
            int row = random.nextInt(6);
            int column = random.nextInt(5);
            ItemTile plantItemTile = new ItemTile(ItemTileType.PLANT);
            while (libraryGrid[row][column].getItemTileType() == ItemTileType.PLANT || libraryGrid[row][column].getItemTileType() != ItemTileType.EMPTY) {
                row = random.nextInt(6);
                column = random.nextInt(5);
            }
            library.setItemTile(row, column, plantItemTile);
        }
        assertTrue(card6.checkRules(libraryGrid));

        for(int i = 0; i < libraryGrid.length; i++){
            for(int j = 0; j < libraryGrid[0].length; j++){
                if(libraryGrid[i][j].getItemTileType() == ItemTileType.CAT){
                    library.setItemTile(i, j, new ItemTile(ItemTileType.EMPTY));
                    break;
                }
            }
        }
        assertFalse(card6.checkRules(libraryGrid));
    }
}