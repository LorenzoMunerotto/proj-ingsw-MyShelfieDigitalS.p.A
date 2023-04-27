package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameState.Exceptions.GameStartedException;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import it.polimi.ingsw.model.gameState.Exceptions.InvalidNumOfPlayers;
import it.polimi.ingsw.model.gameState.Exceptions.UsernameAlreadyExistsException;
import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI extends View {
    private static final Scanner scanner = new Scanner(System.in);

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
                    CLIColors.RED_BRIGHT, e.getMessage(), CLIColors.RESET);
            Thread.currentThread().interrupt();
            System.exit(1);
        }
    }

    private void chooseUsername() {
        this.username = "";
        System.out.print(CLIAssets.output + "Please insert your username: ");
        while (this.username.isBlank()) {
            this.username = CLI.scanner.nextLine().strip();
            if (!isUsernameValid(this.username)) {
                this.username = "";
                System.out.printf(CLIAssets.output + "%sInvalid username%s, please try again: ",
                        CLIColors.RED_BRIGHT, CLIColors.RESET);
            }
        }
    }

    private void choosePlayersNumber() {
        this.playersNumber = 0;
        String playersNumberString;
        System.out.printf(CLIAssets.output + "Please insert exact number of players for the game [%s2%s-%s4%s]: ",
                CLIColors.CYAN_BRIGHT, CLIColors.RESET, CLIColors.CYAN_BRIGHT, CLIColors.RESET);
        while (this.playersNumber < MIN_PLAYERS_NUMBER || this.playersNumber > MAX_PLAYERS_NUMBER) {
            try {
                playersNumberString = CLI.scanner.nextLine().strip();
                this.playersNumber = Integer.parseInt(playersNumberString);
                if (playersNumber < MIN_PLAYERS_NUMBER || playersNumber > MAX_PLAYERS_NUMBER)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.printf(CLIAssets.output + "%sInvalid input%s, insert exact number of players for the game [%s2%s-%s4%s]: ",
                        CLIColors.RED_BRIGHT, CLIColors.RESET, CLIColors.CYAN_BRIGHT, CLIColors.RESET, CLIColors.CYAN_BRIGHT, CLIColors.RESET);
            }
        }
    }

    private void chooseTiles() {
        this.coordinates = "";
        System.out.printf(CLIAssets.output + "Please insert the coordinates of the tile you want to place [%sA1%s-%sI9%s]: ",
                CLIColors.CYAN_BRIGHT, CLIColors.RESET, CLIColors.CYAN_BRIGHT, CLIColors.RESET);
        while (this.coordinates.isBlank()) {
            coordinates = CLI.scanner.nextLine().strip().toUpperCase();
            if (!isCoordinatesValid(coordinates)) {
                this.coordinates = "";
                System.out.printf(CLIAssets.output + "> %sInvalid input%s, please insert the coordinates of the tile you want to place [%sA1%s-%sI9%s]: ",
                        CLIColors.RED_BRIGHT, CLIColors.RESET, CLIColors.CYAN_BRIGHT, CLIColors.RESET, CLIColors.CYAN_BRIGHT, CLIColors.RESET);
            }
        }
    }

    private void chooseColumn() {
        this.column = 0;
        String columnString;
        System.out.printf(CLIAssets.output + "Please insert the column of the library where you want to place the tiles [%s1%s-%s5%s]: ",
                CLIColors.CYAN_BRIGHT, CLIColors.RESET, CLIColors.CYAN_BRIGHT, CLIColors.RESET);
        while (this.column < 1 || this.column > 5) {
            try {
                columnString = CLI.scanner.nextLine().strip();
                this.column = Integer.parseInt(columnString);
                if (this.column < 1 || this.column > 5)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.printf(CLIAssets.output + "%sInvalid input%s, please insert the column of the library where you want to place the tiles [%s1%s-%s5%s]: ",
                        CLIColors.RED_BRIGHT, CLIColors.RESET, CLIColors.CYAN_BRIGHT, CLIColors.RESET, CLIColors.CYAN_BRIGHT, CLIColors.RESET);
            }
        }
    }

    // error
    // messages from other clients
    // winning message
    // print leaderboard

    protected void createGame() throws IllegalUsernameException, UsernameAlreadyExistsException, GameStartedException, InvalidNumOfPlayers {
        List<Player> players = new ArrayList<>();
        Game game = new Game();
        GameData gameData = game.getGameData();
        System.out.println(CLIAssets.MYSHELFIE_TITLE);
        this.chooseUsername();
        players.add(new Player(this.username));
        this.choosePlayersNumber();
        gameData.setNumOfPlayers(this.playersNumber);
        for (int i = 1; i < this.playersNumber; i++) {
            this.chooseUsername();
            players.add(new Player(this.username));
        }
        for (Player player : players) {
            gameData.addPlayer(player);
        }
        game.boardInitialization();
        game.assignAllPersonalCard();
        CLIDrawer drawer = new CLIDrawer(gameData);
        for (int i = 0; i < this.playersNumber; i++) {
            System.out.println("Player " + CLIColors.PURPLE_BRIGHT + gameData.getCurrentPlayer().getUsername() + CLIColors.RESET + " turn");
            drawer.printSeparator();
            drawer.printGame();
            this.chooseTiles();
            gameData.nextPlayer();
        }
        // send to server somehow and check if username is available

    }

    protected void joinGame() {
        this.chooseUsername();
        // send to server somehow
        // check if username is available
        // check if there is a game to join
        // check if there is space in the game
    }
}
