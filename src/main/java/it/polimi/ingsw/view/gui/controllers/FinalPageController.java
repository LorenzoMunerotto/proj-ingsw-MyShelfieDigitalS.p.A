package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.view.gui.GUI;
import it.polimi.ingsw.view.gui.Result;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.javatuples.Pair;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FinalPageController implements Controller{
    @FXML
    private TableView<Result> tableResultID;
    @FXML
    private TableColumn<Result, String> nameColumnID= new TableColumn<Result, String>("userName");

    @FXML
    private TableColumn<Result, Integer> pointsColumnID =new TableColumn<Result,Integer>("points");

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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                compilePointsTable();
            }
        });

    }

    public void compilePointsTable(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                List<Pair<String, Integer>> leaderBoards = virtualModel.getLeaderBoard();
                 ObservableList<Result> data = FXCollections.observableArrayList();
                //nameColumnID.setCellValueFactory(new PropertyValueFactory<>(leaderBoards.get(i).getValue0()));
                //pointsColumnID.setCellValueFactory(new PropertyValueFactory<>(String.valueOf(leaderBoards.get(i).getValue1())));
                TableColumn userNameColumn = new TableColumn("userName");
                userNameColumn.setMinWidth(100);
                userNameColumn.setCellValueFactory(
                        new PropertyValueFactory<Result, String>("userName"));
                TableColumn pointsColum = new TableColumn("points");
                pointsColum.setMinWidth(100);
                pointsColum.setCellValueFactory(
                        new PropertyValueFactory<Result, Integer>("points"));

                for (int i = 0; i < leaderBoards.size(); i++) {
                    //tableResultID.getColumns().add(SleaderBoards.get(i).getValue0());
                    data.add(new Result(leaderBoards.get(i).getValue0(),leaderBoards.get(i).getValue1()));
                    //nameColumnID.getColumns(leaderBoards.get(i).getValue0(),leaderBoards.get(i).getValue1()).ge

                    //nameColumnID.getColumns().add(leaderBoards.get(i).getValue0());
                    //pointsColumnID.getColumns().add(leaderBoards.get(i).getValue1());
                }
                //nameColumnID.setCellValueFactory(new PropertyValueFactory<>("userName"));
                //pointsColumnID.setCellValueFactory(new PropertyValueFactory<>("points"));
                //tableResultID.setItems(data);
                tableResultID.getColumns().addAll(userNameColumn, pointsColum);
                tableResultID.getItems().addAll(data);
                tableResultID.setVisible(true);
                //nameColumnID.setVisible(true);
                //pointsColumnID.setVisible(true);
            }
        });
    }

}
