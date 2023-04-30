package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.client.view.cli.CLI;
import it.polimi.ingsw.client.view.cli.CLIAssets;
import it.polimi.ingsw.client.view.cli.CLIConstants;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.server.serverMessage.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private final SocketListener socketListener;
    private View view;
    private static final Scanner input = new Scanner(System.in);
    private static final List<String> availableViewType = Arrays.asList("c", "g");

    public Client() {
        this.socketListener= new SocketListener(this);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(socketListener);
    }

    public void handle(NumOfPlayerRequest numOfPlayerRequest) {
    }

    public void handle(CustomMessage customMessage){
        System.out.println(customMessage.getMessage());
    }



    public void handle(UsernameRequest usernameRequest){
    }

    public void handle(ServerMessage message){
        System.out.println(message.getMessage());
    }


    public void handle(BoardUpdateMessage boardUpdateMessage){
        System.out.println("Board updated ");

        drawBoard(boardUpdateMessage.getGridBoard());

    }

    public void handle(StartTurnMessage startTurnMessage){

        System.out.println(startTurnMessage.getMessage());
    }


    public void handle(LibraryUpdateMessage libraryUpdateMessage){
    }

    public void handle(MoveRequest moveRequest){
    }
    public void drawBoard (ItemTileType[][] grid){
    }

    /**
     * Choose view type between cli and gui.
     *
     * @return view type string
     */
    public static String chooseViewType(){
        String viewType = "";
        System.out.printf(CLIAssets.output + "Select preferred interface between cli and gui [%sc%s/%sg%s]: ", CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        while (viewType.isEmpty()){
            viewType = Client.input.nextLine().strip().toLowerCase();
            if(!availableViewType.contains(viewType)){
                System.out.printf(CLIAssets.output + "%sInvalid input%s, please select preferred interface between cli and gui [%sc%s/%sg%s]: ", CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                viewType = "";
            }
        }
        return viewType;
    }

    public static void main(String[] Args){

        Client client = new Client();
        String viewType = chooseViewType();
        if(viewType.equals("c")){
            client.view = new CLI();
        }
        else{
            System.out.println("Sorry, gui is not available yet, i'll let you play with the cli :)");
        }
    }

}

// swith case, o errore o a seconda dello stato della partita fa qualcosa