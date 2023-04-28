package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.view.cli.CLI;
import it.polimi.ingsw.controller.GameHandler;
import it.polimi.ingsw.client.view.cli.CLIAssets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class View {

    protected String username;
    protected int playersNumber;
    protected String coordinates;
    protected int column;
    protected int MIN_PLAYERS_NUMBER = 2;
    protected int MAX_PLAYERS_NUMBER = 4;
    protected String winner;
    protected VirtualModel virtualModel;

    public View(VirtualModel virtualModel) {
        this.virtualModel = virtualModel;
    }

    protected static boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile(CLIAssets.USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    protected static boolean isCoordinatesValid(String coordinates) {
        Pattern pattern = Pattern.compile(CLIAssets.COORDINATES_REGEX);
        Matcher matcher = pattern.matcher(coordinates);
        return matcher.matches();
    }

    public static void main(String[] args) {
        GameHandler game = new GameHandler();
        VirtualModelProxy virtualModel = new VirtualModelProxy();
        View view = new CLI(virtualModel);
        view.createGame(game);
    }

    protected void reset() {
        this.username = "";
        this.playersNumber = 0;
    }

    public abstract void createGame(GameHandler game);

    protected abstract void waitForTurn();

    protected void checkForWinner() {
        if (virtualModel.getWinner() != null) {
            winner = virtualModel.getWinner();

        }
    }
}
