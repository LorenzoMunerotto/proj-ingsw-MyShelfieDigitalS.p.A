package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.board.BoardManagerTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardManagerTest {

    Board board;
    Bag bag;
    BoardManagerTestHelper boardManager;

    @ParameterizedTest(name = "{displayName} - {index}")
    @CsvFileSource(resources = "/isRefillTimeTest.csv")
    void isRefillTimeTest(String boardAsString, Boolean isRefillTime) {

        board = BoardManagerTestHelper.newBoardFromString(boardAsString);
        bag = new Bag();
        boardManager = new BoardManagerTestHelper(board, bag);
        boardManager.updateNotEmptyCells(board);

        assertEquals(isRefillTime, boardManager.isRefillTime());

        if (isRefillTime) {
            boardManager.refillBoard();
            assertEquals(0, boardManager.getEmptyCells().size());
        }
    }

    @ParameterizedTest(name = "{displayName} - {index}")
    @CsvFileSource(resources = "/isRefillTimeTest.csv")
    void refillBoardTest(String boardAsString, Boolean isRefillTime) {

        if (isRefillTime) {

            board = BoardManagerTestHelper.newBoardFromString(boardAsString);
            bag = new Bag();
            boardManager = new BoardManagerTestHelper(board, bag);

            boardManager.refillBoard();
            assertEquals(0, boardManager.getEmptyCells().size());
            assertNotEquals(0, boardManager.getNotEmptyCells().size());
        }
    }


    @Test
    @DisplayName("Test grab item tiles with both valid and invalid coordinates")
    public void testGrabItemTiles_Valid() {
        board = new Board(2);
        bag = new Bag();
        boardManager = new BoardManagerTestHelper(board, new Bag());
        boardManager.refillBoard();

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 3));
        coordinates.add(new Coordinate(1, 4));
        coordinates.add(new Coordinate(1, 5));
        coordinates.remove(2);
        List<ItemTileType> grabbedTiles = boardManager.grabItemTiles(coordinates);
        assertEquals(2, grabbedTiles.size());

        assertEquals(ItemTileType.EMPTY, board.getItemTile(1, 3));
        assertEquals(ItemTileType.EMPTY, board.getItemTile(1, 4));

        coordinates.clear();
        coordinates.add(new Coordinate(0, 3));
        coordinates.add(new Coordinate(0, 4));
    }

    @Test
    @DisplayName("Test has side free with both valid and invalid coordinates")
    public void testHasSideFree() {
        board = new Board(2);
        bag = new Bag();
        boardManager = new BoardManagerTestHelper(board, new Bag());
        boardManager.refillBoard();

        assertTrue(boardManager.hasSideFree(1, 3));
        assertFalse(boardManager.hasSideFree(2, 4));
    }

    @Test
    @DisplayName("Test is lined with both valid and invalid coordinates")
    public void testIsLined() {
        board = new Board(2);
        bag = new Bag();
        boardManager = new BoardManagerTestHelper(board, new Bag());
        boardManager.refillBoard();

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 3));
        coordinates.add(new Coordinate(1, 4));
        assertTrue(boardManager.isLined(coordinates));

        coordinates.remove(1);
        coordinates.add(new Coordinate(2, 4));
        assertFalse(boardManager.isLined(coordinates));
    }
}
