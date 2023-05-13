package it.polimi.ingsw.view;

import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.view.cli.CLIConstants;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is the abstract class for the view.
 */
public abstract class View {

    /**
     * It is the username of the player.
     */
    protected String username;
    /**
     * It is the number of players in the game chosen by the first player.
     */
    protected int playersNumber;
    /**
     * It is the coordinates of the tile chosen by the player.
     */
    protected String coordinates;
    /**
     * It is the column of the library chosen by the player.
     */
    protected int column;
    /**
     * It is the minimum number of players in the game.
     */
    protected int MIN_PLAYERS_NUMBER = 2;
    /**
     * It is the maximum number of players in the game.
     */
    protected int MAX_PLAYERS_NUMBER = 4;
    /**
     * It is the virtual model.
     * Still don't know when and where to initialize it.
     */
    protected VirtualModel virtualModel;

    /**
     * This constructor initializes the virtual model and the controller.
     * Maybe.
     */
    public View() {
        this.virtualModel = new VirtualModel();
    }

    /**
     * This method checks if the username is valid.
     *
     * @param username is the username of the player
     * @return true if the username is valid, false otherwise
     */
    protected static boolean isUsernameValid(String username) {
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
    protected static boolean isCoordinatesValid(String coordinates) {
        Pattern pattern = Pattern.compile(CLIConstants.COORDINATES_REGEX);
        Matcher matcher = pattern.matcher(coordinates);
        return matcher.matches();
    }

    public VirtualModel getVirtualModel() {
        return virtualModel;
    }

    /**
     * This method is the main method of the view.
     *
     * @param args are the arguments of the main method
     */
    public abstract void main(String[] args);
    /**
     * Asks the user to choose his username.
     *
     * @return the username of the player
     */
    public abstract String chooseUsername();
    /**
     * Asks the user to choose the number of players for the game.
     *
     * @return the number of players chosen by the user
     */
    public abstract Integer choosePlayersNumber();
    /**
     * Asks the user to choose the item tiles to grab from the board.
     *
     * @return the list of the item tiles chosen by the user
     */
    public abstract List<Coordinate> chooseTiles();
    /**
     * Asks the user to choose the column of the library where to place the tiles.
     *
     * @return the column chosen by the user
     */
    public abstract Integer chooseColumn();
    /**
     * Starts the game and prints the objects of the game.
     */
    public abstract void startGame();
    /**
     * This method is the method that shows the game.
     */
    public abstract void showGame();
    /**
     * This method is the method that waits for the turn of the player.
     */
    public abstract void waitForTurn();
    /**
     * This method is the method that plays the turn of the player.
     */
    public abstract void playTurn();
    /**
     * This method is the method that ends the game.
     */
    public abstract void endGame(Boolean isWinner);
    /**
     * This method is the method that manages the turn of the player.
     */
    public abstract void showErrorMessage(String errorMessage);
    /**
     * Stops the waiting thread.
     */
    public abstract void stopWaiting();
    /**
     * Shows a message.
     */
    public abstract void showMessage(String message);
    /**
     * Shows a chat message.
     */
    public abstract void showChatMessage(String sender, String content);
}