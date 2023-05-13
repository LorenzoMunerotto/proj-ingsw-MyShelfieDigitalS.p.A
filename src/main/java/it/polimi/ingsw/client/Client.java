package it.polimi.ingsw.client;

import it.polimi.ingsw.chat.ChatMessage;
import it.polimi.ingsw.client.clientMessage.Move;
import it.polimi.ingsw.client.clientMessage.NumberOfPLayerChoice;
import it.polimi.ingsw.client.clientMessage.UsernameChoice;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.cli.CLI;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.server.serverMessage.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents the client.
 */
public class Client implements ServerMessageHandler {

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
     *
     * @param view the view
     */
    public Client(View view) {
        this.socketListener = new SocketListener(this, serverIp, serverPort);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(socketListener);
        this.view = view;
        this.virtualModel = this.view.getVirtualModel();
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
        System.out.print(CLIConstants.CONSOLE_ARROW + "Please, insert server IP address: ");
        String serverAddress = Client.input.nextLine().strip();
        if (!serverAddress.isEmpty()) {
            serverIp = serverAddress;
        } else {
            System.out.println(CLIConstants.CONSOLE_ARROW + "Using the default server ip address " + CLIConstants.CYAN_BRIGHT + serverIp + CLIConstants.RESET);
        }
    }

    /**
     * Choose the server port.
     */
    private static void chooseServerPort() {
        String serverPortString;
        System.out.printf(CLIConstants.CONSOLE_ARROW + "Please, specify server port: ");
        try {
            serverPortString = Client.input.nextLine().strip();
            int currentPort = Integer.parseInt(serverPortString);
            if (currentPort < 1024 || currentPort > 65535) {
                serverPort = currentPort;
            } else {
                System.out.println(CLIConstants.CONSOLE_ARROW + "Using the default server server port " + CLIConstants.CYAN_BRIGHT + serverPort + CLIConstants.RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(CLIConstants.CONSOLE_ARROW + "Using the default server server port " + CLIConstants.CYAN_BRIGHT + serverPort + CLIConstants.RESET);
        }
    }

    /**
     * This method launches the chat client terminal.
     *
     * @throws IOException the io exception
     */
    private static void launchChat() throws IOException {
        String command;
        String[] array;

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            command = "cmd.exe";
            array = new String[]{command, "/c", "start", "java", "-cp", System.getProperty("java.class.path"), "it.polimi.ingsw.chat.Chat"};
        } else {
            command = "/bin/bash";
            array = new String[]{command, "-c", "gnome-terminal", "--", "java", "-cp", System.getProperty("java.class.path"), "it.polimi.ingsw.chat.Chat"};
        }
        ProcessBuilder processBuilder = new ProcessBuilder(array);
        processBuilder.start();
    }

    /**
     * Main method.
     *
     * @param Args the args
     * @throws IOException the io exception
     */
    public static void main(String[] Args) throws IOException {
        Client client;
        chooseServerIP();
        chooseServerPort();
        String viewType = chooseViewType();
        if (viewType.equals("c")) {
            System.out.printf(CLIConstants.CONSOLE_ARROW + "You selected cli interface%n");
            /*try {
                launchChat();
            } catch (IOException e) {
                System.err.println("Error launching chat client terminal: " + e.getMessage());
            }*/
        } else {
            System.out.println("Sorry, gui is not available yet, i'll let you play with the cli :)");
        }
        client = new Client(new CLI());
        client.view.main(Args);
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
    public void handle(UsernameRequest usernameRequest) {
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
        socketListener.send(new NumberOfPLayerChoice(view.choosePlayersNumber()));
    }

    /**
     * This method handles the move request.
     *
     * @param moveRequest the move request
     */
    public void handle(MoveRequest moveRequest) {
        List<Coordinate> coordinates = view.chooseTiles();
        Integer column = view.chooseColumn();
        socketListener.send(new Move(coordinates, column));
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

    /**
     * This method handles the library set message.
     *
     * @param librarySetMessage the library set message
     */
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
        virtualModel.setServerMessage(customMessage.getMessage());
    }

    /**
     * This method handles the start game message.
     *
     * @param startGameMessage the start game message
     */
    @Override
    public void handle(StartGameMessage startGameMessage) {
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
     * This method handles the end game message.
     *
     * @param endGameMessage the end game message
     */
    @Override
    public void handle(EndGameMessage endGameMessage) {
        boolean isWinner = virtualModel.getMyUsername().equals(virtualModel.getClientUsernamePoints().get(0).getValue0());
        view.endGame(isWinner);
        System.exit(0);
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
        System.exit(0);
    }

    /**
     * This method handles the connection message.
     *
     * @param connectionMessage the connection message
     */
    @Override
    public void handle(ConnectionMessage connectionMessage) {
    }

    /**
     * This method handles the chat message.
     *
     * @param chatMessage the chat message
     */
    @Override
    public void handle(ChatMessage chatMessage) {
        view.showChatMessage(chatMessage.getSender(), chatMessage.getContent());
    }
}