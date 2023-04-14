package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.board.BoardTestHelper;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;
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
    BoardManager boardManager;

    @ParameterizedTest(name = "{displayName} - {index}")
    @CsvFileSource(resources = "/isRefillTimeTest.csv")
    void isRefillTimeTest(String boardAsString, Boolean isRefillTime){

        board = BoardTestHelper.newBoardFromString(boardAsString);
        bag = new Bag();
        boardManager = new BoardManager(board,bag);

        assertEquals(isRefillTime, boardManager.isRefillTime() );

        if(isRefillTime){
            boardManager.refillBoard();
            assertTrue(BoardTestHelper.checkAllNotEmpty(board));
        }
    }

    @ParameterizedTest(name = "{displayName} - {index}")
    @CsvFileSource(resources = "/isRefillTimeTest.csv")
    void refillBoardTest(String boardAsString, Boolean isRefillTime){

        if(isRefillTime){

            board = BoardTestHelper.newBoardFromString(boardAsString);
            Board oldBoard = BoardTestHelper.newBoardFromString(boardAsString);
            bag = new Bag();
            boardManager = new BoardManager(board,bag);

            boardManager.refillBoard();
            assertTrue(BoardTestHelper.checkAllNotEmpty(board));
            assertTrue(BoardTestHelper.checkAllTilesNotEmptyNotRefill(oldBoard, board));
        }
    }


    @Test
    @DisplayName("Test grab item tiles with both valid and invalid coordinates")
    public void testGrabItemTiles_Valid() {
        board = new Board(2);
        bag = new Bag();
        boardManager = new BoardManager(board, new Bag());
        boardManager.refillBoard();

        List<Pair<Integer, Integer>> coordinates = new ArrayList<>();
        coordinates.add(Pair.with(1, 3));
        coordinates.add(Pair.with(1, 4));
        coordinates.add(Pair.with(1, 5));
        assertThrows(IllegalArgumentException.class, () -> boardManager.grabItemTiles(coordinates));
        coordinates.remove(2);
        List<ItemTile> grabbedTiles = boardManager.grabItemTiles(coordinates);
        assertEquals(2, grabbedTiles.size());

        assertEquals(ItemTileType.EMPTY, board.getBoardCell(1, 3).getItemTile().getItemTileType());
        assertEquals(ItemTileType.EMPTY, board.getBoardCell(1, 4).getItemTile().getItemTileType());

        coordinates.clear();
        coordinates.add(Pair.with(0, 3));
        coordinates.add(Pair.with(0, 4));
        assertThrows(IllegalArgumentException.class, () -> boardManager.grabItemTiles(coordinates));
    }

    @Test
    @DisplayName("Test has side free with both valid and invalid coordinates")
    public void testHasSideFree(){
        board = new Board(2);
        bag = new Bag();
        boardManager = new BoardManager(board, new Bag());
        boardManager.refillBoard();

        assertTrue(boardManager.hasSideFree(1, 3));
        assertFalse(boardManager.hasSideFree(2, 4));
    }

    @Test
    @DisplayName("Test is lined with both valid and invalid coordinates")
    public void testIsLined(){
        board = new Board(2);
        bag = new Bag();
        boardManager = new BoardManager(board, new Bag());
        boardManager.refillBoard();

        List<Pair<Integer, Integer>> coordinates = new ArrayList<>();
        coordinates.add(Pair.with(1, 3));
        coordinates.add(Pair.with(1, 4));
        assertTrue(boardManager.isLined(coordinates));

        coordinates.remove(1);
        coordinates.add(Pair.with(2, 4));
        assertFalse(boardManager.isLined(coordinates));
    }
}
