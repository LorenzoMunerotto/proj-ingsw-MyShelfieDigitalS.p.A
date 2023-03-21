package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enums.ItemTileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    Board board;
    Bag bag;

    @Test
    @DisplayName("Test the constructor for a 2 players game")
    public void testCreateBoardForTwoPlayers() {
        board = new Board(2);
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
                () -> assertEquals(29, finalCounterNotNull),
                () -> assertNull(board.getBoardGrid()[0][3]),
                () -> assertNull(board.getBoardGrid()[0][4]),
                () -> assertEquals(0, finalCounterEmpty)
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

    @Test
    @DisplayName("Test setItemTiles for a 2 players game")
    public void testSetItemTiles2Players() {
        board = new Board(2);
        bag = new Bag();
        Random random = new Random();

        //random list of item tiles
        BoardCell[][] grid = board.getBoardGrid();
        int randomAmount = random.nextInt(45);
        List<ItemTile> itemTileList = new ArrayList<>(bag.getRandomItemTiles(randomAmount));
        int randomIndex = random.nextInt(itemTileList.size());
        ItemTile itemTile = itemTileList.get(randomIndex);

        //checking exception if list size is bigger than available cells
        if (randomAmount >= 29) {
            assertThrows(IllegalArgumentException.class, () -> board.setItemTiles(itemTileList), "exception thrown");
        } else {
            board.setItemTiles(itemTileList);
            int counterAvailable = 0;
            //counterAvailable checks if all the item tiles have been inserted in the board
            for (BoardCell[] boardCells : grid) {
                for (int column = 0; column < grid.length; column++) {
                    if (boardCells[column] != null && boardCells[column].getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                        counterAvailable++;
                    }
                }
            }
            //checking if the item tile moves correctly from the list to the board
            int rowRandom = 0;
            int columnRandom = 0;
            for (BoardCell[] boardCells : grid) {
                for (int column = 0; column < grid.length; column++) {
                    if (boardCells[column] != null && boardCells[column].getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                        if (randomIndex == 0) {
                            rowRandom = boardCells[column].getRow();
                            columnRandom = boardCells[column].getColumn();
                        }
                        randomIndex--;
                    }
                }
            }
            assertEquals(randomAmount, counterAvailable);
            assertNull(grid[0][3]);
            assertNull(grid[0][4]);
            assertEquals(itemTile, grid[rowRandom][columnRandom].getItemTile());
        }
    }

    @Test
    @DisplayName("Test setItemTiles for a 3 players game")
    public void testSetItemTiles3Players() {
        board = new Board(3);
        bag = new Bag();
        Random random = new Random();

        //random list of item tiles
        BoardCell[][] grid = board.getBoardGrid();
        int randomAmount = random.nextInt(45);
        List<ItemTile> itemTileList = new ArrayList<>(bag.getRandomItemTiles(randomAmount));
        int randomIndex = random.nextInt(itemTileList.size());
        ItemTile itemTile = itemTileList.get(randomIndex);

        //checking exception if list size is bigger than available cells
        if (randomAmount >= 37) {
            assertThrows(IllegalArgumentException.class, () -> board.setItemTiles(itemTileList), "exception thrown");
        } else {
            board.setItemTiles(itemTileList);
            int counterAvailable = 0;
            //counterAvailable checks if all the item tiles have been inserted in the board
            for (BoardCell[] boardCells : grid) {
                for (int column = 0; column < grid.length; column++) {
                    if (boardCells[column] != null && boardCells[column].getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                        counterAvailable++;
                    }
                }
            }
            //checking if the item tile moves correctly from the list to the board
            int rowRandom = 0;
            int columnRandom = 0;
            for (BoardCell[] boardCells : grid) {
                for (int column = 0; column < grid.length; column++) {
                    if (boardCells[column] != null && boardCells[column].getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                        if (randomIndex == 0) {
                            rowRandom = boardCells[column].getRow();
                            columnRandom = boardCells[column].getColumn();
                        }
                        randomIndex--;
                    }
                }
            }
            assertEquals(randomAmount, counterAvailable);
            assertNotNull(grid[0][3]);
            assertNull(grid[0][4]);
            assertEquals(itemTile, grid[rowRandom][columnRandom].getItemTile());
        }
    }

    @Test
    @DisplayName("Test setItemTiles for a 4 players game")
    public void testSetItemTiles4Players() {
        board = new Board(4);
        bag = new Bag();
        Random random = new Random();

        //random list of item tiles
        BoardCell[][] grid = board.getBoardGrid();
        int randomAmount = random.nextInt(100);
        List<ItemTile> itemTileList = new ArrayList<>(bag.getRandomItemTiles(randomAmount));
        int randomIndex = random.nextInt(itemTileList.size());
        ItemTile itemTile = itemTileList.get(randomIndex);

        //checking exception if list size is bigger than available cells
        if (randomAmount >= 45) {
            assertThrows(IllegalArgumentException.class, () -> board.setItemTiles(itemTileList), "exception thrown");
        } else {
            board.setItemTiles(itemTileList);
            int counterAvailable = 0;
            //counterAvailable checks if all the item tiles have been inserted in the board
            for (BoardCell[] boardCells : grid) {
                for (int column = 0; column < grid.length; column++) {
                    if (boardCells[column] != null && boardCells[column].getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                        counterAvailable++;
                    }
                }
            }
            //checking if the item tile moves correctly from the list to the board
            int rowRandom = 0;
            int columnRandom = 0;
            for (BoardCell[] boardCells : grid) {
                for (int column = 0; column < grid.length; column++) {
                    if (boardCells[column] != null && boardCells[column].getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                        if (randomIndex == 0) {
                            rowRandom = boardCells[column].getRow();
                            columnRandom = boardCells[column].getColumn();
                        }
                        randomIndex--;
                    }
                }
            }
            assertEquals(randomAmount, counterAvailable);
            assertNotNull(grid[0][3]);
            assertNotNull(grid[0][4]);
            assertEquals(itemTile, grid[rowRandom][columnRandom].getItemTile());
        }
    }
}