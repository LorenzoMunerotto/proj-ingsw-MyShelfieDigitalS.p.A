package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.gui.GUI;
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

public class LoginController implements Initializable  {

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
    private GUI gui;

    private Integer[] numbers ={2,3,4};

    private int numberOfPlayers;
    private String userName;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void setErrorsLabelIDText(String message){
        errorsLabelID.setText(null);
        errorsLabelID.setText(message);
        errorsLabelID.setVisible(true);
    }

    public void userNameSubmit(ActionEvent actionEvent) {
        if(gui.isUsernameValid(textNameInputID.getText())){
            errorsLabelID.setText(null);
            gui.setUsername(textNameInputID.getText());
            textNameInputID.setVisible(false);
            userNameButtonID.setVisible(false);
            nameLabelID.setVisible(false);
        }
        else if(!gui.isUsernameValid(textNameInputID.getText())){
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
            gui.setPlayersNumber(numberBoxID.getValue());
            numberBoxID.setVisible(false);
            numberLabelID.setVisible(false);
            loginButtonID.setVisible(false);
        }

    }

    public void chooseUsernameView() {
        textNameInputID.setVisible(true);
        nameLabelID.setVisible(true);
        userNameButtonID.setVisible(true);
        System.out.println("chooseUsernameView");
    }


    public void choosePlayersNumberView() {
        numberBoxID.getItems().addAll(numbers);
        numberBoxID.setVisible(true);
        numberLabelID.setVisible(true);
        loginButtonID.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //backGroundID.setImage(new Image(getClass().getResourceAsStream("/images/BackgroundImage.jpg")));
        System.out.println("initialize");
        numberLabelID.setVisible(false);
        errorsLabelID.setVisible(true);
        numberBoxID.setVisible(false);
        numberLabelID.setVisible(false);
        loginButtonID.setVisible(false);
        textNameInputID.setVisible(false);
        userNameButtonID.setVisible(false);
        nameLabelID.setVisible(false);
    }

}
