package it.polimi.ingsw.terminal;

import it.polimi.ingsw.AbstractListenable;
import it.polimi.ingsw.EventHandler;
import it.polimi.ingsw.Event;
import it.polimi.ingsw.Listener;
import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.*;
import it.polimi.ingsw.model.gameState.events.Move;
import it.polimi.ingsw.model.gameState.events.NumOfPlayerChoice;
import it.polimi.ingsw.model.gameState.events.UsernameChoice;
import it.polimi.ingsw.model.gameState.events.VirtualGameData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Drawer extends AbstractListenable implements  Listener,  EventHandler {


    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader tastiera = new BufferedReader(input);

    VirtualGameData virtualGameData;



    public Drawer(GameData gameData) {
        super();
        gameData.addListener(this);


    }


    /**
     * handle a change in gameData
     * @param virtualGameData
     */

    public void handle(VirtualGameData virtualGameData){

            this.virtualGameData = virtualGameData;
            if (virtualGameData.getNumOfPlayers()==0){
                askNumOfPlayers();
            }
            else if (virtualGameData.isStarted()==false){
                askUsername();
            }
            else if (virtualGameData.getFirstFullLibraryUsername().isPresent() && virtualGameData.getCurrentPlayerIndex()==0){
                showRank();
            }
            else if (virtualGameData.isStarted()) {
                drawTurnInformation();
                drawBoard();
                currentLibrary();
                takePlayerMove();
            }


    }


    /**
     * draw turn information
     */
    public void drawTurnInformation(){
        System.out.println("E' il turno di: " + virtualGameData.getCurrentPlayer().getUsername() + " punteggio attuale: "+virtualGameData.getCurrentPlayer().getTotPoints());
        System.out.println ("CarteComuni : " + virtualGameData.getCommonGoalCardsList().get(0).getIndex() + "  e "+ virtualGameData.getCommonGoalCardsList().get(1).getIndex());
        System.out.println (virtualGameData.getCurrentPlayer().getPersonalGoalCard());
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

    /**
     * draw the board
     */
    public void drawBoard (){
        Board board = virtualGameData.getBoard();

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

    /**
     * draw the current Library
     */
    public void currentLibrary () {

        Library currentLibrary = virtualGameData.getCurrentPlayer().getLibrary();

        System.out.print("     ");
        for (int col = 0; col < currentLibrary.getCOLUMNS(); col++) {
            System.out.print("  " + col + "  ");
        }
        System.out.println();
        for (int row = 0; row < currentLibrary.getROWS(); row++) {
            System.out.print("  " + row + "  ");
            for (int col = 0; col < currentLibrary.getCOLUMNS(); col++) {

                System.out.print(stringTile(currentLibrary.getItemTile(row, col).getItemTileType()));

            }
            System.out.println();
        }
    }

    /**
     * This method asks in loop the input until the input respect the pattern "row1col1-row2col2-row3col3|column"
     */
        public void takePlayerMove() {

        Boolean inputCorrect = false;

        while(inputCorrect==false) {


            List<Coordinate> listOfCoordinate = new ArrayList<>();
            Integer column = null;

            System.out.println("inserire coordinate delle tessere desiderate nell'ordine di inserimento e la colonna dove inserirle  ");
            System.out.println("es. 74-75|2");
            try {
                String moveAsString = tastiera.readLine();

                //parsare i comandi
                String newstr;
                newstr = moveAsString.replaceAll(" ", "");
                newstr = newstr.replaceAll("\n", "");
                newstr = newstr.replaceAll("\"", "");
                String[] new_comandi = newstr.split("\\|");

                String[] coordinate = new_comandi[0].split("-");

                column = Integer.valueOf(new_comandi[1]);

                for (String coordinata : coordinate) {

                    int row = Integer.valueOf("" + coordinata.charAt(0));
                    int col = Integer.valueOf("" + coordinata.charAt(1));

                    Coordinate coordinate1 = new Coordinate(row, col);
                    listOfCoordinate.add(coordinate1);
                }

                inputCorrect = true;
                //notifico al controller la mossa del giocatore
                notifyAllListeners(new Move(listOfCoordinate, column)); //
            } catch (Exception e) {

                System.out.println("Invalid input");

            }
        }

        }


    /**
     * draw the rank, only at the end of the game
     */
    public void showRank(){
        List<Player> players =  virtualGameData.getPlayers().stream().sorted(Comparator.comparingInt(Player::getTotPoints).reversed()).collect(Collectors.toList());
        players.forEach(player->System.out.println(player.getUsername()+" "+player.getTotPoints()));
    }


    /**
     * asks the number of players
     */
    public void askNumOfPlayers(){
        System.out.print("You are the first player, select numberOfPlayer: ");
        Integer numOfPlayer;
        try {
            numOfPlayer = Integer.valueOf(tastiera.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //notifico al controller la scelta del giocatore
        notifyAllListeners(new NumOfPlayerChoice(numOfPlayer));

    }

    /**
     * asks the username
     */
    public void askUsername(){
        System.out.print("new player " + (virtualGameData.getCurrentNumOfPlayers() + 1) + "/" + virtualGameData.getNumOfPlayers() + " select username: ");
        String username = null;

        try {
            username = tastiera.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //notifico al controller la scelta del giocatore
        notifyAllListeners(new UsernameChoice(username));


    }

    /**
     * This is part of Observable-Observer pattern
     * @param event
     */
    @Override
    public void update(Event event) {
        event.accept(this);
    }


    /**
     * handle the numOfPlayerChoice event,(= the player's choice is invalid)
     * ask again to choose the num of players again
     * @param numOfPlayerChoice
     */
    public void handle(NumOfPlayerChoice numOfPlayerChoice) {
      System.out.println("Invalid Selection");
      askNumOfPlayers();
    }

    /**
     * handle the usernameChoice event,(= the player's choice is invalid)
     * ask again the username
     * @param usernameChoice
     */
    public void handle(UsernameChoice usernameChoice) {
        System.out.println("Invalid username");
        askUsername();
    }

    /**
     * handle the move event,(= the player's choice is invalid)
     * ask again the move
     * @param move
     */
    public void handle(Move move) {
        System.out.println("Invalid move");
        takePlayerMove();
    }




}
