package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.ClientController;
import it.polimi.ingsw.client.view.cli.CLIAssets;

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
     * It is the username of the winner of the game.
     */
    protected String winner;
    /**
     * It is the virtual model.
     * Still don't know when and where to initialize it.
     */
    protected VirtualModel virtualModel;
    /**
     * It is the previous message received.
     */
    protected MessageType previousMessage;
    /**
     * It is the controller of the client.
     */
    protected ClientController controller;

    /**
     * This constructor initializes the virtual model and the controller.
     * Maybe.
     */
    public View() {
        this.virtualModel= new VirtualModelProxy();
        this.controller = new ClientController(virtualModel, this);
    }

    /**
     * This method checks if the username is valid.
     *
     * @param username is the username of the player
     * @return true if the username is valid, false otherwise
     */
    protected static boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile(CLIAssets.USERNAME_REGEX);
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
        Pattern pattern = Pattern.compile(CLIAssets.COORDINATES_REGEX);
        Matcher matcher = pattern.matcher(coordinates);
        return matcher.matches();
    }

    /**
     * This method resets the view.
     * Useless???
     */
    protected void reset() {
        this.username = "";
        this.playersNumber = 0;
        this.winner = "";
    }

    /**
     * This method is the main method of the view.
     * @param args are the arguments of the main method
     */
    public abstract void main(String[] args);

    /**
     * This method is the method that creates the game.
     */
    protected abstract void createGame();

    /**
     * This method is the method that joins the game.
     */
    protected abstract void joinGame();

    /**
     * This method is the method that starts the game.
     */
    protected abstract void startGame();

    /**
     * This method is the method that shows the game.
     */
    protected abstract void showGame();

    /**
     * This method is the method that waits for the turn of the player.
     */
    protected abstract void waitForTurn();

    /**
     * This method is the method that plays the turn of the player.
     */
    protected abstract void playTurn();

    /**
     * This method is the method that ends the game.
     */
    protected abstract void endGame();

    /**
     * This method is the method that manages the turn of the player.
     */
    protected abstract void showErrorMessage();

    /**
     * This method is the method that manages the turn of the player.
     */
    protected void turnManager(){
        if (this.winner.isEmpty()){
            if (this.username.equals(virtualModel.getCurrentPlayer().getUsername())){
                this.playTurn();
            } else {
                this.waitForTurn();
            }
        } else {
            this.endGame();
        }
    }

    /**
     * This method is the method that checks if there is a winner.
     */
    protected void checkForWinner() {
        if (virtualModel.getWinner() != null) {
            winner = virtualModel.getWinner();
        }
    }

    /**
     * This method is the method that updates the view.
     *
     * @param message is the message received
     */
    public void updateView(MessageType message){
        switch (message) {
            case CREATE_GAME -> this.createGame();
            case JOIN_GAME -> this.joinGame();
            case START_GAME -> {
                this.startGame();
                this.turnManager();
            }
            case PLAY_TURN -> {
                this.showGame();
                this.checkForWinner();
                if (!this.winner.isEmpty()) {
                    this.turnManager();
                } else {
                    this.endGame();
                }
            }
            case SHOW_ERROR_MESSAGE -> showErrorMessage();
            default -> {
            }
        }
    }
}
