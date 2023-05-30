package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.view.gui.GUI;
import it.polimi.ingsw.view.gui.Result;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.javatuples.Pair;

import java.util.List;


/**
 * Controller for the "FinalPageController.fxml" file.
 */
public class FinalPageController implements Controller {
    @FXML
    private TableView<Result> tableResultID;
    private VirtualModel virtualModel;

    /**
     * {@inheritDoc}
     *
     * @param gui is the main class for the graphics part of the game.
     */
    @Override
    public void setGui(GUI gui) {
        this.virtualModel = gui.getClient().getVirtualModel();
    }

    /**
     * {@inheritDoc}
     */
    public void setUp() {
        Platform.runLater(this::compilePointsTable);
    }

    /**
     * This method fills in the table with the final classification.
     */
    public void compilePointsTable() {
        Platform.runLater(() -> {
            List<Pair<String, Integer>> leaderBoards = virtualModel.getLeaderBoard();
            ObservableList<Result> data = FXCollections.observableArrayList();
            TableColumn<Result, String> userNameColumn = new TableColumn<>("username");
            TableColumn<Result, String> pointsColum = new TableColumn<>("points");
            tableResultID.getColumns().addAll(userNameColumn, pointsColum);
            userNameColumn.setMinWidth(150);
            pointsColum.setMinWidth(150);
            for (Pair<String, Integer> leaderBoard : leaderBoards) {
                data.add(new Result(leaderBoard.getValue0(), String.valueOf(leaderBoard.getValue1())));
            }
            userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            pointsColum.setCellValueFactory(new PropertyValueFactory<>("points"));
            tableResultID.getItems().addAll(data);
            tableResultID.setVisible(true);
        });
    }
}