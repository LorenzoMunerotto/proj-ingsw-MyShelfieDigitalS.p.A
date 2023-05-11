package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.view.cli.CLIConstants;
import it.polimi.ingsw.view.gui.GUI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.javatuples.Pair;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static it.polimi.ingsw.view.cli.CLIConstants.LEADERBOARD_BOTTOM_FRAME_FORMAT;
import static it.polimi.ingsw.view.cli.CLIConstants.LEADERBOARD_MIDDLE_FRAME_FORMAT;


public class FinalPageController implements Initializable {
    @FXML
    private TableView tableResultID;
    @FXML
    private TableColumn nameColumnID;

    @FXML
    private TableColumn pointsColumnID;

    private GUI gui;
    private final VirtualModel virtualModel;

    public FinalPageController(GUI gui) {
        this.gui = gui;
        this.virtualModel=gui.getClient().getVirtualModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        compilePointsTable();
    }

    private void compilePointsTable(){
        List<Pair<String, Integer>> leaderBoards = virtualModel.getLeaderBoard();
        for (int i = 0; i < leaderBoards.size(); i++) {
            nameColumnID.getColumns().add(leaderBoards.get(i).getValue0());
            pointsColumnID.getColumns().add(leaderBoards.get(i).getValue1());
        }
        tableResultID.setVisible(true);
        nameColumnID.setVisible(true);
        pointsColumnID.setVisible(true);
    }

}
