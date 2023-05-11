package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.view.gui.GUI;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {
    @FXML
    public GridPane libraryID;
    @FXML
    private ImageView commonCard1ID;
    @FXML
    private ImageView commonCard2ID;
    @FXML
    private ImageView personalCardImgID;
    @FXML
    private GridPane boardID;
    @FXML
    private AnchorPane ancorPaneID;
    @FXML
    private Label personalCardLabelID;
    @FXML
    private Label commonCardLabelID;
    @FXML
    private VBox vBoxPersonalID;
    @FXML
    private VBox vBoxCommonlID;
    @FXML
    private HBox hBoxCommonID;
    @FXML
    private Circle turnCircleID;
    @FXML
    private ChoiceBox<String> librarySelectionID;

    @FXML
    private TextField errorsTextID;
    private GUI gui;
    private final VirtualModel virtualModel;
    private ArrayList<String> players=  new ArrayList<String>();
    private String personalCardFile =new String("EMPTY.png");
    private String commonCard1File =new String("CC1.jpg");
    private String commonCard2File =new String("CC2.jpg");

    private boolean youTurn=true;
    private ArrayList<ImageView> aImgViewBoard =new ArrayList<ImageView>();
    private ArrayList<Image> aImgBoard =new ArrayList<Image>();
    private ArrayList<ImageView> aImgViewLibrary =new ArrayList<ImageView>();
    private ArrayList<Image> aImgLibrary =new ArrayList<Image>();

    public GameViewController(GUI gui) {
        this.gui = gui;
        this.virtualModel=gui.getClient().getVirtualModel();
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    private static String fileName ="Cornici1.1.png";

    EventHandler mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            //personal card index needed
            if(youTurn==true){
                ImageView imageView = (ImageView) t.getSource();
                imageView.setImage(new Image(getClass().getResourceAsStream(personalCardFile)));
                System.out.println("You clicked " + imageView.getImage());
            }
        }
    };

    public void fullLibrary(){
        for(int c=0; c<5; c++){
            for(int r=0; r<6; r++){
                String fileName = virtualModel.getBoard()[r][c].toString();
                aImgLibrary.add(new Image(getClass().getResourceAsStream("/fxml/" + fileName + ".png")));
                aImgViewLibrary.add(new ImageView(aImgLibrary.get(c*6+r)));
                aImgViewLibrary.get(c*6+r).setX(90);
                aImgViewLibrary.get(c*6+r).setY(90);
                aImgViewLibrary.get(c*6+r).setPreserveRatio(true);
                aImgViewLibrary.get(c*6+r).setFitHeight(GUI.getMaxX()*(0.05));
                aImgViewLibrary.get(c*6+r).setFitWidth(GUI.getMaxX()*(0.05));
                libraryID.add(aImgViewLibrary.get(c*6+r),c,r);
            }
        }
        libraryID.setLayoutX(GUI.getMaxX()*0.05);
        libraryID.setLayoutY(GUI.getMaxY()*0.50);
        libraryID.setHgap(2);
        libraryID.setVgap(2);
    }

    public void fullBoard(){
        for(int c=0; c<9; c++){
            for(int r=0; r<9; r++){
                //Image image= new Image(getClass().getResourceAsStream("Cornici1.1.png"));
                aImgBoard.add(new Image(getClass().getResourceAsStream("Cornici1.1.png")));
                //ImageView imageView = new ImageView(aImgBoard.get(c*9+r));
                aImgViewBoard.add(new ImageView(aImgBoard.get(c*9+r)));
                aImgViewBoard.get(c*9+r).setX(90);
                aImgViewBoard.get(c*9+r).setY(90);
                aImgViewBoard.get(c*9+r).setPreserveRatio(true);
                aImgViewBoard.get(c*9+r).setFitHeight(GUI.getMaxX()*(0.05));
                aImgViewBoard.get(c*9+r).setFitWidth(GUI.getMaxX()*(0.05));
                boardID.add(aImgViewBoard.get(c*9+r),c,r);
                boardID.getChildren().get(c*9+r).setOnMouseClicked(mouseHandler);
            }
        }
        boardID.setLayoutX(GUI.getMaxX()*0.50);
        boardID.setLayoutY(GUI.getMaxY()*0.10);
        boardID.setHgap(2);
        boardID.setVgap(2);
    }
    public void printError (String error){
        errorsTextID.setText(error);

    }

    public void personalCardInizializer(){
        personalCardImgID.setImage(new Image(getClass().getResourceAsStream("front_EMPTY.jpg")));
        vBoxPersonalID.setLayoutX(GUI.getMaxX()*0.35);
        vBoxPersonalID.setLayoutY(GUI.getMaxY()*0.60);
        vBoxPersonalID.setFillWidth(true);
        //vBoxPersonalID.maxWidth(GUI.getMaxX()*0.17);
        vBoxPersonalID.setMaxWidth(GUI.getMaxX()*0.17);
        vBoxPersonalID.setMinWidth(GUI.getMaxX()*0.17);
        personalCardImgID.setPreserveRatio(true);
        personalCardImgID.setFitWidth(GUI.getMaxX()*0.15);
        personalCardImgID.setFitHeight(GUI.getMaxX()*0.15*(756*1110));
    }

    public void commonCardInizializzer(){
        commonCard1ID.setImage(new Image(getClass().getResourceAsStream(commonCard1File)));
        commonCard2ID.setImage(new Image(getClass().getResourceAsStream(commonCard2File)));
        commonCard1ID.setPreserveRatio(true);
        commonCard2ID.setPreserveRatio(true);
        vBoxCommonlID.setMaxHeight(GUI.getMaxY()*0.25);
        vBoxCommonlID.setMaxWidth(GUI.getMaxX()*0.35);
        hBoxCommonID.setMaxHeight(GUI.getMaxX()*0.35*(1385/913));
        hBoxCommonID.setMaxWidth(GUI.getMaxX()*0.35);
        commonCard1ID.setFitWidth(GUI.getMaxX()*0.15);
        commonCard1ID.setFitHeight(GUI.getMaxX()*0.15*(913/1365));
        commonCard2ID.setFitWidth(GUI.getMaxX()*0.15);
        commonCard2ID.setFitHeight(GUI.getMaxX()*0.15*(913/1365));
        vBoxCommonlID.setLayoutY(GUI.getMaxY()*0.10);
        vBoxCommonlID.setLayoutX(GUI.getMaxX()*0.05);
    }

    public void fillLibrarySelectionID(){
        for(String i : virtualModel.getClientUsernameLibrary().keySet() ){
            librarySelectionID.getItems().add(i);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //libraryID dovrebbe numerare verticalmente
        personalCardInizializer();
        commonCardInizializzer();
        fullBoard();
        fullLibrary();
        fillLibrarySelectionID();
        ItemTileType[][] board = virtualModel.getBoard();
        ItemTileType[][] currentLibrary = virtualModel.getLibrary();
        ItemTileType[][] personalCardLibrary = virtualModel.getPersonalGoalCard();
        //boardID.setPadding(new Insets(5,5,5,5));



        /*
        for(int c=0; c<9; c++){
            for(int r=0; r<9; r++){
                Image image= new Image(getClass().getResourceAsStream("Cornici1.1.png"));
                aImg.add(image);
                ImageView imageView = new ImageView(aImg.get(c*9+r));
                aImgView.add(imageView);

                //aImgView.get(c*9+r).setFitHeight(GUI.getMaxX()*(0.4/9));
                //aImgView.get(c*9+r).setFitWidth(GUI.getMaxY()*(0.4/9));

                aImgView.get(c*9+r).setFitHeight(GUI.getMaxX()*(0.05));
                aImgView.get(c*9+r).setFitWidth(GUI.getMaxX()*(0.05));
                boardID.add(aImgView.get(c*9+r),c,r);
                //boardID.setPadding(new Insets(5,5,5,5));
                //boardID.getChildren().add(aImgView.get(c*9+r));
                //boardID.getChildren().set(c*9+r, aImgView.get(c*9+r));
                //GridPane.setFillWidth(aImgView.get(c*9+r), true);
                //GridPane.setFillHeight(aImgView.get(c*9+r), true);
             //   libraryID.getChildren().set(r+c,aImgView.get(c*9+r));
            }
        }
        //boardID.snapPositionY(GUI.getMaxY()*0.05);
        //boardID.snapPositionX(GUI.getMaxX()*0.55);
        //boardID.setMaxWidth(GUI.getMaxX()*0.55);
        //boardID.setMaxHeight(GUI.getMaxY()*0.05);

        boardID.setLayoutX(GUI.getMaxX()*0.50);
        boardID.setLayoutY(GUI.getMaxY()*0.10);
        //boardID.setPadding(new Insets(5,5,5,5));
        /*
        boardID.setMaxHeight(GUI.getMaxY()*0.4);
        boardID.setMaxWidth(GUI.getMaxX()*0.4);
        boardID.snapPositionY(GUI.getMaxY()*0.05);
        boardID.snapPositionX(GUI.getMaxX()*0.55);
        boardID.visibleProperty().set(true);
        libraryID.setMaxHeight(GUI.getMaxY()*0.4);
        libraryID.setMaxWidth(GUI.getMaxX()*0.4);
        libraryID.snapPositionY(GUI.getMaxY()*0.05);
        libraryID.snapPositionX(GUI.getMaxX()*0.55); /*
        Image image= new Image(getClass().getResourceAsStream("Cornici1.1.png"));
        img1.setImage(image);
        img1.setFitHeight(GUI.getMaxY()*(0.4/9));
        img1.setFitWidth(GUI.getMaxX()*(0.4/9));
        img2.setImage(image);
        img2.setFitHeight(GUI.getMaxY()*(0.4/9));
        img2.setFitWidth(GUI.getMaxX()*(0.4/9));
        img3.setImage(image);
        img3.setFitHeight(GUI.getMaxY()*(0.4/9));
        img3.setFitWidth(GUI.getMaxX()*(0.4/9));
        img4.setImage(image);
        img4.setFitHeight(GUI.getMaxY()*(0.4/9));
        img4.setFitWidth(GUI.getMaxX()*(0.4/9));
        img5.setImage(image);
        img6.setImage(image);
        libraryID.getChildren().set(0,img1);
        //img1.fitHeightProperty().bind((ObservableValue<? extends Number>) libraryID.heightProperty());
        //img1.fitWidthProperty().bind(libraryID.li);
        libraryID.getChildren().set(1,img2);
        libraryID.getChildren().set(2,img3);
        libraryID.getChildren().set(3,img4);
        libraryID.getChildren().set(4,img5);
        libraryID.getChildren().set(5,img6);
        //libraryID.add(img1, 0,0);
        //getCil

        /*
        FileInputStream imageStream = null;
        try {
            imageStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FileInputStream imageStream1 = imageStream;
        Image image =new (imageStream);
        /*for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                ImageView imageView = new ImageView();
                FileInputStream imageStream = null;
                try {
                    imageStream = new FileInputStream(fileName);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Image image = new Image(imageStream);
                imageView.setImage(image);
                //libraryID.getChildren().set(imageView, i, j)

                libraryID.getChildren().add(imageView);
                libraryID.add(imageView, i, j);
                libraryID.getChildren().add(imageView);
                libraryID.visibleProperty().set(true);

                libraryID.getChildren().add(imageView);

            }
        }*/


    }

}
