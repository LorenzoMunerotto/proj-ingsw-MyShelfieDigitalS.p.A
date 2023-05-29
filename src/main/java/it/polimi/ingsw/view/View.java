package it.polimi.ingsw.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.view.cli.CLIConstants;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is the abstract class for the view.
 */
public interface View  {

     int MIN_PLAYERS_NUMBER = 2;
    /**
     * It is the maximum number of players in the game.
     */
    int MAX_PLAYERS_NUMBER = 4;


     void setClient(Client client);

     VirtualModel getVirtualModel();


    void setUsername(String username);


    void setPlayersNumber(int playersNumber);

    /**
     * This method checks if the username is valid.
     *
     * @param username is the username of the player
     * @return true if the username is valid, false otherwise
     */
    default boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile(CLIConstants.USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    /**
     * This method checks if the coordinates are valid.
     *
     * @param coordinates are the coordinates of the tile chosen by the player
     * @return true if the coordinates are valid, false otherwise
     */
    default boolean isCoordinatesValid(String coordinates) {
        Pattern pattern = Pattern.compile(CLIConstants.COORDINATES_REGEX);
        Matcher matcher = pattern.matcher(coordinates);
        return matcher.matches();
    }

    /**
     * This method is the main method of the view.
     *
     * @param args are the arguments of the main method
     */
     void main(String[] args);

     void chooseUsername();

     void choosePlayersNumber();

     void chooseMove();

     void startGame();

    /**
     * This method is the method that shows the game.
     */
    void showGame();
    /**
     * This method is the method that waits for the turn of the player.
     */
    void waitForTurn();
    /**
     * This method is the method that plays the turn of the player.
     */
     void playTurn();
    /**
     * This method is the method that ends the game.
     */
     void endGame(Boolean isWinner);
    /**
     * This method is the method that manages the turn of the player.
     */
     void showErrorMessage(String errorMessage);
    /**
     * Stops the waiting thread.
     */
     void stopWaiting();
    /**
     * Shows a message.
     */
     void showMessage(String message);

     void showChatMessage(String sender, String messageText);
}