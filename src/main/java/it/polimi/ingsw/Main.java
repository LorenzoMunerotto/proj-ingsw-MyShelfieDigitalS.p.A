package it.polimi.ingsw;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.terminal.Controller;
import it.polimi.ingsw.view.cli.CLIAssets;
import it.polimi.ingsw.view.cli.CLIColors;
import it.polimi.ingsw.view.cli.CLIDrawer;

/**
 * java says hello
 */
public class Main {
    public static void main(String[] args) {


        System.out.println(CLIColors.CYAN_BRIGHT+CLIAssets.MYSHELFIE_TITLE+CLIColors.RESET);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        Game game = new Game();
        Controller controller = new Controller(game.getGameData());
        CLIDrawer drawer = new CLIDrawer(game.getGameData());

        controller.manageNewUsers();
        game.boardInitialization();
        game.assignAllPersonalCard();

        drawer.printGame();
    }
}


