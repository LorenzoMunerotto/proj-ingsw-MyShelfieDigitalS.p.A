package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.library_test.LibraryTestHelper;
import it.polimi.ingsw.model.data.ItemTile;
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
    LibraryTestHelper libraryTestHelper;

    Random random;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card6 = new CommonCard6(6, points);
        libraryTestHelper = new LibraryTestHelper();
        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 6")
    void checkRules() {
        assertFalse(card6.checkRules(libraryTestHelper));
        for (int i = 0; i < 8; i++) {
            int row = random.nextInt(6);
            int col = random.nextInt(5);
            ItemTile catItemTile = new ItemTile(ItemTileType.CAT);
            while (libraryTestHelper.getItemTile(row,col).getItemTileType() == ItemTileType.CAT || libraryTestHelper.getItemTile(row,col).getItemTileType() != ItemTileType.EMPTY) {
                row = random.nextInt(6);
                col = random.nextInt(5);
            }
            libraryTestHelper.setItemTile(row, col, catItemTile);
        }
        for (int i = 0; i < 6; i++) {
            int row = random.nextInt(6);
            int col = random.nextInt(5);
            ItemTile plantItemTile = new ItemTile(ItemTileType.PLANT);
            while (libraryTestHelper.getItemTile(row,col).getItemTileType() == ItemTileType.PLANT || libraryTestHelper.getItemTile(row,col).getItemTileType() != ItemTileType.EMPTY) {
                row = random.nextInt(6);
                col = random.nextInt(5);
            }
            libraryTestHelper.setItemTile(row, col, plantItemTile);
        }
        assertTrue(card6.checkRules(libraryTestHelper));

        for(int row = 0; row < libraryTestHelper.getROWS(); row++){
            for(int col = 0; col < libraryTestHelper.getCOLUMNS(); col++){
                if(libraryTestHelper.getItemTile(row,col).getItemTileType() == ItemTileType.CAT){
                    libraryTestHelper.setItemTile(row, col, new ItemTile(ItemTileType.EMPTY));
                    break;
                }
            }
        }
        assertFalse(card6.checkRules(libraryTestHelper));
    }
}