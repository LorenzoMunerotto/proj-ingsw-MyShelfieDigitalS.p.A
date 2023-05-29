package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.gui.GUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController implements Controller {

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
    private TextFlow errorsTextID;

    @FXML
    private Pane paneID;

    private boolean usernamePress = false;
    private GUI gui;

    private Integer[] numbers = {2, 3, 4};

    @FXML
    private Button startGameButtonID;

    @Override
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void setErrorsLabelIDText(String error) {
        Platform.runLater(() -> {
            errorsTextID.getChildren().clear();
            Text text = new Text(error);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 19));
            text.setStrokeWidth(1);
            text.setStroke(Color.BLACK);
            errorsTextID.getChildren().add(text);
            errorsTextID.setVisible(true);
        });
    }

    public void userNameSubmit(ActionEvent actionEvent) {
        numberLabelID.setVisible(false);
        if (gui.isUsernameValid(textNameInputID.getText())) {
            errorsTextID.getChildren().clear();
            gui.setUsername(textNameInputID.getText());
            textNameInputID.setVisible(false);
            userNameButtonID.setVisible(false);
            nameLabelID.setVisible(false);
        } else if (!gui.isUsernameValid(textNameInputID.getText())) {
            usernamePress = false;
            setErrorsLabelIDText("Invalid username, please try again");
            textNameInputID.clear();
        }
    }

    public void submitNumberOfPlayer(ActionEvent event) {
        if (numberBoxID.getValue() == null) {
            setErrorsLabelIDText("Please, select the exact number of players for the game");
        } else if (numberBoxID.getValue() != null) {
            errorsTextID.getChildren().clear();
            gui.setPlayersNumber(numberBoxID.getValue());
            numberBoxID.setVisible(false);
            numberLabelID.setVisible(false);
            loginButtonID.setVisible(false);
        }

    }

    public void loadGameView() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gui.changeStage("/fxml/GameView.fxml");

            }
        });
    }

    public void enterTheGame(ActionEvent event) throws IOException {
        gui.changeStage("/fxml/GameView.fxml");

    }


    public void preGame() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                startGameButtonID.setText("Enter the Game");
                startGameButtonID.setVisible(true);
            }
        });

    }

    public void chooseUsernameView() {
        textNameInputID.setVisible(true);
        nameLabelID.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000FF;");
        nameLabelID.setVisible(true);
        userNameButtonID.setVisible(true);
    }


    public void choosePlayersNumberView() {
        numberBoxID.getItems().addAll(numbers);
        numberLabelID.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000FF;");
        numberBoxID.setVisible(true);
        numberLabelID.setVisible(true);
        loginButtonID.setVisible(true);
    }

    public void setUp() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                paneID.setBackground(new Background(
                        new BackgroundImage(
                                new Image(getClass().getResourceAsStream("/images/Display.jpg")),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                        )));
                numberLabelID.setVisible(false);
                errorsTextID.setVisible(true);
                numberBoxID.setVisible(false);
                numberLabelID.setVisible(false);
                loginButtonID.setVisible(false);
                textNameInputID.setVisible(false);
                userNameButtonID.setVisible(false);
                startGameButtonID.setVisible(false);
                nameLabelID.setVisible(false);
            }
        });
    }

}
