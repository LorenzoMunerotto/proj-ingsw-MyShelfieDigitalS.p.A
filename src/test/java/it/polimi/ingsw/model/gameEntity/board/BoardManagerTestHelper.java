package it.polimi.ingsw.model.gameEntity.board;

import it.polimi.ingsw.model.gameEntity.Bag;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameMechanics.BoardManager;

import static it.polimi.ingsw.model.gameEntity.enums.ItemTileType.EMPTY;
import static it.polimi.ingsw.model.gameEntity.enums.ItemTileType.NULL;

/**
 * Class containing helper methods for testing the board.
 */
public class BoardManagerTestHelper extends BoardManager{

    /**
     * Constructor of the class.
     *
     * @param board the board of the game
     * @param bag   the bag of the game
     */
    public BoardManagerTestHelper(Board board, Bag bag) {
        super(board, bag);
    }

    /**
     * Create a new board from a string.
     *
     * @param str string representing the board
     * @return the new board
     */
    public static Board newBoardFromString(String str) {
        String newStr;
        newStr = str.replaceAll("\r\n|\n|\r", "");
        newStr = newStr.replaceAll(" ", "");
        newStr = newStr.replaceAll("\"", "");
        String[] rows = newStr.split("\\|");

        int numOfPlayer = Integer.parseInt(rows[0]);
        Board board = new Board(numOfPlayer);

        for (int row = 0; row < board.getBoardGrid().length; row++) {
            String[] singleRow = rows[row + 1].split(",");
            for (int column = 0; column < board.getBoardGrid()[0].length; column++) {
                if (board.getItemTile(row, column) != ItemTileType.NULL) {
                    try {
                        ItemTileType itemTile = ItemTileType.valueOf(singleRow[column]);
                        board.setItemTile(row, column, itemTile);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid ItemTileType value: " + singleRow[column] + " at row " + row + " and column " + column);
                    }
                }
            }
        }
        return board;
    }

    public void updateNotEmptyCells(Board board) {
        for (int row = 0; row < board.getBoardGrid().length; row++) {
            for (int column = 0; column < board.getBoardGrid()[0].length; column++) {
                if (board.getItemTile(row, column) != NULL) {

                    if (board.getItemTile(row, column) == EMPTY) {
                        int finalRow = row;
                        int finalColumn = column;
                        this.getNotEmptyCells().removeIf(coordinate -> coordinate.getRow() == finalRow && coordinate.getColumn() == finalColumn);
                    } else {
                        this.getNotEmptyCells().add(new Coordinate(row, column));
                    }
                }
            }
        }
    }
}
