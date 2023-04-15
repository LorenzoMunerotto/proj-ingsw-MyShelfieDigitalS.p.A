package it.polimi.ingsw.terminal;

import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameState.GameData;
import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    final GameData gameData;

    public Controller(GameData gameData) {
        this.gameData = gameData;
    }

    public void manageNewUsers() {

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(input);
        
        while (gameData.getCurrentNumOfPlayers() == 0) {
            try {
                System.out.print("You are the first player, please select your username: ");
                String username = keyboard.readLine();
                Player newPlayer = new Player(username);
                gameData.addPlayer(newPlayer);
                System.out.println("Welcome " + username + " to the game!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (gameData.getNumOfPlayers() == 0) {
            try {
                System.out.print("Please, select the number of player for this game: ");
                int numOfPlayer = Integer.parseInt(keyboard.readLine());
                gameData.setNumOfPlayers(numOfPlayer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (gameData.getCurrentNumOfPlayers() != gameData.getNumOfPlayers()) {
            try {
                System.out.print("You are the player number " + (gameData.getCurrentNumOfPlayers() + 1) + " of " + gameData.getNumOfPlayers() + ", please select your username: ");
                String username = keyboard.readLine();
                Player newPlayer = new Player(username);
                gameData.addPlayer(newPlayer);
                System.out.println("Welcome " + username + " to the game!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Pair<List<Pair<Integer, Integer>>, Integer> takePlayerInput() {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(input);

        List<Pair<Integer, Integer>> listOfCoordinate = new ArrayList<>();
        int column;
        
        System.out.println("Please insert the coordinates of the desired tiles in the order of insertion and the column of the library where to insert them.");
        System.out.println("Note that you can pick from 1 to 3 tiles!");
        System.out.println("While typing follow this template: row1column1-row2column2-row3column3|column");
        try {
            String commands = keyboard.readLine();

            // pars the commands
            String newStr;
            newStr = commands.replaceAll(" ", "");
            newStr = newStr.replaceAll("\n", "");
            newStr = newStr.replaceAll("\"", "");
            String[] new_commands = newStr.split("\\|");

            String[] coordinates = new_commands[0].split("-");

            column = Integer.parseInt(new_commands[1]);

            for (String coordinate : coordinates) {

                int row = Integer.parseInt(String.valueOf(coordinate.charAt(0)));
                int col = Integer.parseInt(String.valueOf(coordinate.charAt(1)));

                Pair<Integer, Integer> pair = new Pair<>(row, col);
                listOfCoordinate.add(pair);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Your choice is not valid");
        }

        return new Pair<>(listOfCoordinate, column);
    }
}
