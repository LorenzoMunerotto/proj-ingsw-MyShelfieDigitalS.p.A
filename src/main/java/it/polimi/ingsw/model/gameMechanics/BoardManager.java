package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.listener.AbstractListenable;
import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.events.BoardRefillEvent;
import it.polimi.ingsw.model.gameState.events.BoardUpdateEvent;
import it.polimi.ingsw.model.gameState.exceptions.EmptyBagException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.CRC32;

import static it.polimi.ingsw.model.gameEntity.enums.ItemTileType.*;

/**
 * Class that manages the board.
 */
public class BoardManager extends AbstractListenable {

    /**
     * The board of the game.
     */
    private final Board board;
    /**
     * The bag of the game.
     */
    private final Bag bag;
    /**
     * The list of playable empty cells.
     */
    private final List<Coordinate> emptyCells;
    /**
     * The list of playable not empty cells.
     */
    private final List<Coordinate> notEmptyCells;

    /**
     * Constructor of the class.
     *
     * @param board the board of the game
     * @param bag   the bag of the game
     */
    public BoardManager(Board board, Bag bag) {
        this.board = board;
        int length = board.getBoardGrid().length;
        this.bag = bag;
        emptyCells = new ArrayList<>();
        IntStream.range(0, length).forEach(row -> IntStream.range(0, length).forEach(column -> {
            if (board.getItemTile(row, column) == EMPTY) emptyCells.add(new Coordinate(row, column));
        }));
        notEmptyCells = new ArrayList<>();
    }

    /**
     * This method returns the list of empty cells.
     *
     * @return the list of empty cells
     */
    public List<Coordinate> getEmptyCells() {
        return emptyCells;
    }

    /**
     * This method returns the list of not empty cells.
     *
     * @return the list of not empty cells
     */
    public List<Coordinate> getNotEmptyCells() {
        return notEmptyCells;
    }

    /**
     * Get the board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Checks if the board is ready for the refill.
     *
     * @return true if the board is ready, false otherwise
     */
    public boolean isRefillTime() {
        for (Coordinate coordinate : notEmptyCells) {
            int row = coordinate.getRow();
            int col = coordinate.getColumn();
            if (!(isAlone(row, col))) return false;
        }
        return true;
    }

    /**
     * Refills the board with new random item tiles from the bag
     */
    public void refillBoard() throws EmptyBagException {
        bag.shuffle();
        int i = emptyCells.size() - 1;
        try{
            for (; i >= 0; i--) {
                Coordinate coordinate = emptyCells.get(i);
                int row = coordinate.getRow();
                int column = coordinate.getColumn();
                board.setItemTile(row, column, bag.grabItemTile());
                this.emptyCells.remove(coordinate);
                this.notEmptyCells.add(coordinate);
        }
        long checksum = calculateCRC();
        notifyAllListeners(new BoardRefillEvent(board.getBoardGrid(), checksum));
    }catch (EmptyBagException e){
            try {
                for (; i >= 0; i--) {
                    Coordinate coordinate = emptyCells.get(i);
                    emptyCells.remove(i);
                    notEmptyCells.add(coordinate);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            long checksum = calculateCRC();
            notifyAllListeners(new BoardRefillEvent(board.getBoardGrid(), checksum));
            throw e;
        }
    }

    /**
     * This method grabs the item tiles from the board and returns them.
     *
     * @param coordinates the coordinates of the item tiles to grab
     * @return the item tiles grabbed
     */
    public List<ItemTileType> grabItemTiles(List<Coordinate> coordinates) {
        List<ItemTileType> itemTileList = new ArrayList<>();
        for (Coordinate coordinate : coordinates) {
            int row = coordinate.getRow();
            int column = coordinate.getColumn();

            itemTileList.add(board.getItemTile(row, column));
            board.setItemTile(row, column, EMPTY);
            this.emptyCells.add(coordinate);
            this.notEmptyCells.remove(coordinate);
        }
        long checksum = calculateCRC();
        notifyAllListeners(new BoardUpdateEvent(coordinates, checksum));
        return itemTileList;
    }

    /**
     * This method checks if the cell has a side free.
     *
     * @param row    the row of the cell
     * @param column the column of the cell
     * @return true if the cell has a side free, false otherwise
     */
    public boolean hasSideFree(int row, int column) {
        return isUpperSideFree(row, column) ||
                isLowerSideFree(row, column) ||
                isRightSideFree(row, column) ||
                isLeftSideFree(row, column);
    }

    /**
     * This method tell if the boardCell in boardGrid[row][col] is alone.
     * A BoardCell is alone if all 4 adjacent cells are empty.
     *
     * @param row is the row of the board grid
     * @param col is the column of the board grid
     * @return True if the boardCell in boardGrid[row][col] is alone
     */
    public boolean isAlone(int row, int col) {
        return isUpperSideFree(row, col) &&
                isLowerSideFree(row, col) &&
                isRightSideFree(row, col) &&
                isLeftSideFree(row, col);
    }

    /**
     * This method checks if the coordinates are in line.
     *
     * @param coordinates the coordinates to check
     * @return true if the coordinates are in line, false otherwise
     */
    public boolean isLined(List<Coordinate> coordinates) {
        List<Coordinate> coordinatesCopy = coordinates.stream()
                .sorted(Comparator.comparingInt(Coordinate::getRow).thenComparingInt(Coordinate::getColumn))
                .collect(Collectors.toList());
        int number = coordinatesCopy.size();

        boolean inRow = coordinatesCopy.get(0).getRow() == coordinatesCopy.get(number - 1).getRow();
        boolean inColumn = coordinatesCopy.get(0).getColumn() == coordinatesCopy.get(number - 1).getColumn();

        if (inRow) {
            int firstColumn = coordinatesCopy.get(0).getColumn();
            return IntStream.rangeClosed(0, number - 1).allMatch(i -> coordinatesCopy.get(i).getColumn() == firstColumn + i);
        } else if (inColumn) {
            int firstRow = coordinatesCopy.get(0).getRow();
            return IntStream.rangeClosed(0, number - 1).allMatch(i -> coordinatesCopy.get(i).getRow() == firstRow + i);
        }
        return false;
    }


    /**
     * This method returns true if the cell is empty or null.
     *
     * @param row    is the row of the board grid
     * @param column is the column of the board grid
     * @return true if the cell is empty or null, false otherwise
     */
    private boolean isEmptyOrNull(int row, int column) {
        return board.getItemTile(row, column) == EMPTY || board.getItemTile(row, column) == NULL;
    }

    /**
     * This method returns true if the upper side is free.
     *
     * @param row    is the row of the board grid
     * @param column is the column of the board grid
     * @return the upper cell if it exists, false otherwise
     */
    private boolean isUpperSideFree(int row, int column) {
        return row == 0 || isEmptyOrNull(row - 1, column);
    }

    /**
     * This method returns true if the lower side is free.
     *
     * @param row    is the row of the board grid
     * @param column is the column of the board grid
     * @return the lower cell if it exists, false otherwise
     */
    private boolean isLowerSideFree(int row, int column) {
        return row == board.getBoardGrid().length - 1 || isEmptyOrNull(row + 1, column);
    }

    /**
     * This method returns true if the left side is free.
     *
     * @param row    is the row of the board grid
     * @param column is the column of the board grid
     * @return the left cell if it exists, false otherwise
     */
    private boolean isLeftSideFree(int row, int column) {
        return column == 0 || isEmptyOrNull(row, column - 1);
    }

    /**
     * This method returns true if the right side is free.
     *
     * @param row    is the row of the board grid
     * @param column is the column of the board grid
     * @return the right cell if it exists, false otherwise
     */
    private boolean isRightSideFree(int row, int column) {
        return column == board.getBoardGrid()[0].length - 1 || isEmptyOrNull(row, column + 1);
    }

    /**
     * This method calculates the checksum of the board.
     * It is used to check if the board has been modified.
     *
     * @return the checksum of the board
     */
    public long calculateCRC() {
        CRC32 crc = new CRC32();
        for (int row = 0; row < board.getBoardGrid().length; row++) {
            for (int col = 0; col < board.getBoardGrid()[0].length; col++) {
                crc.update(board.getItemTile(row, col).toString().getBytes());
            }
        }
        return crc.getValue();
    }
}