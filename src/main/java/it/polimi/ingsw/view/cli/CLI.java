package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.View;
import it.polimi.ingsw.model.gameEntity.Coordinate;

import java.util.List;
import java.util.Scanner;

/**
 * This class represents the CLI view of the game.
 */
public class CLI extends View {
    /**
     * It is the scanner used to read the user input.
     */
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * It is the drawer used to draw the game.
     */
    private final CLIDrawer drawer;
    /**
     * It is the parser used to parse the user input.
     */
    private final CLIParser parser;
    /**
     * It is a thread that is used for waiting.
     */
    private Thread waitingThread;

    /**
     * Default constructor, initializes the drawer.
     */
    public CLI() {
        drawer = new CLIDrawer(this.virtualModel);
        parser = new CLIParser();
    }

    /**
     * Clears the console.
     */
    public static void clear() {
        /*TERM environment variable not set.
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
        }*/
    }

    /**
     * Asks the user to choose his username.
     */
    @Override
    public String chooseUsername() {
        this.username = "";
        System.out.print(CLIConstants.CONSOLE_ARROW + "Please insert your username: ");
        while (this.username.isBlank()) {
            this.username = CLI.scanner.nextLine().strip();
            if (!isUsernameValid(this.username)) {
                this.username = "";
                System.out.printf(CLIConstants.CONSOLE_ARROW + "%sInvalid username%s, please try again: ",
                        CLIConstants.RED_BRIGHT, CLIConstants.RESET);
            }
        }
        return this.username;
    }

    /**
     * Asks the user to choose the number of players for the game.
     */
    @Override
    public Integer choosePlayersNumber() {
        this.playersNumber = 0;
        String playersNumberString;
        System.out.printf(CLIConstants.CONSOLE_ARROW + "Please insert exact number of players for the game [%s2%s-%s4%s]: ",
                CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        while (this.playersNumber < MIN_PLAYERS_NUMBER || this.playersNumber > MAX_PLAYERS_NUMBER) {
            try {
                playersNumberString = CLI.scanner.nextLine().strip();
                this.playersNumber = Integer.parseInt(playersNumberString);
                if (playersNumber < MIN_PLAYERS_NUMBER || playersNumber > MAX_PLAYERS_NUMBER)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.printf(CLIConstants.CONSOLE_ARROW + "%sInvalid input%s, insert exact number of players for the game [%s2%s-%s4%s]: ",
                        CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
            }
        }
        return this.playersNumber;
    }


    /**
     * Asks the user to choose the item tiles to grab from the board.
     */
    public List<Coordinate> chooseTiles() {
        this.coordinates = "";
        System.out.printf(CLIConstants.CONSOLE_ARROW + "Please insert the coordinates of the tile you want to place [%sA1%s-%sI9%s]: ",
                CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        while (this.coordinates.isBlank()) {
            coordinates = CLI.scanner.nextLine().strip().toUpperCase();
            if (!isCoordinatesValid(coordinates)) {
                this.coordinates = "";
                System.out.printf(CLIConstants.CONSOLE_ARROW + "> %sInvalid input%s, please insert the coordinates of the tile you want to place [%sA1%s-%sI9%s]: ",
                        CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
            }
        }
        return parser.parseCoordinates(coordinates);
    }

    /**
     * Asks the user to choose the column of the library where to place the tiles.
     */
    public Integer chooseColumn() {
        this.column = 0;
        String columnString;
        System.out.printf(CLIConstants.CONSOLE_ARROW + "Please insert the column of the library where you want to place the tiles [%s1%s-%s5%s]: ",
                CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        while (this.column < 1 || this.column > 5) {
            try {
                columnString = CLI.scanner.nextLine().strip();
                this.column = Integer.parseInt(columnString);
                if (this.column < 1 || this.column > 5)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.printf(CLIConstants.CONSOLE_ARROW + "%sInvalid input%s, please insert the column of the library where you want to place the tiles [%s1%s-%s5%s]: ",
                        CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
            }
        }
        return parser.getColumnKey(this.column);
    }

    /**
     * Thread that prints a clock while waiting for other players to play their turn.
     * maybe we can use it also while a client wait to other to connect to the server
     */
    @Override
    public void waitForTurn() {
        this.waitingThread = new Thread(() -> {
            int index = 0;

            while (!Thread.currentThread().isInterrupted()) {
                System.out.printf("\rWaiting for other players to play their turn...%s%s%s%n",CLIConstants.BLUE_BRIGHT,CLIConstants.clockChars[index],CLIConstants.RESET);
                index = (index + 1) % CLIConstants.clockChars.length;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        waitingThread.start();
    }

    /**
     * Stops the waiting thread.
     */
    public void stopWaiting() {
        if (this.waitingThread != null) {
            this.waitingThread.interrupt();
            this.waitingThread = null;
        }
    }

    /**
     * Starts the game and prints the objects of the game.
     */
    @Override
    public void startGame(){
        this.stopWaiting();
        CLI.clear();
        System.out.printf(CLIConstants.GREEN_BRIGHT + "The game has started!%n" + CLIConstants.RESET);
       /* try {
            this.drawer.printGame();
            this.previousMessage = MessageType.START_GAME;
        } catch (Exception e) {
            System.out.println("Error while starting game");
        }
        */
    }

    /**
     * Shows the updated game.
     */
    @Override
    public void showGame(){
        this.stopWaiting();
        CLI.clear();
        System.out.printf(CLIConstants.GREEN_BRIGHT + "The game was updated!%n" + CLIConstants.RESET);
        try{
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
        System.out.println(CLIConstants.CONSOLE_ARROW + "It is your turn!");
    }

    /**
     * It is the end of the game.
     * It prints the leaderboard and the winner.
     */
    @Override
    public void endGame(Boolean isWinner) {
        this.drawer.printLeaderBoard(isWinner);

    }

    /**
     * Shows an error message.
     */
    @Override
    public void showErrorMessage(String errorMessage) {
        System.out.printf("%s%sInvalid input!%s %s%n", CLIConstants.CONSOLE_ARROW, CLIConstants.RED_BRIGHT, CLIConstants.RESET, errorMessage);
    }

    /**
     * Main method of the cli.
     *
     * @param args the arguments of the main method
     */
    @Override
    public void main(String[] args) {
        CLI.clear();
        /*
        String input = "";
        System.out.printf(CLIAssets.output + "Do you want to create a new game or join an already created one? [%sc%s/%sj%s]: ",
                CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
        while (input.isEmpty()) {
            input = CLI.scanner.nextLine().strip().toUpperCase();
            switch (input) {
                case "C" -> this.createGame();
                case "J" -> this.joinGame();
                default -> {
                    System.out.printf(CLIAssets.output + "%sInvalid input%s, do you want to create a new game or join an already created one? [%sc%s/%sj%s]: ",
                            CLIConstants.RED_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET, CLIConstants.CYAN_BRIGHT, CLIConstants.RESET);
                    input = "";
                }
            }
        }*/
    }

    @Override
    public void showMessage(String message) {
        if (message!=null) {
            System.out.println(message);
        }
    }
}
