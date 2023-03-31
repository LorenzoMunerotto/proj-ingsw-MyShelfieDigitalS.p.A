package it.polimi.ingsw.model;

import it.polimi.ingsw.model.data.Bag;
import it.polimi.ingsw.model.data.Board;
import it.polimi.ingsw.model.data.enums.ItemTileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    Board board;
    Bag bag;

    @Test
    @DisplayName("Test the constructor for a 2 players game")
    public void testCreateBoardForTwoPlayers() {
        board = new Board(2);
        int counterEmpty = 0;
        for(int i = 0; i < board.getBoardGrid().length; i++){
            for(int j = 0; j < board.getBoardGrid().length; j++){
                if(board.getBoardGrid()[i][j] != null && board.getBoardGrid()[i][j].getItemTile().getItemTileType() == ItemTileType.EMPTY){
                    counterEmpty++;
                }
            }
        }
        int finalCounterEmpty = counterEmpty;
        assertAll(
                () -> assertEquals(board.getEmptyCells(), finalCounterEmpty),
                () -> assertNull(board.getBoardGrid()[0][3]),
                () -> assertNull(board.getBoardGrid()[0][4]),
                () -> assertEquals(0, board.getValidCells().size())
        );
    }

    @Test
    @DisplayName("Test the constructor for a 3 players game")
    public void testCreateBoardForThreePlayers() {
        board = new Board(3);
        int counterNotNull = 0;
        int counterEmpty = 0;
        for(int i = 0; i < board.getBoardGrid().length; i++){
            for(int j = 0; j < board.getBoardGrid().length; j++){
                if(board.getBoardGrid()[i][j] != null){
                    counterNotNull++;
                    if(board.getBoardGrid()[i][j].getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                        counterEmpty++;
                    }
                }
            }
        }
        int finalCounterNotNull = counterNotNull;
        int finalCounterEmpty = counterEmpty;
        assertAll(
                () -> assertEquals(37, finalCounterNotNull),
                () -> assertNotNull(board.getBoardGrid()[0][3]),
                () -> assertNull(board.getBoardGrid()[0][4]),
                () -> assertEquals(0, finalCounterEmpty)
        );
    }

    @Test
    @DisplayName("Test the constructor for a 4 players game")
    public void testCreateBoardForFourPlayers() {
        board = new Board(4);
        int counterNotNull = 0;
        int counterEmpty = 0;
        for(int i = 0; i < board.getBoardGrid().length; i++){
            for(int j = 0; j < board.getBoardGrid().length; j++){
                if(board.getBoardGrid()[i][j] != null){
                    counterNotNull++;
                    if(board.getBoardGrid()[i][j].getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                        counterEmpty++;
                    }
                }
            }
        }
        int finalCounterNotNull = counterNotNull;
        int finalCounterEmpty = counterEmpty;
        assertAll(
                () -> assertEquals(45, finalCounterNotNull),
                () -> assertNotNull(board.getBoardGrid()[0][3]),
                () -> assertNotNull(board.getBoardGrid()[0][4]),
                () -> assertEquals(0, finalCounterEmpty)
        );
    }

    @Test
    @DisplayName("Test exception if invalid number of players")
    public void testCreateBoardForIllegalAmountOfPlayers() {
        assertThrows(IllegalArgumentException.class, () -> board = new Board(1), "exception thrown");
    }
}