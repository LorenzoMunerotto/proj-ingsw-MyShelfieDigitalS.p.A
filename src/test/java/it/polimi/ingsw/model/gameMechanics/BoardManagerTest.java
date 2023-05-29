package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.board.BoardManagerTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.exceptions.EmptyBagException;
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
    void isRefillTimeTest(String boardAsString, Boolean isRefillTime) throws EmptyBagException {

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
    void refillBoardTest(String boardAsString, Boolean isRefillTime) throws EmptyBagException {

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
    public void testGrabItemTiles_Valid() throws EmptyBagException {
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
    public void testHasSideFree() throws EmptyBagException {
        board = new Board(2);
        bag = new Bag();
        boardManager = new BoardManagerTestHelper(board, new Bag());
        boardManager.refillBoard();

        assertTrue(boardManager.hasSideFree(1, 3));
        assertFalse(boardManager.hasSideFree(2, 4));
    }

    @Test
    @DisplayName("Test is lined with both valid and invalid coordinates")
    public void testIsLined() throws EmptyBagException {
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

    @Test
    @DisplayName("Test isAlone with both valid and invalid coordinates")
    public void testIsAlone() {
        board = new Board(2);
        bag = new Bag();
        boardManager = new BoardManagerTestHelper(board, new Bag());

        board.setItemTile(1, 3, ItemTileType.CAT);
        board.setItemTile(2, 5, ItemTileType.CAT);

        assertTrue(boardManager.isAlone(1, 3));
        assertTrue(boardManager.isAlone(2, 5));

        board.setItemTile(1, 4, ItemTileType.PLANT);
        assertFalse(boardManager.isAlone(1, 3));
    }

    @Test
    @DisplayName("Test calculateCRC to ensure no modification on the board")
    public void testCalculateCRC() throws EmptyBagException {
        board = new Board(2);
        bag = new Bag();
        boardManager = new BoardManagerTestHelper(board, new Bag());
        boardManager.refillBoard();

        long previousCRC = boardManager.calculateCRC();
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 3));
        coordinates.add(new Coordinate(1, 4));
        boardManager.grabItemTiles(coordinates);
        long currentCRC = boardManager.calculateCRC();

        assertNotEquals(previousCRC, currentCRC);
    }

    @Test
    @DisplayName("Test that the empty bag exception is thrown when the bag is empty")
    public void testEmptyBagException() throws EmptyBagException {
        board = new Board(4);
        bag = new Bag();
        boardManager = new BoardManagerTestHelper(board, new Bag());

        for (int counter = 0; counter < 2; counter++) {
            boardManager.refillBoard();
            for (int row = 0; row < board.getBoardGrid().length; row++) {
                for (int column = 0; column < board.getBoardGrid()[row].length; column++) {
                    if (board.getItemTile(row, column) == ItemTileType.NULL) {
                        continue;
                    }
                    if (board.getItemTile(row, column) != ItemTileType.EMPTY) {
                        board.setItemTile(row, column, ItemTileType.EMPTY);
                        boardManager.getEmptyCells().add(new Coordinate(row, column));
                        boardManager.getNotEmptyCells().remove(new Coordinate(row, column));
                    }
                }
            }
        }
        assertThrows(EmptyBagException.class, () -> boardManager.refillBoard());
    }

    @Test
    @DisplayName("Test that the empty cell list and the not empty cell list are updated correctly")
    public void testCellList() throws EmptyBagException {
        board = new Board(4);
        bag = new Bag();
        boardManager = new BoardManagerTestHelper(board, new Bag());
        boardManager.refillBoard();

        assertEquals(0, boardManager.getEmptyCells().size());
        assertEquals(45, boardManager.getNotEmptyCells().size());

        for (int row = 0; row < board.getBoardGrid().length; row++) {
            for (int column = 0; column < board.getBoardGrid()[row].length; column++) {
                if (board.getItemTile(row, column) == ItemTileType.NULL) {
                    continue;
                }
                if (board.getItemTile(row, column) != ItemTileType.EMPTY) {
                    board.setItemTile(row, column, ItemTileType.EMPTY);
                    boardManager.getEmptyCells().add(new Coordinate(row, column));
                    boardManager.getNotEmptyCells().remove(new Coordinate(row, column));
                }
            }
        }

        assertEquals(45, boardManager.getEmptyCells().size());
        assertEquals(0, boardManager.getNotEmptyCells().size());

        boardManager.refillBoard();

        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 3));
        coordinates.add(new Coordinate(1, 4));
        boardManager.grabItemTiles(coordinates);

        assertEquals(2, boardManager.getEmptyCells().size());
        assertEquals(43, boardManager.getNotEmptyCells().size());
    }
}