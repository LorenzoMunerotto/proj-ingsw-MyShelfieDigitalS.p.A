package it.polimi.ingsw.view;

import it.polimi.ingsw.model.gameState.Exceptions.GameStartedException;
import it.polimi.ingsw.model.gameState.Exceptions.IllegalUsernameException;
import it.polimi.ingsw.model.gameState.Exceptions.InvalidNumOfPlayers;
import it.polimi.ingsw.model.gameState.Exceptions.UsernameAlreadyExistsException;
import it.polimi.ingsw.view.cli.CLI;
import it.polimi.ingsw.view.cli.CLIAssets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class View {

    protected String username;
    protected int playersNumber;
    protected String coordinates;
    protected int column;
    protected int MIN_PLAYERS_NUMBER = 2;
    protected int MAX_PLAYERS_NUMBER = 4;
    protected VirtualModel virtualModel;

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

    protected void reset(){
        this.username = "";
        this.playersNumber = 0;
    }

    protected  abstract void createGame() throws UsernameAlreadyExistsException, GameStartedException, IllegalUsernameException, InvalidNumOfPlayers;

    public static void main(String[] args) throws UsernameAlreadyExistsException, IllegalUsernameException, GameStartedException, InvalidNumOfPlayers {
        View view = new CLI();
        view.createGame();
    }
}
