package it.polimi.ingsw;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.gameEntity.ItemTile;
import it.polimi.ingsw.model.gameMechanics.BoardManager;
import it.polimi.ingsw.model.gameMechanics.LibraryManager;
import it.polimi.ingsw.model.gameState.GameData;
import it.polimi.ingsw.terminal.Controller;
import it.polimi.ingsw.terminal.Drawer;
import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * java says hello
 */
public class Main {
    public static void main(String[] args) {


        System.out.println("My Shelfy!");


        Game game = new Game();
        Controller controller = new Controller(game.getGameData());
        Drawer drawer = new Drawer(game.getGameData());

        controller.manageNewUsers();
        game.boardInitialization();
        game.assignAllPersonalCard();

        while (game.getGameData().getFirstFullLibraryUsername().isEmpty() || game.getGameData().getCurrentPlayerIndex() != 0) {

            drawer.drawTurnInformation();
            drawer.drawBoard();
            drawer.currentLibrary();

            Integer currentPlayer = game.getGameData().getCurrentPlayerIndex();

            while (game.getGameData().getCurrentPlayerIndex()==currentPlayer) {
                try {
                    Pair<List<Pair<Integer, Integer>>, Integer> input = controller.takePlayerInput();
                    game.play(input.getValue0(), input.getValue1());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        drawer.showRank();




    }

}


