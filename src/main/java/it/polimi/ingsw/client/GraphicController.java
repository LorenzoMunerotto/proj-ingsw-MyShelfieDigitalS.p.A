package it.polimi.ingsw.client;
import it.polimi.ingsw.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class GraphicController {
    private static String fileName ="src/main/resources/Cornici1.1.png";
    @FXML
    private static GridPane boardID;

    public static void popola() throws FileNotFoundException {

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                FileInputStream imageStream = new FileInputStream(fileName);
                Image image = new Image(imageStream);
                boardID.add(new ImageView(image), i, j);
            }
        }
    }



    public GraphicController() throws FileNotFoundException {
    }


    private void fill(){

    }
}
