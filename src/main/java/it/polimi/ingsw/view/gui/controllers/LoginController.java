package it.polimi.ingsw.view.gui.controllers;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button loginButtonID;

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

    private Integer[] numbers ={2,3,4};

    private int numberOfPlayers;
    private String userName;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void submit(ActionEvent event) throws IOException {
        if(numberBoxID.getValue()==null&&textNameInputID.getText().isEmpty()){
            errorsLabelID.setText("Please, enter the number of players and a UserName");
            System.out.println("Please, enter the number of players and a UserName");
        }
        else if(numberBoxID.getValue()==null){
            errorsLabelID.setText("Please, enter the number of players");
            System.out.println("Please, enter the number of players");
        }
        else if(textNameInputID.getText().isEmpty()){
            errorsLabelID.setText("Please, enter a Username");
            System.out.println("Please, enter a Username");
        }
        else{
            numberOfPlayers=numberBoxID.getValue();
            errorsLabelID.setText("Ok");
            userName=textNameInputID.getText();
            System.out.println(numberOfPlayers+ " " + userName);
            //stage.setScene(scene);
            //Parent root =FXMLLoader.load(HelloApplication.class.getResource("loginView.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("GameView.fxml"));
            scene=new Scene(root);
            Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            stage.show();
            stage.setFullScreen(true);
            try {
                if(numberBoxID.getValue()!=null&&!textNameInputID.getText().isEmpty()){
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberBoxID.getItems().addAll(numbers);
    }
}
