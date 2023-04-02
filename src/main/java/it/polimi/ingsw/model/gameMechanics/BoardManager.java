package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.GameData;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages the board.
 */
public class BoardManager {

    /**
     * The board of the game.
     */
    final Board board;
    /**
     * The bag of the game.
     */
    final Bag bag;

    /**
     * Constructor for the board manager, initializes the board and the bag.
     *
     * @param gameData the game data
     */
    public BoardManager(GameData gameData) {
        this.board = gameData.getBoard();
        this.bag = gameData.getBag();
    }

    /**
     * Refills the board with new random item tiles from the bag,
     * if there are no more valid cells on the board.
     */
    public void refillBoard() {
        if(this.board.getValidCells().size() != 0) throw new IllegalArgumentException("There are still valid cells on the board!");
        int emptyCells = board.getEmptyCells();
        List<ItemTile> itemTileList = bag.getRandomItemTiles(emptyCells);

        for (int row = 0; row < board.getBoardGrid().length; row++) {
            for (int column = 0; column < board.getBoardGrid()[row].length; column++) {

                BoardCell currentCell = board.getBoardGrid()[row][column];
                
                if (currentCell != null) {
                    if(currentCell.getItemTile().getItemTileType() == ItemTileType.EMPTY){
                        currentCell.setItemTile(itemTileList.remove(0));
                        board.setEmptyCells(board.getEmptyCells() - 1);
                    }
                }
            }
        }
    }

    /**
     * Updates the status of the board and of the board cells and it update setValid value.
     */
    public void updateBoard() {
        BoardCell[][] boardGrid = this.board.getBoardGrid();
        List<BoardCell> validCells = new ArrayList<>();
        int emptyCells = 0;

        for (int row = 0; row < boardGrid.length; row++) {
            for (int column = 0; column < boardGrid[row].length; column++) {
                BoardCell currentCell = boardGrid[row][column];
                if (currentCell != null) {
                    if (currentCell.getItemTile().getItemTileType() == ItemTileType.EMPTY) {
                        emptyCells++;
                    } else {
                        int freeSides = 0;
                        // top
                        if (row == 0 || boardGrid[row - 1][column] == null || boardGrid[row - 1][column].getItemTile().getItemTileType() == ItemTileType.EMPTY) {
                            freeSides++;
                        }
                        // bottom
                        if (row == boardGrid.length - 1 || boardGrid[row + 1][column] == null || boardGrid[row + 1][column].getItemTile().getItemTileType() == ItemTileType.EMPTY) {
                            freeSides++;
                        }
                        // left
                        if (column == 0 || boardGrid[row][column - 1] == null || boardGrid[row][column - 1].getItemTile().getItemTileType() == ItemTileType.EMPTY) {
                            freeSides++;
                        }
                        // right
                        if (column == boardGrid[row].length - 1 || boardGrid[row][column + 1] == null || boardGrid[row][column + 1].getItemTile().getItemTileType() == ItemTileType.EMPTY) {
                            freeSides++;
                        }
                        if (freeSides > 0 && freeSides < 4) {
                            validCells.add(currentCell);
                            currentCell.setValid(true);
                        } else {
                            currentCell.setValid(false);
                        }
                    }
                }

            }
        }
        board.setEmptyCells(emptyCells);
        board.setValidCells(validCells);
    }

    /**
     * Grabs the item tiles from the board.
     *
     * @param coordinates the coordinates of the cells to grab
     * @return the list of item tiles grabbed
     */
    public List<ItemTile> grabItemTiles(List<Pair<Integer, Integer>> coordinates) {
        int size = coordinates.size();
        if (size < 1 || size > 3)
            throw new IllegalArgumentException("Invalid number of coordinates");

        List<ItemTile> itemTileList = new ArrayList<>();
        List<BoardCell> cellList = new ArrayList<>();

        for (Pair<Integer, Integer> coordinate : coordinates) {
            BoardCell currentCell = board.getBoardGrid()[coordinate.getValue0()][coordinate.getValue1()];
            if (currentCell == null || currentCell.getItemTile().getItemTileType() == ItemTileType.EMPTY)
                throw new IllegalArgumentException("Invalid coordinates");
            if (!currentCell.isValid())
                throw new IllegalArgumentException("You can't grab an item tile from this cell");
            cellList.add(currentCell);
        }

        if (isAdjacent(cellList)) {
            for (BoardCell cell : cellList) {
                itemTileList.add(cell.getItemTile());
                cell.setItemTile(new ItemTile(ItemTileType.EMPTY));
                board.setEmptyCells(board.getEmptyCells() + 1);
            }
        } else throw new IllegalArgumentException("The cells are not adjacent");
        return itemTileList;
    }

    /**
     * Checks if the cells are adjacent.
     *
     * @param cells the list of cells to check
     * @return true if the cells are adjacent, false otherwise
     */
    public boolean isAdjacent(List<BoardCell> cells) {
        int rowDistance, columnDistance;
        for (int i = 0; i < cells.size() - 1; i++) {
            rowDistance = Math.abs(cells.get(i).getRow() - cells.get(i + 1).getRow());
            columnDistance = Math.abs(cells.get(i).getColumn() - cells.get(i + 1).getColumn());
            if (!((rowDistance == 0 && columnDistance == 1) || (rowDistance == 1 && columnDistance == 0))) {
                return false;
            }
        }
        return true;
    }

    public void getEmptyCells() {
        int emptyCells = 0;
        for (int row = 0; row < board.getBoardGrid().length; row++) {
            for (int column = 0; column < board.getBoardGrid()[row].length; column++) {
                BoardCell currentCell = board.getBoardGrid()[row][column];
                if (currentCell != null && currentCell.getItemTile().getItemTileType() == ItemTileType.EMPTY) {
                    emptyCells++;
                }
            }
        }
        board.setEmptyCells(emptyCells);
    }
}
