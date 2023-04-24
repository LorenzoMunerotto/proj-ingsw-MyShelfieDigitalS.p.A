package it.polimi.ingsw;

import it.polimi.ingsw.client.GraphicController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.terminal.Controller;
import it.polimi.ingsw.terminal.Drawer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.javatuples.Pair;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static it.polimi.ingsw.client.GraphicController.popola;

/**
 * java says hello
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch();



        System.out.println("\n" +
                "███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗██╗\n" +
                "████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝██║\n" +
                "██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗  ██║\n" +
                "██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝  ╚═╝\n" +
                "██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗██╗\n" +
                "╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝╚═╝\n" +
                "                                                                              \n");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        Game game = new Game();
        Controller controller = new Controller(game.getGameData());
        Drawer drawer = new Drawer(game.getGameData());

        controller.manageNewUsers();
        game.boardInitialization();
        game.assignAllPersonalCard();

        while (game.getGameData().getFirstFullLibraryUsername().isEmpty() || !game.getGameData().getCurrentPlayer().hasChair()) {

            drawer.drawCurrentPlayerInfo();
            drawer.drawGameInfo();

            Integer currentPlayer = game.getGameData().getCurrentPlayerIndex();

            while (Objects.equals(game.getGameData().getCurrentPlayerIndex(), currentPlayer)) {
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


    @Override
    public void start (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Graphic.fxml"));
        Scene scene= new Scene(fxmlLoader.load(),1920,1080);
        popola();
        stage.setScene(scene);
        stage.show();
        popola();
        stage.show();
    }
}


