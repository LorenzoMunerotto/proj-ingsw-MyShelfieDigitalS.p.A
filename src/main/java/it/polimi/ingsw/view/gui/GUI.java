package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.events.NumOfPlayerChoice;
import it.polimi.ingsw.view.events.UsernameChoice;
import it.polimi.ingsw.view.gui.controllers.GameViewController;
import it.polimi.ingsw.view.gui.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.application.Application.launch;

public class GUI extends Application implements View {
    private LoginController loginController;
    private GameViewController gameViewController;
    private Client client;
    private VirtualModel virtualModel;
    private static double minX;
    private static double minY;
    private static double maxX;
    private static double maxY;

    private static double width;
    private static double height;



    public GUI(){
       this.virtualModel= new VirtualModel();
    }

    @Override
    public VirtualModel getVirtualModel() {
        return virtualModel;
    }

    public void start(Stage stage) throws Exception {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        maxX=screenBounds.getMaxX();
        maxY=screenBounds.getMaxY();
        width=screenBounds.getWidth();
        height=screenBounds.getHeight();
        System.out.println("preFXML");
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxml/loginView.fxml"));
        Parent root = loader.load();
        loginController = loader.getController();
        loginController.setGui(this);
        System.out.println("postController");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("postFXML");
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
        Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        Scene scene = new Scene(root);
        //stage.setFullScreen(true);
        stage.setScene(scene);
        //FXMLLoader loader =new FXMLLoader(getClass().getResource("loginView.fxml"));
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
        loginController.chooseUsernameView();
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
    public void startGame() throws IOException {
        FXMLLoader loader = FXMLLoader.load(getClass().getResource("/fxml/GameView.fxml"));
        Parent root = loader.load();
        gameViewController = loader.getController();
        Scene scene=new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void showGame() {

    }

    @Override
    public void waitForTurn(String username) {
        //gameViewController.
    }

    @Override
    public void playTurn() {

    }

    @Override
    public void endGame(Boolean isWinner) {

    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void stopWaiting() {

    }

    @Override
    public void showMessage(String message) {

    }
}
