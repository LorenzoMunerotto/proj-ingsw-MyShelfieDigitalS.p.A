package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.Bag;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
     * Constructor of the class.
     *
     * @param board the board of the game
     * @param bag the bag of the game
     */
    public BoardManager( Board board, Bag bag) {
        this.board = board;
        this.bag = bag;
    }

    /**
     * Checks if the board is ready for the refill.
     *
     * @return true if the board is ready, false otherwise
     */
    public boolean isRefillTime(){

        for (int row = 0; row < board.getROWS(); row++) {
            for (int col = 0; col < board.getCOLUMNS(); col++) {
                if (!(board.getBoardCell(row,col).isPlayable())) continue;
                if ((board.getBoardCell(row,col).getItemTile().getItemTileType()!=ItemTileType.EMPTY) && !(board.isAlone(row,col))) return false;
            }
        }
        return true;
    }

    /**
     * Refills the board with new random item tiles from the bag
     */
    public void refillBoard() {

        bag.shuffle();

        for (int row = 0; row < board.getROWS(); row++) {
            for (int col = 0; col < board.getCOLUMNS(); col++) {
                if (!(board.getBoardCell(row,col).isPlayable())) continue;
                if (board.getBoardCell(row,col).getItemTile().getItemTileType()==ItemTileType.EMPTY) {
                    board.putItemTile(row,col, bag.getRandomItemTile());
                }
            }
        }
    }

    /**
     * This method grabs the item tiles from the board and returns them.
     *
     * @param coordinates the coordinates of the item tiles to grab
     * @return the item tiles grabbed
     */
    public List<ItemTile> grabItemTiles(List<Pair<Integer, Integer>> coordinates) {

        int size = coordinates.size();
        if (size < 1 || size > 3) throw new IllegalArgumentException("Invalid number of coordinates");
        if (new HashSet<>(coordinates).size()<size) throw new IllegalArgumentException("Invalid number of coordinates - duplicate coordinates");
        if (!(isLined(coordinates))) throw new IllegalArgumentException("Invalid coordinates - the cells are not in line");


        for (Pair<Integer, Integer> coordinate : coordinates) {
            int row = coordinate.getValue0();
            int col = coordinate.getValue1();

            if (!board.getBoardCell(row,col).isPlayable() ) throw new IllegalArgumentException("Invalid coordinates - the cell is not playable");
            if (board.getBoardCell(row,col).getItemTile().getItemTileType() == ItemTileType.EMPTY) throw new IllegalArgumentException("Invalid coordinates - the cell is empty");
            if (!hasSideFree(row,col)) throw new IllegalArgumentException("Invalid coordinates - there is a tile, which has not a side free at the beginning of the turn");
            if (board.isAlone(row,col))throw new IllegalArgumentException("Invalid coordinates - the cell is alone");
        }

        List<ItemTile> itemTileList = new ArrayList<>();
        for (Pair<Integer, Integer> coordinate : coordinates) {
            int row = coordinate.getValue0();
            int col = coordinate.getValue1();

            itemTileList.add(board.takeItemTile(row,col));

        }

        return itemTileList;
    }

    /**
     * This method checks if the cell has a side free.
     *
     * @param row the row of the cell
     * @param col the column of the cell
     * @return true if the cell has a side free, false otherwise
     */
    private boolean hasSideFree( int row, int col){
        if (!(Board.hasLeftBoardCell(row,col) && Board.hasUpperBoardCell(row,col) && Board.hasRightBoardCell(row,col) && Board.hasLowerBoardCell(row,col))){
            return  true;
        }
        if (!(board.getLeftBoardCell(row,col).isPlayable() && board.getUpperBoardCell(row,col).isPlayable() && board.getRightBoardCell(row,col).isPlayable() && board.getLowerBoardCell(row,col).isPlayable())){
            return true;
        }
        return !(board.getLeftBoardCell(row, col).getItemTile().getItemTileType() != ItemTileType.EMPTY && board.getUpperBoardCell(row, col).getItemTile().getItemTileType() != ItemTileType.EMPTY && board.getRightBoardCell(row, col).getItemTile().getItemTileType() != ItemTileType.EMPTY && board.getLowerBoardCell(row, col).getItemTile().getItemTileType() != ItemTileType.EMPTY);
    }

    /**
     * This method checks if the coordinates are in line.
     *
     * @param coordinates the coordinates to check
     * @return true if the coordinates are in line, false otherwise
     */
    private boolean isLined(List<Pair<Integer, Integer>> coordinates){

        int number = coordinates.size();

        boolean sameRow = coordinates.stream().map(Pair::getValue0).collect(Collectors.toSet()).size()==1;
        List<Integer> columns = coordinates.stream().map(Pair::getValue1).sorted().collect(Collectors.toList());
        boolean inRow = (columns.get(columns.size()-1)-columns.get(0) == number-1) && sameRow;

        boolean sameColumn = coordinates.stream().map(Pair::getValue1).collect(Collectors.toSet()).size()==1;
        List<Integer> rows = coordinates.stream().map(Pair::getValue0).sorted().collect(Collectors.toList());
        boolean inColumn = (rows.get(rows.size()-1)-rows.get(0) == number-1) && sameColumn;

        return inRow || inColumn;

    }





}
