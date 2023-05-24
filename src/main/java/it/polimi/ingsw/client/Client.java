package it.polimi.ingsw.client;

import it.polimi.ingsw.client.clientMessage.CheckConnection;
import it.polimi.ingsw.view.events.*;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.cli.CLI;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.server.serverMessage.*;
import it.polimi.ingsw.view.gui.GUI;

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
     * The scanner.
     */
    private static final Scanner input = new Scanner(System.in);
    /**
     * The available view type.
     */
    private static final List<String> availableViewType = Arrays.asList("c", "g");
    /**
     * The default server ip.
     */
    private static String serverIp = "127.0.0.1";
    /**
     * The default server port.
     */
    private static int serverPort = 1235;
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
     * Default constructor, initialize the socket listener and the view.
     */
    public Client(View view) {
        this.view = view;
        this.virtualModel = view.getVirtualModel();
        view.setClient(this);
        this.socketListener= new SocketListener(this, serverIp, serverPort);
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
     * Choose view type between cli and gui.
     *
     * @return view type string
     */
    public static String chooseViewType() {
        String viewType = "";
        System.out.printf(CLIConstants.CONSOLE_ARROW + "Select preferred interface between cli and gui [%sc%s/%sg%s]: ", CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        while (viewType.isEmpty()) {
            viewType = Client.input.nextLine().strip().toLowerCase();
            if (!availableViewType.contains(viewType)) {
                System.out.printf(CLIConstants.CONSOLE_ARROW + "%sInvalid input%s, please select preferred interface between cli and gui [%sc%s/%sg%s]: ", CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                viewType = "";
            }
        }
        return viewType;
    }

    /**
     * Choose the server ip.
     */
    private static void chooseServerIP() {
        System.out.print(CLIConstants.CONSOLE_ARROW + "Please, insert server IP address (press enter to use default): ");
        String serverAddress = Client.input.nextLine().strip();
        if (!serverAddress.isEmpty()) {
            if (isValidIPAddress(serverAddress)) {
                serverIp = serverAddress;
            } else {
                System.out.printf("%sInvalid IP address%s, using the default server ip address: %s%n", CLIConstants.RED_BRIGHT, CLIConstants.RESET, serverIp);
            }
        } else {
            System.out.println("Using the default server ip address: " + CLIConstants.CYAN_BRIGHT + serverIp + CLIConstants.RESET);
        }
    }

    /**
     * Choose the server port.
     */
    private static void chooseServerPort() {
        String serverPortString;
        System.out.printf(CLIConstants.CONSOLE_ARROW + "Please, insert server port (press enter to use default): ");
        try {
            serverPortString = Client.input.nextLine().strip();
            int currentPort = Integer.parseInt(serverPortString);
            if (currentPort < 1024 || currentPort > 65535) {
                serverPort = currentPort;
            } else {
                System.out.printf("%sInvalid port%s, using the default server port: %d%n", CLIConstants.RED_BRIGHT, CLIConstants.RESET, serverPort);
            }
        } catch (NumberFormatException e) {
            System.out.println("Using the default server port: " + CLIConstants.CYAN_BRIGHT + serverPort + CLIConstants.RESET);
        }
    }

    /**
     * This method is used to check if the server ip address is valid.
     *
     * @param ipAddress the ip address
     * @return true if the ip address is valid, false otherwise
     */
    private static boolean isValidIPAddress(String ipAddress) {
        try {
            String[] sections = ipAddress.split("\\.");
            if (sections.length != 4) {
                return false;
            }
            for (String section : sections) {
                int i = Integer.parseInt(section);
                if ((i < 0) || (i > 255)) {
                    return false;
                }
            }
            return !ipAddress.endsWith(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Get the view.
     *
     * @return the view
     */
    public View getView() {
        return view;
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
        if(view instanceof CLI){
           view.chooseMove();
        }
        if(view instanceof GUI){
            view.showGame();
        }
    }

    @Override
    public void handle(Move move) {
        socketListener.send(move);
    }

    /**
     * This method handles the board set message.
     *
     * @param boardSetMessage the board set message
     */
    @Override
    public void handle(BoardSetMessage boardSetMessage) {
        virtualModel.setBoard(boardSetMessage.getGridBoard());
    }

    @Override
    public void handle(EndGameMessage endGameMessage){
        view.showGame();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean isWinner = virtualModel.getMyUsername().equals(virtualModel.getLeaderBoard().get(0).getValue0());
        view.endGame(isWinner);
    }

    @Override
    public void handle(LibrarySetMessage librarySetMessage) {
        virtualModel.setLibrary(librarySetMessage.getLibraryOwnerUsername(), librarySetMessage.getLibraryGrid());
    }

    /**
     * This method handles the personal card set message.
     *
     * @param personalCardSetMessage the personal card set message
     */
    @Override
    public void handle(PersonalCardSetMessage personalCardSetMessage) {
        virtualModel.setPersonalGoalCard(personalCardSetMessage.getLibraryGrid(), personalCardSetMessage.getIndex());
    }

    /**
     * This method handles the common cards set message.
     *
     * @param commonCardsSetMessage the common cards set message
     */
    @Override
    public void handle(CommonCardsSetMessage commonCardsSetMessage) {
        virtualModel.setCommonGoalCards(commonCardsSetMessage.getCommonGoalCardList());
    }

    /**
     * This method handles a custom message.
     *
     * @param customMessage the custom message
     */
    @Override
    public void handle(CustomMessage customMessage) {
        if(view instanceof CLI){
            virtualModel.setServerMessage(customMessage.getMessage());
            view.showMessage(customMessage.getMessage());
        }
        if(view instanceof GUI){
            view.showMessage(customMessage.getMessage());
        }

    }

    /**
     * This method handles the start game message.
     *
     * @param startGameMessage the start game message
     */
    @Override
    public void handle(StartGameMessage startGameMessage)  {
        view.startGame();
    }

    /**
     * This method handles the start turn message.
     *
     * @param startTurnMessage the start turn message
     */
    @Override
    public void handle(StartTurnMessage startTurnMessage) {
        virtualModel.updateCurrentPlayerUsernameIndex(startTurnMessage.getCurrentPlayer());

        if (virtualModel.getCurrentPlayerUsername().equals(virtualModel.getMyUsername())) {
            view.playTurn();
            view.showGame();
        } else {
            view.showGame();
            view.waitForTurn();
        }
    }

    /**
     * This method handles the board refill message.
     *
     * @param boardRefillMessage the board refill message
     */
    @Override
    public void handle(BoardRefillMessage boardRefillMessage) {
        virtualModel.updateBoard(boardRefillMessage.getGridBoard(), boardRefillMessage.getChecksum());
        virtualModel.setServerMessage(boardRefillMessage.getMessage());
    }

    /**
     * This method handles the board update message.
     *
     * @param boardUpdateMessage the board update message
     */
    @Override
    public void handle(BoardUpdateMessage boardUpdateMessage) {
        virtualModel.updateBoard(boardUpdateMessage.getCoordinates(), boardUpdateMessage.getChecksum());
    }

    /**
     * This method handles the library update message.
     *
     * @param libraryUpdateMessage the library update message
     */
    @Override
    public void handle(LibraryUpdateMessage libraryUpdateMessage) {
        virtualModel.updateLibraryByUsername(libraryUpdateMessage.getLibraryOwnerUsername(), libraryUpdateMessage.getItemTileTypeList(), libraryUpdateMessage.getColumn(), libraryUpdateMessage.getChecksum());
    }

    /**
     * This method handles the common card reach message.
     *
     * @param commonCardReachMessage the common card reach message
     */
    @Override
    public void handle(CommonCardReachMessage commonCardReachMessage) {
        virtualModel.updateCommonCardPoints(commonCardReachMessage.getCommonCardIndex(), commonCardReachMessage.getPoint());
        virtualModel.setServerMessage(commonCardReachMessage.getMessage());
    }

    /**
     * This method handles the first full library message.
     *
     * @param firstFullLibraryMessage the first full library message
     */
    @Override
    public void handle(FirstFullLibraryMessage firstFullLibraryMessage) {
        virtualModel.setServerMessage(firstFullLibraryMessage.getMessage());
    }

    /**
     * This method handles the points update message.
     *
     * @param pointsUpdateMessage the points update message
     */
    @Override
    public void handle(PointsUpdateMessage pointsUpdateMessage) {
        virtualModel.updatePointsByUsername(pointsUpdateMessage.getUsername(), pointsUpdateMessage.getPoints());
    }

    /**
     * This method handles the break rules message.
     *
     * @param breakRulesMessage the break rules message
     */
    @Override
    public void handle(BreakRulesMessage breakRulesMessage) {
        view.showErrorMessage(breakRulesMessage.getType().getDescription());
    }

    /**
     * This method handles the error message.
     *
     * @param errorMessage the error message
     */
    @Override
    public void handle(ErrorMessage errorMessage) {
        view.showErrorMessage(errorMessage.getType().getDescription());
    }

    /**
     * This method handles the disconnection message.
     *
     * @param disconnectionMessage the disconnection message
     */
    @Override
    public void handle(DisconnectionMessage disconnectionMessage) {
        view.stopWaiting();
        virtualModel.setServerMessage(disconnectionMessage.getMessage());
        view.showMessage(disconnectionMessage.getMessage());
    }

    /**
     * This method handles the connection message.
     *
     * @param connectionMessage the connection message
     */
    @Override
    public void handle(ConnectionMessage connectionMessage) {
    }

    @Override
    public void handle(PlayerOrderSetMessage playerOrderSetMessage) {
        virtualModel.setPlayerIndex(playerOrderSetMessage.getPlayerOrder());
    }

    @Override
    public void handle(CheckConnectionRequest checkConnectionRequest) {
            socketListener.send(new CheckConnection());
    }

    @Override
    public void handle(ChatServerMessage chatServerMessage) {
        view.showChatMessage(chatServerMessage.getSender(), chatServerMessage.getMessageText());
    }

    public static void main(String[] Args){
        chooseServerIP();
        chooseServerPort();
        String viewType = "";
        while(viewType.isBlank()){
            viewType = chooseViewType();
            switch (viewType) {
                case "c" -> {
                    System.out.printf(CLIConstants.CONSOLE_ARROW + "You selected cli interface%n");
                    new Client(new CLI()).view.main(null);
                }
                case "g" -> {
                    System.out.printf(CLIConstants.CONSOLE_ARROW + "You selected gui interface%n");
                    new Client(new GUI()).view.main(null);
                }
                default -> {
                    System.out.printf(CLIConstants.CONSOLE_ARROW + "Invalid input%n");
                    viewType = "";
                }
            }
        }
    }
}