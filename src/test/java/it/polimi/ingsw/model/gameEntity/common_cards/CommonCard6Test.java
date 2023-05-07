package it.polimi.ingsw.model.gameEntity.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard6Test {

    CommonGoalCard card6;
    LibraryTestHelper libraryTestHelper;

    Random random;

    @BeforeEach
    void setUp() {

        card6 = new CommonCard6();
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
            ItemTileType catItemTile = ItemTileType.CAT;
            while (libraryTestHelper.getItemTile(row,col) == ItemTileType.CAT || libraryTestHelper.getItemTile(row,col) != ItemTileType.EMPTY) {
                row = random.nextInt(6);
                col = random.nextInt(5);
            }
            libraryTestHelper.setItemTile(row, col, catItemTile);
        }
        for (int i = 0; i < 6; i++) {
            int row = random.nextInt(6);
            int col = random.nextInt(5);
            ItemTileType plantItemTile = ItemTileType.PLANT;
            while (libraryTestHelper.getItemTile(row,col) == ItemTileType.PLANT || libraryTestHelper.getItemTile(row,col) != ItemTileType.EMPTY) {
                row = random.nextInt(6);
                col = random.nextInt(5);
            }
            libraryTestHelper.setItemTile(row, col, plantItemTile);
        }
        assertTrue(card6.checkRules(libraryTestHelper));

        for(int row = 0; row < libraryTestHelper.getLibraryGrid().length; row++){
            for(int col = 0; col < libraryTestHelper.getLibraryGrid()[0].length; col++){
                if(libraryTestHelper.getItemTile(row,col) == ItemTileType.CAT){
                    libraryTestHelper.setItemTile(row, col, ItemTileType.EMPTY);
                    break;
                }
            }
        }
        assertFalse(card6.checkRules(libraryTestHelper));
    }
}