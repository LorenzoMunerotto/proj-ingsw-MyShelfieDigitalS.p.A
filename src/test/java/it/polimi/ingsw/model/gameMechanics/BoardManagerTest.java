package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.GameData;
import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardManagerTest {

    BoardManager boardManager;
    GameData gameData;

    Board board;
    Bag bag;

    @BeforeEach
    void setUp() {

        gameData = new GameData(2);
        boardManager = new BoardManager(gameData);
        board = gameData.getBoard();
        bag = gameData.getBag();
    }

    @Test
    @DisplayName("Test refillBoard() when the board is empty")
    void refillBoardEmptyBoard() {
        assertEquals(0, board.getValidCells().size());
        List<BoardCell> boardCells = List.of(new BoardCell(0, 0));
        board.setValidCells(boardCells);
        assertThrows(IllegalArgumentException.class, () -> boardManager.refillBoard());

        board.setValidCells(new ArrayList<>());
        int emptyCells = board.getEmptyCells();
        int bagSize = bag.getItemTiles().size();
        assertEquals(29, emptyCells);
        assertEquals(132, bagSize);
        boardManager.refillBoard();
        assertEquals(0, board.getEmptyCells());
        assertEquals(bagSize - emptyCells, bag.getItemTiles().size());

        int counter = 0;
        for (int i = 0; i < board.getBoardGrid().length; i++) {
            for (int j = 0; j < board.getBoardGrid()[i].length; j++) {
                if (board.getBoardGrid()[i][j] != null && board.getBoardGrid()[i][j].getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                    counter++;
                }
            }
        }
        assertEquals(29, counter);
    }

    @Test
    @DisplayName("Test refillBoard() when the board contains a not valid cell")
    void refillBoardNotEmptyBoard() {
        int bagSize = bag.getItemTiles().size();
        board.getBoardGrid()[5][5].setItemTile(new ItemTile(ItemTileType.CAT));
        int currentEmptyCells = board.getEmptyCells() - 1;
        board.setEmptyCells(currentEmptyCells);
        boardManager.refillBoard();

        assertEquals(0, board.getEmptyCells());
        assertEquals(bagSize - currentEmptyCells, bag.getItemTiles().size());
    }

    @Test
    @DisplayName("Test updateBoard() method, checking if the board is correctly updated after the refill")
    void updateBoardAfterRefill() {
        boardManager.refillBoard();
        boardManager.updateBoard();
        assertEquals(0, board.getEmptyCells());
        assertFalse(board.getBoardGrid()[5][5].isValid());

        List<Pair<Integer, Integer>> validCells = new ArrayList<>();
        validCells.add(new Pair<>(1, 3));
        validCells.add(new Pair<>(1, 4));
        validCells.add(new Pair<>(2, 3));
        validCells.add(new Pair<>(2, 5));
        validCells.add(new Pair<>(3, 2));
        validCells.add(new Pair<>(3, 6));
        validCells.add(new Pair<>(3, 7));
        validCells.add(new Pair<>(4, 1));
        validCells.add(new Pair<>(4, 7));
        validCells.add(new Pair<>(5, 1));
        validCells.add(new Pair<>(5, 2));
        validCells.add(new Pair<>(5, 6));
        validCells.add(new Pair<>(6, 3));
        validCells.add(new Pair<>(6, 5));
        validCells.add(new Pair<>(7, 4));
        validCells.add(new Pair<>(7, 5));

        List<Pair<Integer, Integer>> invalidCells = new ArrayList<>();
        invalidCells.add(new Pair<>(2, 4));
        invalidCells.add(new Pair<>(3, 3));
        invalidCells.add(new Pair<>(3, 4));
        invalidCells.add(new Pair<>(3, 5));
        invalidCells.add(new Pair<>(4, 2));
        invalidCells.add(new Pair<>(4, 3));
        invalidCells.add(new Pair<>(4, 4));
        invalidCells.add(new Pair<>(4, 5));
        invalidCells.add(new Pair<>(4, 6));
        invalidCells.add(new Pair<>(5, 3));
        invalidCells.add(new Pair<>(5, 4));
        invalidCells.add(new Pair<>(5, 5));
        invalidCells.add(new Pair<>(6, 4));

        for (Pair<Integer, Integer> validCell : validCells) {
            assertTrue(board.getBoardGrid()[validCell.getValue0()][validCell.getValue1()].isValid());
        }
        for (Pair<Integer, Integer> invalidCell : invalidCells) {
            assertFalse(board.getBoardGrid()[invalidCell.getValue0()][invalidCell.getValue1()].isValid());
        }

        board.getBoardGrid()[1][4].setItemTile(new ItemTile(ItemTileType.EMPTY));
        boardManager.updateBoard();
        assertEquals(1, board.getEmptyCells());
        validCells.remove(new Pair<>(1, 4));
        validCells.add(new Pair<>(2, 4));
        invalidCells.remove(new Pair<>(2, 4));
        for (Pair<Integer, Integer> validCell : validCells) {
            assertTrue(board.getBoardGrid()[validCell.getValue0()][validCell.getValue1()].isValid());
        }
        for (Pair<Integer, Integer> invalidCell : invalidCells) {
            assertFalse(board.getBoardGrid()[invalidCell.getValue0()][invalidCell.getValue1()].isValid());
        }
    }

    @Test
    @DisplayName("Test updateBoard() method, checking if the board is correctly updated when there is only one item tile")
    void updateBoardAloneTile() {
        BoardCell boardCell = board.getBoardGrid()[5][5];
        boardCell.setItemTile(new ItemTile(ItemTileType.CAT));
        boardCell.setValid(true);
        boardManager.updateBoard();
        assertEquals(28, board.getEmptyCells());
        assertFalse(board.getBoardGrid()[5][5].isValid());
    }

    @Test
    void grabItemTiles() {
    }

    @Test
    void isAdjacent() {
    }
}