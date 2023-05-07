package it.polimi.ingsw.client;

import it.polimi.ingsw.client.clientMessage.Move;
import it.polimi.ingsw.client.clientMessage.NumOfPlayerChoice;
import it.polimi.ingsw.client.clientMessage.UsernameChoice;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.cli.CLI;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.server.serverMessage.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents the client.
 */
public class Client implements ServerMessageHandler{

    /**
     * The socket listener.
     */
    private final SocketListener socketListener;
    /**
     * The view.
     */
    private final View view;
    /**
     * The virtual model.
     */
    private final VirtualModel virtualModel;
    /**
     * The scanner.
     */
    private static final Scanner input = new Scanner(System.in);
    /**
     * The available view type.
     */
    private static final List<String> availableViewType = Arrays.asList("c", "g");

    /**
     * Default constructor, initialize the socket listener and the view.
     *
     * @param view the view
     */
    public Client(View view) {
        this.socketListener= new SocketListener(this);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(socketListener);
        this.view = view;
        this.virtualModel = this.view.getVirtualModel();
    }

    /**
     * This method handles the username request.
     *
     * @param usernameRequest the username request
     */
    public void handle(UsernameRequest usernameRequest){
        String username = view.chooseUsername();
        virtualModel.setMyUsername(username);
        socketListener.send(new UsernameChoice(username));
    }

    /**
     * This method handles the number of player request.
     *
     * @param numOfPlayerRequest the number of player request
     */
    public void handle(NumOfPlayerRequest numOfPlayerRequest) {
        socketListener.send(new NumOfPlayerChoice(view.choosePlayersNumber()));
    }

    /**
     * This method handles the move request.
     *
     * @param moveRequest the move request
     */
    public void handle(MoveRequest moveRequest){
        List<Coordinate> coordinates = view.chooseTiles();
        Integer column = view.chooseColumn();
        socketListener.send(new Move(coordinates,column));
    }

    /**
     * This method handles a custom message.
     *
     * @param customMessage the custom message
     */
    public void handle(CustomMessage customMessage){
        view.showMessage(customMessage.getMessage());
    }

    /**
     * This method handles the start game message.
     *
     * @param startGameMessage the start game message
     */
    public void handle(StartGameMessage startGameMessage){
        view.startGame();
    }

    public void handle(BoardSetMessage boardSetMessage){
        virtualModel.setBoard(boardSetMessage.getGridBoard());
    }
    /**
     * This method handles the board update message.
     *
     * @param boardUpdateMessage the board update message
     */
    public void handle(BoardUpdateMessage boardUpdateMessage) {
        virtualModel.updateBoard(boardUpdateMessage.getCoordinates(), boardUpdateMessage.getChecksum());
    }

    @Override
    public void handle(BoardRefillMessage boardRefillMessage) {
        virtualModel.updateBoard(boardRefillMessage.getGridBoard(), boardRefillMessage.getChecksum());

        // this shouldn't be there
        view.showMessage(boardRefillMessage.getMessage());
    }

    public void handle(StartTurnMessage startTurnMessage){
        virtualModel.updateCurrentPlayerUsername(startTurnMessage.getUsername());

        // this shouldn't be there, the cli should do it based on the previous message
        if (virtualModel.getCurrentPlayerUsername().equals(virtualModel.getMyUsername())){
            view.playTurn();
            view.showGame();
        }else{
            view.showGame();
            view.waitForTurn(virtualModel.getCurrentPlayerUsername());
        }
    }

    public void handle(LibrarySetMessage librarySetMessage) {
        virtualModel.setLibrary(librarySetMessage.getLibraryOwnerUsername(), librarySetMessage.getLibraryGrid());
    }

    public void handle(LibraryUpdateMessage libraryUpdateMessage){
        virtualModel.updateLibraryByUsername(libraryUpdateMessage.getLibraryOwnerUsername(), libraryUpdateMessage.getItemTileTypeList(), libraryUpdateMessage.getColumn(), libraryUpdateMessage.getChecksum());
    }

    public void handle(CommonCardsSetMessage commonCardsSetMessage){
        virtualModel.setCommonGoalCards(commonCardsSetMessage.getCommonGoalCardList());
    }

    @Override
    public void handle(BreakRulesMessage breakRulesMessage) {
       view.showErrorMessage(breakRulesMessage.getType().getDescription());
    }

    @Override
    public void handle(ErrorMessage errorMessage) {
        view.showErrorMessage(errorMessage.getType().getDescription());
    }

    @Override
    public void handle(CommonCardReachMessage commonCardReachMessage) {
        virtualModel.updateCommonCardPoints(commonCardReachMessage.getCommonCardIndex(), commonCardReachMessage.getPoint());
        view.showMessage(commonCardReachMessage.getMessage());
    }

    @Override
    public void handle(EndGameMessage endGameMessage) {
        boolean isWinner = virtualModel.getMyUsername().equals(virtualModel.getLeaderBoard().get(0).getValue0());
        view.endGame(isWinner);
    }

    @Override
    public void handle(EndTurnMessage endTurnMessage) {
        view.stopWaiting();
        // view.showMessage(endTurnMessage.getMessage());
    }

    @Override
    public void handle(DisconnectionMessage disconnectionMessage) {
        view.stopWaiting();
        view.showMessage(disconnectionMessage.getMessage());
    }

    @Override
    public void handle(FirstFullLibraryMessage firstFullLibraryMessage) {
        virtualModel.setFirstFullLibraryUsername(firstFullLibraryMessage.getUsername());

        view.showMessage(firstFullLibraryMessage.getMessage());
    }

    @Override
    public void handle(PersonalCardSetMessage personalCardSetMessage) {
        virtualModel.setPersonalGoalCard(personalCardSetMessage.getLibraryGrid());
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
        System.out.printf(CLIConstants.CONSOLE_ARROW + "Select preferred interface between cli and gui [%sc%s/%sg%s]: ", CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        while (viewType.isEmpty()){
            viewType = Client.input.nextLine().strip().toLowerCase();
            if(!availableViewType.contains(viewType)){
                System.out.printf(CLIConstants.CONSOLE_ARROW + "%sInvalid input%s, please select preferred interface between cli and gui [%sc%s/%sg%s]: ", CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                viewType = "";
            }
        }
        return viewType;
    }

    /**
     * Get the view.
     *
     * @return the view
     */
    public View getView() {
        return view;
    }

    public static void main(String[] Args){
        Client client;
        String viewType = chooseViewType();
        if(viewType.equals("c")){
            System.out.printf(CLIConstants.CONSOLE_ARROW + "You selected cli interface%n");
        }
        else{
            System.out.println("Sorry, gui is not available yet, i'll let you play with the cli :)");
        }
        client = new Client(new CLI());
        client.view.main(Args);
    }
}