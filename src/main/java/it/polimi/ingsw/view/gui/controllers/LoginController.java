package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController extends View implements Initializable  {

    @FXML
    private Button loginButtonID;
    @FXML
    private Button userNameButtonID;

    @FXML
    private Label nameLabelID;

    @FXML
    private TextField textNameInputID;

    @FXML
    private Label numberLabelID;

    @FXML
    private ChoiceBox<Integer> numberBoxID;

    @FXML
    private Label errorsLabelID;
    @FXML
    private ImageView backGroundID;
    private boolean usernamePress=false;

    private Integer[] numbers ={2,3,4};

    private int numberOfPlayers;
    private String userName;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void userNameSubmit(ActionEvent actionEvent) {
        if(isUsernameValid(textNameInputID.getText())){
            usernamePress=true;
            errorsLabelID.setText(null);
            super.setUsername(textNameInputID.getText());
            textNameInputID.setVisible(false);
            userNameButtonID.setVisible(false);
        }
        else if(!isUsernameValid(textNameInputID.getText())){
            usernamePress=false;
            errorsLabelID.setText("Invalid username, please try again");
            textNameInputID.clear();
        }
    }
    public void submitNumberOfPlayer(ActionEvent event)  {
        if(numberBoxID.getValue()==null){
            textNameInputID.clear();
            errorsLabelID.setText("Please, select the exact number of players for the game");
        }
        else if(numberBoxID.getValue()!=null){
            errorsLabelID.setText(null);
            super.setPlayersNumber(numberBoxID.getValue());
            numberBoxID.setVisible(false);
            numberLabelID.setVisible(false);
            loginButtonID.setVisible(false);
        }

    }

    @Override
    public void main(String[] args) {

    }

    @Override
    public void chooseUsername() {
        textNameInputID.setVisible(true);
        numberLabelID.setVisible(true);
        userNameButtonID.setVisible(true);
    }

    @Override
    public void choosePlayersNumber() {
        numberBoxID.getItems().addAll(numbers);
        numberBoxID.setVisible(true);
        numberLabelID.setVisible(true);
        loginButtonID.setVisible(true);
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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/GameView.fxml"));
        scene=new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    @Override
    public void showGame() {

    }

    @Override
    public void waitForTurn(String username) {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //backGroundID.setImage(new Image(getClass().getResourceAsStream("/images/BackgroundImage.jpg")));
        numberLabelID.setVisible(false);
        errorsLabelID.setVisible(false);
        numberBoxID.setVisible(false);
        numberLabelID.setVisible(false);
        loginButtonID.setVisible(false);
        textNameInputID.setVisible(false);
        userNameButtonID.setVisible(false);
    }

}
