package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.events.NumOfPlayerChoice;
import it.polimi.ingsw.view.events.UsernameChoice;
import it.polimi.ingsw.view.gui.controllers.Controller;
import it.polimi.ingsw.view.gui.controllers.FinalPageController;
import it.polimi.ingsw.view.gui.controllers.GameViewController;
import it.polimi.ingsw.view.gui.controllers.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.application.Application.launch;

public class GUI extends Application implements View {
    private static LoginController loginController;
    private static Scene sceneGame;
    private static GameViewController gameViewController;
    private static FinalPageController finalPageController;
    private static Client client;
    private VirtualModel virtualModel;
    private final HashMap<String, Controller> nameMapController = new HashMap<>();
    private static double maxX;
    private static double maxY;
    private static final String LOGIN ="/fxml/LoginView.fxml";
    private static final String GAME ="/fxml/GameView.fxml";
    private static final String FINAL ="/fxml/FinalPage.fxml";

    private static double width;
    private static double height;

    private static FXMLLoader loader;
    private static FXMLLoader loaderGame;
    private Scene currentScene;
    private Stage stage;
    private static Parent root = null;
    private static Parent rootGame = null;
    private final HashMap<String, Scene> nameMapScene = new HashMap<>();
    private final Logger logger = Logger.getLogger(getClass().getName());
    private int scene=0;
    public GUI(){
        this.virtualModel= new VirtualModel();
    }

    @Override
    public VirtualModel getVirtualModel() {
        return virtualModel;
    }

    public void start(Stage primaryStage) {
        setup();
        this.stage = primaryStage;
        run();
        //loadLoginView();
    }
    public void setup() {
        screenInfo();
        List<String> fxmList = new ArrayList<>(Arrays.asList(LOGIN, GAME, FINAL));
        try {
            for (String path : fxmList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                nameMapScene.put(path, new Scene(loader.load()));
                Controller controller = loader.getController();
                controller.setGui(this);
                if(path==LOGIN){
                    loginController=loader.getController();
                    loginController.setGui(this);
                }
                else if(path==GAME){
                    gameViewController=loader.getController();
                    gameViewController.setGui(this);
                }
                else if(path==FINAL){
                    finalPageController=loader.getController();
                    finalPageController.setGui(this);
                }
                nameMapController.put(path, controller);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        nameMapController.get(LOGIN).setUp();
        currentScene = nameMapScene.get(LOGIN);
    }
    public void run() {
        stage.setTitle("MyShelfieDigitals S.p.A.");
        stage.setScene(currentScene);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.getIcons().add(new Image(GUI.class.getResourceAsStream("/images/Box.png")));
        stage.show();
    }
    public void changeStage(String newScene) {
        currentScene = nameMapScene.get(newScene);
        stage.setScene(currentScene);
        nameMapController.get(newScene).setUp();
        if (newScene.equals("/fxml/FinalPage.fxml")){
            stage.setFullScreen(false);
            stage.setWidth(300);
            stage.setHeight(300);
        }
        else {
            stage.setFullScreen(true);
        }
        stage.show();
    }

    private void screenInfo(){
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        maxX=screenBounds.getMaxX();
        maxY=screenBounds.getMaxY();
        width=screenBounds.getWidth();
        height=screenBounds.getHeight();
    }
    public void setController(){
        loader= new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
        //System.out.println(loginController);
        setLoginController(loader.getController());
        //System.out.println(loginController);
        loginController.setGui(this);
    }

    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }


    @Override
    public void setClient(Client client) {
        GUI.client =client;
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
        System.out.println("chooseUsername");
        System.out.println(loginController);
        getLoginController().chooseUsernameView();
        System.out.println(loginController);
    }

    @Override
    public void choosePlayersNumber() {
        loginController.choosePlayersNumberView();
    }

    @Override
    public void chooseMove(){

    }

    @Override
    public void startGame()  {
        getLoginController().loadGameView();
        System.out.println("StartGAme mesage");
        scene=1;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void showGame() {
        //loadGameView();
        getGameViewController().rePrintAll();
        System.out.println("showGame");
    }

    @Override
    public void waitForTurn() {
        gameViewController.setErrorsTextIDText("Waiting for " + virtualModel.getCurrentPlayerUsername() + " to play the turn...");
        getGameViewController().setYouTurn(false);
    }


    @Override
    public void playTurn() {
        getGameViewController().setYouTurn(true);
        getGameViewController().setErrorsTextIDText("It is your turn!");
    }

    @Override
    public void endGame(Boolean isWinner) {
        getGameViewController().loadFinalPage();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        if(scene==0){
            getLoginController().setErrorsLabelIDText(errorMessage);
        }
        else if (scene==1){
            getGameViewController().setErrorsTextIDText(errorMessage);
        }
    }

    @Override
    public void stopWaiting() {
    }

    @Override
    public void showMessage(String message) {
        if(scene==0){
            getLoginController().setErrorsLabelIDText(message);
        }
        else if(scene==1){
            getGameViewController().setErrorsTextIDText(message);
        }
        System.out.println(message);
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public static GameViewController getGameViewController() {
        return gameViewController;
    }

    public static void setGameViewController(GameViewController gameViewController) {
        GUI.gameViewController = gameViewController;
    }

    public static void setFinalPageController(FinalPageController finalPageController) {
        GUI.finalPageController = finalPageController;
    }

    public static FinalPageController getFinalPageController() {
        return finalPageController;
    }

    public static Scene getSceneGame() {
        return sceneGame;
    }

    public HashMap<String, Controller> getNameMapController() {
        return nameMapController;
    }

    public HashMap<String, Scene> getNameMapScene() {
        return nameMapScene;
    }
}