package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.view.gui.GUI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.javatuples.Pair;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static it.polimi.ingsw.view.cli.CLIConstants.LEADERBOARD_BOTTOM_FRAME_FORMAT;
import static it.polimi.ingsw.view.cli.CLIConstants.LEADERBOARD_MIDDLE_FRAME_FORMAT;


public class FinalPageController implements Controller{
    @FXML
    private TableView tableResultID;
    @FXML
    private TableColumn nameColumnID;

    @FXML
    private TableColumn pointsColumnID;

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
        List<Pair<String, Integer>> leaderBoards = virtualModel.getLeaderBoard();
        for (int i = 0; i < leaderBoards.size(); i++) {
            nameColumnID.setCellValueFactory(new PropertyValueFactory<>(leaderBoards.get(i).getValue0()));
            //nameColumnID.getColumns().add(leaderBoards.get(i).getValue0());
            pointsColumnID.setCellValueFactory(new PropertyValueFactory<>(String.valueOf(leaderBoards.get(i).getValue1())));
            //pointsColumnID.getColumns().add(leaderBoards.get(i).getValue1());
        }
        tableResultID.setVisible(true);
        nameColumnID.setVisible(true);
        pointsColumnID.setVisible(true);
    }

}
