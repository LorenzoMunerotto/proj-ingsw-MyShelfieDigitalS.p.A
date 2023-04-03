package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.library.LibraryTestHelper;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameMechanics.common_cards.CommonCard10;
import it.polimi.ingsw.model.gameMechanics.common_cards.CommonGoalCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonCard10Test {

    CommonGoalCard card10;
    LibraryTestHelper libraryTestHelper;

    Random random;

    @BeforeEach
    void setUp() {
        List<Integer> points = new ArrayList<>();
        points.add(8);
        points.add(6);
        card10 = new CommonCard10(10, points);
        libraryTestHelper = new LibraryTestHelper();

        random = new Random();
    }

    @Test
    @DisplayName("Test check rules for card 10")
    void checkRules() {
        assertFalse(card10.checkRules(libraryTestHelper));

        int firstRow = random.nextInt(libraryTestHelper.getROWS());
        int secondRow;
        do {
            secondRow = random.nextInt(libraryTestHelper.getROWS());
        } while (secondRow == firstRow);
        for (int col = 0; col < libraryTestHelper.getCOLUMNS(); col++) {
            libraryTestHelper.setItemTile(firstRow, col, new ItemTile(ItemTileType.values()[col]));
            libraryTestHelper.setItemTile(secondRow, col, new ItemTile(ItemTileType.values()[col]));
        }
        assertTrue(card10.checkRules(libraryTestHelper));

        int columnToChange = random.nextInt(libraryTestHelper.getCOLUMNS());
        libraryTestHelper.setItemTile(firstRow, columnToChange, new ItemTile(ItemTileType.EMPTY));
        assertFalse(card10.checkRules(libraryTestHelper));
    }
}