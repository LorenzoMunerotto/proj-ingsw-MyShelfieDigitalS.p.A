package it.polimi.ingsw.terminal;

import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.Library;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.GameData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Drawer {

    GameData gameData;

    public Drawer(GameData gameData) {
        this.gameData = gameData;
    }


    public void drawTurnInformation(){
        System.out.println("E' il turno di: " + gameData.getCurrentPlayer().getUsername() + " punteggio attuale: "+gameData.getCurrentPlayer().getTotPoints());
        System.out.println ("CarteComuni : " + gameData.getCommonGoalCardsList().get(0).getIndex() + "  e "+ gameData.getCommonGoalCardsList().get(1).getIndex());
        System.out.println (gameData.getCurrentPlayer().getPersonalGoalCard());
    }

    private String stringTile(ItemTileType itemTileType){
        if(itemTileType==ItemTileType.CAT) return " CAT ";
        if(itemTileType==ItemTileType.BOOK) return " BOK ";
        if(itemTileType==ItemTileType.GAME) return " GAM ";
        if(itemTileType==ItemTileType.PLANT) return " PLA ";
        if(itemTileType==ItemTileType.TROPHY) return " TRO ";
        if(itemTileType==ItemTileType.FRAME) return " FRA ";
        return "     ";
    }
    public void drawBoard (){
        Board board = gameData.getBoard();

        System.out.print("     ");
        for (int col=0; col<board.getCOLUMNS(); col++){
            System.out.print("  "+col+"  ");
        }
        System.out.println();
        for (int row=0; row<board.getROWS(); row++){
            System.out.print("  "+row+"  ");
            for (int col=0; col<board.getCOLUMNS(); col++){
                if (board.getBoardCell(row,col).isPlayable()) {
                    System.out.print(stringTile(board.getBoardCell(row, col).getItemTile().getItemTileType()));

                }
                else{
                    System.out.print("     ");
                }
            }
            System.out.println();
        }

    }

    public void currentLibrary (){

        Library currentLibrary = gameData.getCurrentPlayer().getLibrary();

        System.out.print("     ");
        for (int col=0; col<currentLibrary.getCOLUMNS(); col++){
            System.out.print("  "+col+"  ");
        }
        System.out.println();
        for (int row=0; row<currentLibrary.getROWS(); row++){
            System.out.print("  "+row+"  ");
            for (int col=0; col<currentLibrary.getCOLUMNS(); col++){

                System.out.print(stringTile(currentLibrary.getItemTile(row,col).getItemTileType()));

            }
            System.out.println();
        }

    }

    public void showRank(){
        List<Player> players = gameData.getPlayers().stream().sorted(Comparator.comparingInt(Player::getTotPoints).reversed()).collect(Collectors.toList());
        players.forEach(player->System.out.println(player.getUsername()+" "+player.getTotPoints()));
    }
}
