package it.polimi.ingsw.model.gameEntity.board;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;

public class BoardTestHelper {



    public static Board newBoardFromString(String str){
        String newstr;
        newstr = str.replaceAll("\n","");
        newstr = newstr.replaceAll(" ","");
        newstr = newstr.replaceAll("\"","");
        String[] rows = newstr.split("\\|");

        int numofPlayer = Integer.parseInt(rows[0]);
        Board board = new Board(numofPlayer);


        for (int row =0; row<board.getROWS(); row++){
            String[] singleRow = rows[row+1].split(",");
            for (int col = 0; col<board.getCOLUMNS(); col++){
                if(board.getBoardCell(row,col).isPlayable()) {
                    board.putItemTile(row, col, new ItemTile(ItemTileType.valueOf(singleRow[col])));
                }
            }
        }
        return board;
    }

    public static boolean checkAllNotEmpty(Board board){
        for (int row =0; row<board.getROWS(); row++){
            for (int col = 0; col<board.getCOLUMNS(); col++){
                if(board.getBoardCell(row,col).isPlayable()) {
                    if(board.getBoardCell(row,col).getItemTile().getItemTileType()==ItemTileType.EMPTY) return false;
                }
            }
        }
        return true;
    }

    public static boolean checkAllTilesNotEmptyNotRefill(Board oldBoard, Board refilledBoard){
        for (int row =0; row<oldBoard.getROWS(); row++){
            for (int col = 0; col<oldBoard.getCOLUMNS(); col++){
                if(oldBoard.getBoardCell(row,col).isPlayable()) {
                    if(oldBoard.getBoardCell(row,col).getItemTile().getItemTileType()!=ItemTileType.EMPTY) {
                        if (oldBoard.getBoardCell(row,col).getItemTile().getItemTileType()!=refilledBoard.getBoardCell(row,col).getItemTile().getItemTileType()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
