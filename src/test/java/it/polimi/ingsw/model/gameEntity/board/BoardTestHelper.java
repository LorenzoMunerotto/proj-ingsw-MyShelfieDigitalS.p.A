package it.polimi.ingsw.model.gameEntity.board;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

/**
 * Class containing helper methods for testing the board.
 */
public class BoardTestHelper {

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

        for (int row = 0; row < board.getROWS(); row++) {
            String[] singleRow = rows[row + 1].split(",");
            for (int col = 0; col < board.getCOLUMNS(); col++) {
                if (board.getBoardCell(row, col).isPlayable()) {
                    try {
                        board.putItemTile(row, col, new ItemTile(ItemTileType.valueOf(singleRow[col])));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid ItemTileType value: " + singleRow[col] + " at row " + row + " and col " + col);
                    }
                }
            }
        }
        return board;
    }

    /**
     * Check if all the tiles of the board are not empty.
     *
     * @param board the board to check
     * @return true if all the tiles are not empty, false otherwise
     */
    public static boolean checkAllNotEmpty(Board board) {
        for (int row = 0; row < board.getROWS(); row++) {
            for (int col = 0; col < board.getCOLUMNS(); col++) {
                if (board.getBoardCell(row, col).isPlayable()) {
                    if (board.getBoardCell(row, col).getItemTile().getItemTileType() == ItemTileType.EMPTY)
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Check from two different states of the board if the tiles are filled correctly.
     *
     * @param oldBoard      the old state of the board
     * @param refilledBoard the new state of the board
     * @return true if the tiles are filled correctly, false otherwise
     */
    public static boolean checkAllTilesNotEmptyNotRefill(Board oldBoard, Board refilledBoard) {
        for (int row = 0; row < oldBoard.getROWS(); row++) {
            for (int col = 0; col < oldBoard.getCOLUMNS(); col++) {
                if (oldBoard.getBoardCell(row, col).isPlayable()) {
                    if (oldBoard.getBoardCell(row, col).getItemTile().getItemTileType() != ItemTileType.EMPTY) {
                        if (oldBoard.getBoardCell(row, col).getItemTile().getItemTileType() != refilledBoard.getBoardCell(row, col).getItemTile().getItemTileType()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
