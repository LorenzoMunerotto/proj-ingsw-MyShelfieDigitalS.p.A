package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.events.NumOfPlayerChoice;
import it.polimi.ingsw.view.events.UsernameChoice;
import it.polimi.ingsw.view.gui.controllers.FinalPageController;
import it.polimi.ingsw.view.gui.controllers.GameViewController;
import it.polimi.ingsw.view.gui.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.application.Application.launch;

public class GUI extends Application implements View {
    private static LoginController loginController;
    private static GameViewController gameViewController;
    private static FinalPageController finalPageController;
    private static Client client;
    private static VirtualModel virtualModel;
    private static double minX;
    private static double minY;
    private static double maxX;
    private static double maxY;

    private static double width;
    private static double height;

    private static FXMLLoader loader;
    private static FXMLLoader loaderGame;
    private Stage stage;

    public GUI(){
       this.virtualModel= new VirtualModel();
    }

    @Override
    public VirtualModel getVirtualModel() {
        return virtualModel;
    }

    public void start(Stage stage) {
        System.out.println("postFXML");
        this.stage = stage;
        loadLoginView();
    }
    private void loadLoginView(){
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        maxX=screenBounds.getMaxX();
        maxY=screenBounds.getMaxY();
        width=screenBounds.getWidth();
        height=screenBounds.getHeight();
        System.out.println("preFXML");
        loader= new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(loginController);
        setLoginController(loader.getController());
        System.out.println(loginController);
        loginController.setGui(this);
        System.out.println("postController");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadGameView(){
        System.out.println("preLoadGameView");
        loaderGame= new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
        Parent root = null;
        try {
            root = loaderGame.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setGameViewController(loaderGame.getController());
        System.out.println("postLoadGameView");
        gameViewController.setGui(this);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void loadFinalPage(){
        loader= new FXMLLoader(getClass().getResource("/fxml/FinalPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setFinalPageController(loader.getController());
        finalPageController.setGui(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    public static double getMaxX() {
        return maxX;
    }

    public static double getMaxY() {
        return maxY;
    }

    /*
    @Override
    public void start(Stage stage)  {
        //per far partire da un file FXML:
        //Parent root =FXMLLoader.load(HelloApplication.class.getResource("Main.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
        Scene scene = new Scene(root);
        //stage.setFullScreen(true);
        stage.setScene(scene);
        //FXMLLoader loader =new FXMLLoader(getClass().getResource("LoginView.fxml"));
        //AnchorPane loginPane= loader.load();
        //stage.setFullScreen(true);
        stage.show();
        //stage.close per chiudere
        //HelloController controller= new HelloController();
        //controller.popola();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);

    } */

    /*
    @Override

    public void start(Stage primaryStage) {
        stages = new Views();
    }*/


    @Override
    public void setClient(Client client) {
        this.client=client;
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
    public List<Coordinate> chooseTiles() {
        return null;
    }

    @Override
    public Integer chooseColumn() {
        return null;
    }

    @Override
    public void startGame()  {
        System.out.println("StartGAme mesage");
        loadGameView();
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void showGame() {
        //loadGameView();
        System.out.println("showGame");
    }

    @Override
    public void waitForTurn(String username) {
        gameViewController.printError("Waiting for " + username + " to play the turn...");
        //gameViewController.
    }

    @Override
    public void playTurn() {
        gameViewController.printError("It is your turn!");
        gameViewController.yourTurn();
    }

    @Override
    public void endGame(Boolean isWinner) {
        loadFinalPage();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        gameViewController.printError(errorMessage);
    }

    @Override
    public void stopWaiting() {
        gameViewController.notYourTurn();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
        loginController.setErrorsLabelIDText(message);
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
}
