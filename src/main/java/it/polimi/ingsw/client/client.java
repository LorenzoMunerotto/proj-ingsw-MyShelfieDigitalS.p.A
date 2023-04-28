package it.polimi.ingsw.client;

import it.polimi.ingsw.client.clientMessage.Move;
import it.polimi.ingsw.client.clientMessage.NumOfPlayerChoice;
import it.polimi.ingsw.client.clientMessage.UsernameChoice;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.events.BoardUpdateEvent;
import it.polimi.ingsw.model.gameState.events.CommonCardEvent;
import it.polimi.ingsw.model.gameState.events.CurrentPlayerUpdateEvent;
import it.polimi.ingsw.server.serverMessage.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class client {

    SocketListener socketListener;
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader tastiera = new BufferedReader(input);

    public client() {
        this.socketListener= new SocketListener(this);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(socketListener);
    }

    public void handle(NumOfPlayerRequest numOfPlayerRequest) {
        System.out.println(numOfPlayerRequest.getMessage());
        System.out.print(">  ");
        try {
            Integer numOfPlayer = Integer.valueOf(tastiera.readLine());
            socketListener.send(new NumOfPlayerChoice(numOfPlayer));
        } catch (NumberFormatException e) {
            System.out.println("Please select a number");
            handle(numOfPlayerRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void handle(CustomMessage customMessage){
        System.out.println(customMessage.getMessage());
    }



    public void handle(UsernameRequest usernameRequest){
        System.out.println(usernameRequest.getMessage());
        System.out.print(">  ");
        try {
            String username = tastiera.readLine();
            socketListener.send(new UsernameChoice(username));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void handle(ServerMessage message){
        System.out.println(message.getMessage());
    }


    public void handle(BoardUpdateMessage boardUpdateMessage){
        System.out.println("Board aggiornata ");

        drawBoard(boardUpdateMessage.getGridBoard());

    }

    public void handle(StartTurnMessage startTurnMessage){

        System.out.println(startTurnMessage.getMessage());
    }


    public void handle(LibraryUpdateMessage libraryUpdateMessage){
        System.out.println(libraryUpdateMessage.getLibraryOwnerUsername() + " 's library change");

        System.out.print("     ");
        for (int col = 0; col < 5; col++) {
            System.out.print("  " + col + "  ");
        }
        System.out.println();
        for (int row = 0; row < 6; row++) {
            System.out.print("  " + row + "  ");
            for (int col = 0; col < 5; col++) {

                System.out.print(stringTile(libraryUpdateMessage.getLibraryGrid()[row][col]));

            }
            System.out.println();
        }

    }

    public void handle(MoveRequest moveRequest){
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
                socketListener.send(new Move(listOfCoordinate, column)); //
            } catch (Exception e) {

                System.out.println("Invalid input");

            }
        }


    }
    public void drawBoard (ItemTileType[][] grid){


        System.out.print("     ");
        for (int col=0; col<9; col++){
            System.out.print("  "+col+"  ");
        }
        System.out.println();
        for (int row=0; row<9; row++){
            System.out.print("  "+row+"  ");
            for (int col=0; col<9; col++){

                System.out.print(stringTile(grid[row][col]));

            }
            System.out.println();
        }

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

    public static void main(String[] Args){

        client client1 = new client();

        System.out.println("My Shelfie");






    }


}
