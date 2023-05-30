package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.events.Move;
import it.polimi.ingsw.view.events.NumOfPlayerChoice;
import it.polimi.ingsw.view.events.UsernameChoice;
import jline.console.ConsoleReader;

import java.io.IOException;

/**
 * This class represents the CLI view of the game.
 */
public class CLI implements View {

    /**
     * It is the reader used to read the user input.
     */
    private static ConsoleReader consoleReader;
    /**
     * It is the virtual model of the game.
     */
    private final VirtualModel virtualModel;
    /**
     * It is the drawer used to draw the game.
     */
    private final CLIDrawer drawer;
    /**
     * It is the parser used to parse the user input.
     */
    private final CLIParser parser;
    /**
     * It is the client that is using this view.
     */
    private Client client;
    /**
     * It is a thread that is used for waiting.
     */
    private Thread currentViewThread;

    /**
     * Default constructor, initializes the drawer.
     */
    public CLI() throws IOException {
        this.virtualModel = new VirtualModel();
        drawer = new CLIDrawer(virtualModel);
        parser = new CLIParser();
        consoleReader = new ConsoleReader();
    }

    /**
     * Clears the console.
     */
    public static void clear() {
        try {
            String os = System.getProperty("os.name");
            ProcessBuilder processBuilder;
            if (os.contains("Windows"))
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            else
                processBuilder = new ProcessBuilder("clear");
            processBuilder.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.printf("%n%sError while clearing console (%s)%s%n",
                    CLIConstants.RED_BRIGHT, e.getMessage(), CLIConstants.RESET);
            Thread.currentThread().interrupt();
            System.exit(1);
        }
    }

    /**
     * Get the virtual model of the game.
     *
     * @return the virtual model of the game
     */
    @Override
    public VirtualModel getVirtualModel() {
        return virtualModel;
    }

    /**
     * This is a ReadLine that can be interrupted, so the client isn't blocked when waiting for user input
     *
     * @return the string inserted by the user
     * @throws InterruptedException if the thread is interrupted
     * @throws IOException          if there is an error while reading the input
     */
    private String interruptionReadLine()
            throws InterruptedException, IOException {
        consoleReader.setExpandEvents(false);
        String line;
        try {
            line = consoleReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return line;
    }

    /**
     * Asks the user to choose his username.
     */
    @Override
    public void chooseUsername() {
        Runnable chooseUsernameThread = () -> {
            try {
                String username = "";
                System.out.printf(CLIConstants.CONSOLE_ARROW + "Please insert your username [%s4%s-%s20%s alphanumeric characters]: ", CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                while (username.isBlank()) {

                    username = interruptionReadLine();
                    if (!isUsernameValid(username)) {
                        username = "";
                        System.out.printf(CLIConstants.CONSOLE_ARROW + "%sInvalid username%s, please try again [%s4%s-%s20%s alphanumeric characters]: ",
                                CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                    }
                }
                client.handle(new UsernameChoice(username));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        };

        StopSetStartCurrentViewThread(chooseUsernameThread);
    }

    /**
     * Asks the user to choose the number of players for the game.
     */
    @Override
    public void choosePlayersNumber() {
        Runnable choosePlayersNumberThread = () -> {
            try {
                int playersNumber = 0;
                String playersNumberString;
                System.out.printf(CLIConstants.CONSOLE_ARROW + "Please insert exact number of players for the game [%s2%s-%s4%s]: ",
                        CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                while (playersNumber < MIN_PLAYERS_NUMBER || playersNumber > MAX_PLAYERS_NUMBER) {
                    try {
                        playersNumberString = interruptionReadLine();
                        playersNumber = Integer.parseInt(playersNumberString);
                        if (playersNumber < MIN_PLAYERS_NUMBER || playersNumber > MAX_PLAYERS_NUMBER)
                            throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        System.out.printf(CLIConstants.CONSOLE_ARROW + "%sInvalid input%s, insert exact number of players for the game [%s2%s-%s4%s]: ",
                                CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                    }
                }
                client.handle(new NumOfPlayerChoice(playersNumber));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        StopSetStartCurrentViewThread(choosePlayersNumberThread);
    }


    /**
     * Asks the user to choose the item tiles to grab from the board and the column.
     */
    public void chooseMove() {
        Runnable chooseMoveThread = () -> {
            try {
                String coordinates = "";
                System.out.printf(CLIConstants.CONSOLE_ARROW + "Please insert the coordinates of the tile you want to place [%sA1%s-%sI9%s]: ",
                        CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                while (coordinates.isBlank()) {
                    coordinates = interruptionReadLine().toUpperCase();
                    if (!isCoordinatesValid(coordinates)) {
                        coordinates = "";
                        System.out.printf(CLIConstants.CONSOLE_ARROW + "> %sInvalid input%s, please insert the coordinates of the tile you want to place [%sA1%s-%sI9%s]: ",
                                CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                    }
                }

                int column = 0;
                String columnString;
                System.out.printf(CLIConstants.CONSOLE_ARROW + "Please insert the column of the library where you want to place the tiles [%s1%s-%s5%s]: ",
                        CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                while (column < 1 || column > 5) {
                    try {
                        columnString = interruptionReadLine().strip();
                        column = Integer.parseInt(columnString);
                        if (column < 1 || column > 5)
                            throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        System.out.printf(CLIConstants.CONSOLE_ARROW + "%sInvalid input%s, please insert the column of the library where you want to place the tiles [%s1%s-%s5%s]: ",
                                CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                    }
                }
                client.handle(new Move(parser.parseCoordinates(coordinates), parser.getColumnKey(column)));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        };

        StopSetStartCurrentViewThread(chooseMoveThread);
    }


    /**
     * Thread that prints a clock while waiting for other players to play their turn.
     * maybe we can use it also while a client wait to other to connect to the server
     */
    @Override
    public void waitForTurn() {
        Runnable waitForTurnThread = () -> {
            int index = 0;

            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\rWaiting for your turn..." + CLIConstants.BLUE_BRIGHT + CLIConstants.LOADING_ANIMATIONS[index] + CLIConstants.RESET);
                index = (index + 1) % CLIConstants.LOADING_ANIMATIONS.length;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        StopSetStartCurrentViewThread(waitForTurnThread);
    }

    /**
     * Stops the waiting thread.
     */
    public void stopWaiting() {
        if (currentViewThread != null && currentViewThread.isAlive()) {
            currentViewThread.interrupt();
            currentViewThread = null;
        }
        System.out.println();
    }

    /**
     * This method change the CurrentViewThread.
     *
     * @param runnable the runnable to be executed
     */
    private void StopSetStartCurrentViewThread(Runnable runnable) {
        if (currentViewThread != null && currentViewThread.isAlive()) {
            currentViewThread.interrupt();
            currentViewThread = null;
        }
        currentViewThread = new Thread(runnable);
        currentViewThread.start();
    }

    /**
     * Starts the game and prints the objects of the game.
     */
    @Override
    public void startGame() {
        this.stopWaiting();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        CLI.clear();
    }

    /**
     * Shows the updated game.
     */
    @Override
    public void showGame() {
        this.stopWaiting();
        CLI.clear();
        try {
            this.drawer.printGame();
        } catch (Exception e) {
            System.out.println("Error while showing game");
        }
    }

    /**
     * Plays the turn of the current player.
     */
    @Override
    public void playTurn() {
    }

    /**
     * It is the end of the game.
     * It prints the leaderboard and the winner.
     */
    @Override
    public void endGame(Boolean isWinner) {
        System.out.println(this.drawer.getLeaderboardAsString(isWinner));
    }

    /**
     * Shows an error message.
     */
    @Override
    public void showErrorMessage(String errorMessage) {
        System.out.printf("%s%sInvalid input!%s %s%n", CLIConstants.CONSOLE_ARROW, CLIConstants.RED_BRIGHT, CLIConstants.RESET, errorMessage);
    }

    /**
     * Shows a message.
     */
    @Override
    public void showMessage(String message) {
        if (message != null) {
            System.out.println(CLIConstants.GREEN_BRIGHT + "SERVER MESSAGE: " + CLIConstants.RESET + message);
        }
    }

    /**
     * Main method of the cli.
     *
     * @param args the arguments of the main method
     */
    @Override
    public void main(String[] args) {
    }

    /**
     * Sets the client.
     *
     * @param client the client
     */
    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Sets the username.
     *
     * @param username the username
     */
    @Override
    public void setUsername(String username) {
    }

    /**
     * Sets the number of players.
     *
     * @param playersNumber the number of players
     */
    @Override
    public void setPlayersNumber(int playersNumber) {
    }

    /**
     * Shows a chat message.
     *
     * @param sender  the sender of the message
     * @param message the message
     */
    @Override
    public void showChatMessage(String sender, String message) {
    }

    /**
     * Close the game.
     */
    @Override
    public void closeGame() {
        System.exit(0);
    }
}