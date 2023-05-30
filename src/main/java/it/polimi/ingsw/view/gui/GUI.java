package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.events.NumOfPlayerChoice;
import it.polimi.ingsw.view.events.UsernameChoice;
import it.polimi.ingsw.view.gui.controllers.Controller;
import it.polimi.ingsw.view.gui.controllers.FinalPageController;
import it.polimi.ingsw.view.gui.controllers.GameViewController;
import it.polimi.ingsw.view.gui.controllers.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the GUI view of the game.
 */
public class GUI extends Application implements View {

    private static final String LOGIN = "/fxml/LoginView.fxml";
    private static final String GAME = "/fxml/GameView.fxml";
    private static final String FINAL = "/fxml/FinalPage.fxml";
    private static LoginController loginController;
    private static GameViewController gameViewController;
    private static FinalPageController finalPageController;
    private static Client client;
    private static double maxX;
    private static double maxY;
    private final VirtualModel virtualModel;
    private final HashMap<String, Controller> nameMapController = new HashMap<>();
    private final HashMap<String, Scene> nameMapScene = new HashMap<>();
    private final Logger logger = Logger.getLogger(getClass().getName());
    private Scene currentScene;
    private Stage stage;
    private int scene = 0;

    public GUI() {
        this.virtualModel = new VirtualModel();
    }

    public static GameViewController getGameViewController() {
        return gameViewController;
    }

    @Override
    public VirtualModel getVirtualModel() {
        return virtualModel;
    }

    /**
     * Start of the javafx view.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    public void start(Stage primaryStage) {
        setup();
        this.stage = primaryStage;
        run();
    }

    /**
     * Load the different fxml file and their corresponding fxml controller.
     */
    public void setup() {
        screenInfo();
        List<String> fxmList = new ArrayList<>(Arrays.asList(LOGIN, GAME, FINAL));
        try {
            for (String path : fxmList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                nameMapScene.put(path, new Scene(loader.load()));
                Controller controller = loader.getController();
                controller.setGui(this);
                switch (path) {
                    case LOGIN -> {
                        loginController = loader.getController();
                        loginController.setGui(this);
                    }
                    case GAME -> {
                        gameViewController = loader.getController();
                        gameViewController.setGui(this);
                    }
                    case FINAL -> {
                        finalPageController = loader.getController();
                        finalPageController.setGui(this);
                    }
                }
                nameMapController.put(path, controller);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        nameMapController.get(LOGIN).setUp();
        currentScene = nameMapScene.get(LOGIN);
    }

    /**
     * Initial settings of items related to graphics.
     */
    public void run() {
        stage.setTitle("MyShelfieDigitals S.p.A.");
        stage.setScene(currentScene);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
        stage.getIcons().add(new Image(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/Box.png"))));
        stage.show();
    }

    /**
     * Allow to change scene when the game is running.
     *
     * @param newScene next scene
     */
    public void changeStage(String newScene) {
        currentScene = nameMapScene.get(newScene);
        stage.setScene(currentScene);
        nameMapController.get(newScene).setUp();
        if (newScene.equals("/fxml/FinalPage.fxml")) {
            stage.setFullScreen(false);
            stage.setWidth(300);
            stage.setHeight(300);
        } else {
            stage.setFullScreen(true);
        }
        stage.show();
    }

    private void screenInfo() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        maxX = screenBounds.getMaxX();
        maxY = screenBounds.getMaxY();
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    @Override
    public void setUsername(String username) {
        client.handle(new UsernameChoice(username));
    }

    @Override
    public void setPlayersNumber(int playersNumber) {
        client.handle(new NumOfPlayerChoice(playersNumber));
    }

    @Override
    public boolean isUsernameValid(String username) {
        return View.super.isUsernameValid(username);
    }

    @Override
    public boolean isCoordinatesValid(String coordinates) {
        return View.super.isCoordinatesValid(coordinates);
    }

    public void main(String[] args) {
        launch(args);
    }

    @Override
    public void chooseUsername() {
        getLoginController().chooseUsernameView();
    }

    @Override
    public void choosePlayersNumber() {
        loginController.choosePlayersNumberView();
    }

    @Override
    public void chooseMove() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        getLoginController().loadGameView();
        scene = 1;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void setClient(Client client) {
        GUI.client = client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGame() {
        getGameViewController().rePrintAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waitForTurn() {
        gameViewController.setErrorsTextIDText("Waiting for " + virtualModel.getCurrentPlayerUsername() + " to play the turn...");
        getGameViewController().setYouTurn(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playTurn() {
        getGameViewController().setYouTurn(true);
        getGameViewController().setErrorsTextIDText("It is your turn!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame(Boolean isWinner) {
        getGameViewController().loadFinalPage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showErrorMessage(String errorMessage) {
        if (scene == 0) {
            getLoginController().setErrorsLabelIDText(errorMessage);
        } else if (scene == 1) {
            getGameViewController().setErrorsTextIDText(errorMessage);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopWaiting() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(String message) {
        if (scene == 0) {
            getLoginController().setErrorsLabelIDText(message);
        } else if (scene == 1) {
            getGameViewController().setErrorsTextIDText(message);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showChatMessage(String sender, String message) {
        getGameViewController().showChatMessage(sender, message, ChatMessageType.RECEIVER);
    }

    public LoginController getLoginController() {
        return loginController;
    }

    @Override
    public void closeGame() {
    }
}
