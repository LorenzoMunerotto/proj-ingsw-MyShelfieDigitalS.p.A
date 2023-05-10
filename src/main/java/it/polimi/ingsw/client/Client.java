package it.polimi.ingsw.client;

import it.polimi.ingsw.listener.Event;
import it.polimi.ingsw.listener.Listener;
import it.polimi.ingsw.view.events.*;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.cli.CLI;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.server.serverMessage.*;
import it.polimi.ingsw.view.gui.GUI;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents the client.
 */
public class Client implements ServerMessageHandler,  ViewChangeEventHandler {

    /**
     * The socket listener.
     */
    private final SocketListener socketListener;
    /**
     * The view.
     */
    private View view;
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
     *
     */
    public Client() {
        this.virtualModel = new VirtualModel();
        this.socketListener= new SocketListener(this);
    }

    public void setView(View view){
        this.view = view;
        view.setVirtualModel(virtualModel);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(socketListener);
    }

    public VirtualModel getVirtualModel() {
        return virtualModel;
    }

    @Override
    public void update(ViewEvent viewEvent) {
        viewEvent.accept(this);
    }

    /**
     * This method handles the username request.
     *
     * @param usernameRequest the username request
     */
    public void handle(UsernameRequest usernameRequest){
        view.chooseUsername();
    }

    public void handle(UsernameChoice usernameChoice){
        virtualModel.setMyUsername(usernameChoice.getUsername());
        socketListener.send(usernameChoice);
    }

    /**
     * This method handles the number of player request.
     *
     * @param numOfPlayerRequest the number of player request
     */
    public void handle(NumOfPlayerRequest numOfPlayerRequest) {
        view.choosePlayersNumber();

    }

    @Override
    public void handle(NumOfPlayerChoice numOfPlayerChoice) {
        socketListener.send(numOfPlayerChoice);
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

    @Override
    public void handle(Move move) {

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
    public void handle(StartGameMessage startGameMessage) throws IOException {
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
        view.showMessage(endTurnMessage.getMessage());
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
        virtualModel.setPersonalGoalCard(personalCardSetMessage.getLibraryGrid(), personalCardSetMessage.getIndex());
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

            new CLI(new Client()).main(Args);
        }
        else{
            System.out.printf(CLIConstants.CONSOLE_ARROW + "You selected gui interface%n");
            //client.setView(new GUI());
        }

    }


}