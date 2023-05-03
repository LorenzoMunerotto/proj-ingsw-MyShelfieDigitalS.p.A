package it.polimi.ingsw.client;

import it.polimi.ingsw.client.clientMessage.Move;
import it.polimi.ingsw.client.clientMessage.NumOfPlayerChoice;
import it.polimi.ingsw.client.clientMessage.UsernameChoice;
import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.client.view.VirtualModel;
import it.polimi.ingsw.client.view.cli.CLI;
import it.polimi.ingsw.client.view.cli.CLIAssets;
import it.polimi.ingsw.client.view.cli.CLIConstants;
import it.polimi.ingsw.client.view.clientEntity.ClientBoardCell;
import it.polimi.ingsw.client.view.clientEntity.ClientLibrary;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.model.gameState.events.PersonalCardSetEvent;
import it.polimi.ingsw.server.serverMessage.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements ServerMessageHandler{

    private final SocketListener socketListener;
    private final View view;
    private VirtualModel virtualModel;
    private static final Scanner input = new Scanner(System.in);
    private static final List<String> availableViewType = Arrays.asList("c", "g");

    public Client(View view) {
        this.socketListener= new SocketListener(this);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(socketListener);
        this.view = view;
        this.virtualModel = this.view.getVirtualModel();
    }

    public void handle(UsernameRequest usernameRequest){
        String username = view.chooseUsername();
        socketListener.send(new UsernameChoice(username));
        virtualModel.setMyUsername(username);
    }
    public void handle(NumOfPlayerRequest numOfPlayerRequest) {
        socketListener.send(new NumOfPlayerChoice(view.choosePlayersNumber()));
    }

    public void handle(MoveRequest moveRequest){
        view.showGame();
        List<Coordinate> coordinates = view.chooseTiles();
        Integer column = view.chooseColumn();
        socketListener.send(new Move(coordinates,column));
    }

    public void handle(CustomMessage customMessage){
        System.out.println(customMessage.getMessage());
    }


    public void handle(StartGameMessage startGameMessage){
        System.out.println(startGameMessage.getMessage());

    }


    public void handle(BoardUpdateMessage boardUpdateMessage){

        ClientBoardCell[][] board = new ClientBoardCell[9][9];
        for (int row =0; row<9; row++){
            for (int col = 0; col<9; col++){
                board[row][col]= new ClientBoardCell(boardUpdateMessage.getGridBoard()[row][col],boardUpdateMessage.getPlayableGrid()[row][col]);
            }
        }
        virtualModel.updateBoard(board);

    }

    public void handle(StartTurnMessage startTurnMessage){
        virtualModel.updateCurrentPlayerUsername(startTurnMessage.getUsername());
        System.out.println(startTurnMessage.getMessage());
    }


    public void handle(LibraryUpdateMessage libraryUpdateMessage){
        ClientLibrary library = new ClientLibrary(libraryUpdateMessage.getLibraryGrid());
        virtualModel.updateLibraryByUsername(libraryUpdateMessage.getLibraryOwnerUsername(), library);
    }

    public void handle(CommonCardsSetMessage commonCardsSetMessage){
        virtualModel.updateCommonGoalCards(commonCardsSetMessage.getCommonGoalCardList());
    }


    @Override
    public void handle(BoardRefillMessage boardRefillMessage) {
        ClientBoardCell[][] board = new ClientBoardCell[9][9];
        for (int row =0; row<9; row++){
            for (int col = 0; col<9; col++){
                board[row][col]= new ClientBoardCell(boardRefillMessage.getGridBoard()[row][col],boardRefillMessage.getPlayableGrid()[row][col]);
            }
        }
        virtualModel.updateBoard(board);

        System.out.println(boardRefillMessage.getMessage());
    }

    @Override
    public void handle(BreakRulesMessage breakRulesMessage) {
        System.out.println(breakRulesMessage.getMessage());
    }

    @Override
    public void handle(CommonCardReachMessage commonCardReachMessage) {
        System.out.println(commonCardReachMessage.getMessage());
    }

    @Override
    public void handle(EndGameMessage endGameMessage) {
        System.out.println(endGameMessage.getMessage());
    }

    @Override
    public void handle(EndTurnMessage endTurnMessage) {
        System.out.println(endTurnMessage.getMessage());
    }

    @Override
    public void handle(ErrorMessage errorMessage) {
        System.out.println(errorMessage.getMessage());
    }

    @Override
    public void handle(FirstFullLibraryMessage firstFullLibraryMessage) {
        System.out.println(firstFullLibraryMessage.getMessage());
    }

    @Override
    public void handle(PersonalCardSetMessage personalCardSetMessage) {
        ClientLibrary library = new ClientLibrary(personalCardSetMessage.getLibraryGrid());
        virtualModel.setPersonalGoalCard(library);
    }

    @Override
    public void handle(PointsUpdateMessage pointsUpdateMessage) {
        virtualModel.updatePointsByUsername(pointsUpdateMessage.getUsername(), pointsUpdateMessage.getPoints());
    }

    @Override
    public void handle(ConnectionMessage connectionMessage) {

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

    public View getView() {
        return view;
    }

    public static void main(String[] Args){

        Client client;
        String viewType = chooseViewType();
        if(viewType.equals("c")){
            System.out.printf(CLIAssets.output + "You selected cli interface%n");
            client = new Client(new CLI());
        }
        else{
            System.out.println("Sorry, gui is not available yet, i'll let you play with the cli :)");
            client = new Client(new CLI());
        }

        client.view.main(Args);

    }



}

// swith case, o errore o a seconda dello stato della partita fa qualcosa