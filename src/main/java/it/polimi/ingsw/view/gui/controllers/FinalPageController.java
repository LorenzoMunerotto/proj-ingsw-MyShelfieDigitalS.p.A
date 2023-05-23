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

public class FinalPageController implements Controller{
    @FXML
    private TableView<Result> tableResultID;
    private GUI gui;
    private  VirtualModel virtualModel;
    @Override
    public void setGui(GUI gui) {
        this.gui = gui;
        this.virtualModel=gui.getClient().getVirtualModel();
    }

    public void setUp(){
        Platform.runLater(this::compilePointsTable);

    }

    public void compilePointsTable(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                List<Pair<String, Integer>> leaderBoards = virtualModel.getClientUsernamePoints();
                ObservableList<Result> data = FXCollections.observableArrayList();
                TableColumn userNameColumn = new TableColumn("userName");
                TableColumn pointsColum = new TableColumn("points");
                tableResultID.getColumns().addAll(userNameColumn,pointsColum);
                userNameColumn.setMinWidth(150);
                pointsColum.setMinWidth(150);
                for (int i = 0; i < leaderBoards.size(); i++) {
                    data.add(new Result(leaderBoards.get(i).getValue0(),String.valueOf(leaderBoards.get(i).getValue1())));
                }
                userNameColumn.setCellValueFactory(new PropertyValueFactory<Result, String>("userName"));
                pointsColum.setCellValueFactory(new PropertyValueFactory<Result, String>("points"));
                tableResultID.getItems().addAll(data);
                tableResultID.setVisible(true);
            }
            tableResultID.setVisible(true);
            nameColumnID.setVisible(true);
            pointsColumnID.setVisible(true);
        });
    }
}