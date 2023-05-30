package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.gui.GUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.util.Objects;

/**
 * Controller for the "LoginView.fxml" file.
 */
public class LoginController implements Controller {

    private final Integer[] numbers = {2, 3, 4};
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
    private GUI gui;

    /**
     * {@inheritDoc}
     *
     * @param gui is the main class for the graphics part of the game.
     */
    @Override
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * set all the parameters that are called when you need to show a message to the player.
     *
     * @param error error text that will be displayed
     */
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

    /**
     * this method is called when you press the button related to the username submission
     *
     * @param actionEvent Pressing of the button "continue" [userNameButtonID].
     */
    public void userNameSubmit(ActionEvent actionEvent) {
        numberLabelID.setVisible(false);
        if (gui.isUsernameValid(textNameInputID.getText())) {
            errorsTextID.getChildren().clear();
            gui.setUsername(textNameInputID.getText());
            textNameInputID.setVisible(false);
            userNameButtonID.setVisible(false);
            nameLabelID.setVisible(false);
        } else if (!gui.isUsernameValid(textNameInputID.getText())) {
            setErrorsLabelIDText("Invalid username, please try again");
            textNameInputID.clear();
        }
    }

    /**
     * deal with the Number of Player insertion.
     *
     * @param event Pressing of the button for the submission of the number of player.
     */
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

    /**
     * Call the method in the Gui class that allow to move to the GameView.
     */
    public void loadGameView() {
        Platform.runLater(() -> gui.changeStage("/fxml/GameView.fxml"));
    }

    /**
     * prepare all the element related to the username insertion.
     */
    public void chooseUsernameView() {
        textNameInputID.setVisible(true);
        nameLabelID.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000FF;");
        nameLabelID.setVisible(true);
        userNameButtonID.setVisible(true);
    }

    /**
     * prepare all the element related to the number of Player insertion.
     */
    public void choosePlayersNumberView() {
        numberBoxID.getItems().addAll(numbers);
        numberLabelID.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000FF;");
        numberBoxID.setVisible(true);
        numberLabelID.setVisible(true);
        loginButtonID.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    public void setUp() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                paneID.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Display.jpg"))), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));
                numberLabelID.setVisible(false);
                errorsTextID.setVisible(true);
                numberBoxID.setVisible(false);
                numberLabelID.setVisible(false);
                loginButtonID.setVisible(false);
                textNameInputID.setVisible(false);
                userNameButtonID.setVisible(false);
                nameLabelID.setVisible(false);
            }
        });
    }
}