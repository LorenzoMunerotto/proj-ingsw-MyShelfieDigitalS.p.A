package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.view.gui.GUI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.javatuples.Pair;

import java.util.List;

public class FinalPageController implements Controller{
    @FXML
    private TableView tableResultID;
    @FXML
    private TableColumn<Object, Object> nameColumnID;
    @FXML
    private TableColumn<Object, Object> pointsColumnID;

    private GUI gui;
    private  VirtualModel virtualModel;
    @Override
    public void setGui(GUI gui) {
        this.gui = gui;
        this.virtualModel=gui.getVirtualModel();
    }
/*
    public FinalPageController(GUI gui) {
        this.gui = gui;
        this.virtualModel=gui.getClient().getVirtualModel();
    }
    /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        compilePointsTable();
    }
     */
    public void setUp(){
        Platform.runLater(this::compilePointsTable);

    }

    public void compilePointsTable(){
        Platform.runLater(() -> {
            List<Pair<String, Integer>> leaderBoards = virtualModel.getLeaderBoard();
            for (Pair<String, Integer> leaderBoard : leaderBoards) {
                nameColumnID.setCellValueFactory(new PropertyValueFactory<>(leaderBoard.getValue0()));
                //nameColumnID.getColumns().add(leaderBoards.get(i).getValue0());
                pointsColumnID.setCellValueFactory(new PropertyValueFactory<>(String.valueOf(leaderBoard.getValue1())));
                //pointsColumnID.getColumns().add(leaderBoards.get(i).getValue1());
            }
            tableResultID.setVisible(true);
            nameColumnID.setVisible(true);
            pointsColumnID.setVisible(true);
        });
    }
}