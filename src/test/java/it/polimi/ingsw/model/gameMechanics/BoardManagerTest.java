package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.board.BoardTestHelper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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
}
