package it.polimi.ingsw.view.gui.controllers;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.view.events.Move;
import javafx.application.Platform;
import javafx.scene.Node;
import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.view.gui.GUI;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameViewController implements Controller {
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
    private VBox vBoxCommonID;
    @FXML
    private HBox hBoxCommonID;
    @FXML
    private VBox vBoxCommonOwID;
    @FXML
    private HBox hBoxCommonOwID;
    @FXML ImageView commonCard1OwID;
    @FXML ImageView commonCard2OwID;
    @FXML
    private Circle turnCircleID;

    @FXML
    private TextFlow errorsTextID;
    private List<Coordinate> coordinates=new ArrayList<>();
    private GUI gui;
    private  VirtualModel virtualModel;
    private ArrayList<String> players=  new ArrayList<String>();
    private String personalCardFile =new String("EMPTY.png");
    private String commonCard1File =new String("CC1.jpg");
    private String commonCard2File =new String("CC2.jpg");
    private Integer[] Coordinate= new Integer[2];
    private boolean youTurn=false;
    private ArrayList<ImageView> aImgViewBoard =new ArrayList<ImageView>();
    private ArrayList<Image> aImgBoard =new ArrayList<Image>();
    private ArrayList<ImageView> aImgViewLibrary =new ArrayList<ImageView>();
    private ArrayList<Image> aImgLibrary =new ArrayList<Image>();
    private ArrayList<GridPane> aLibraryGridsOw = new ArrayList<GridPane>();
    private ArrayList<Label> aLabelLib =new ArrayList<Label>();


    @FXML private Label itemTileLabelID;
    @FXML private ImageView itemTile1ID;
    @FXML private ImageView itemTile2ID;
    @FXML private ImageView itemTile3ID;
    @FXML private GridPane boardOwID;
    @FXML private Label labelOwL1ID;
    @FXML private Label labelOwL2ID;
    @FXML private Label labelOwL3ID;
    @FXML private Label labelOwL4ID;
    @FXML private GridPane library1ID;
    @FXML private GridPane library2ID;
    @FXML private GridPane library3ID;
    @FXML private GridPane library4ID;
    @FXML private HBox itemTileBoxID;
    /*public GameViewController(GUI gui) {
        this.gui = gui;
        this.virtualModel=gui.getClient().getVirtualModel();
    }*/
    @Override
    public void setGui(GUI gui) {
        this.gui = gui;
        this.virtualModel=gui.getClient().getVirtualModel();
        this.aLibraryGridsOw.add(library1ID);
        this.aLibraryGridsOw.add(library2ID);
        this.aLibraryGridsOw.add(library3ID);
        this.aLibraryGridsOw.add(library4ID);
        this.aLabelLib.add(labelOwL1ID);
        this.aLabelLib.add(labelOwL2ID);
        this.aLabelLib.add(labelOwL3ID);
        this.aLabelLib.add(labelOwL4ID);
        labelOwL3ID.setVisible(false);
        labelOwL4ID.setVisible(false);
    }

    EventHandler clickItemTileBoardHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            System.out.println("pre click board");
            //personal card index needed
            if(youTurn==true){
                if(coordinates.size()<3){
                    Node node = (Node) t.getTarget();
                    int row = GridPane.getRowIndex(node);
                    int column = GridPane.getColumnIndex(node);
                    System.out.println(row +" " +column);
                    coordinates.add(new Coordinate(row, column));
                    if(coordinates.size()==1){
                        itemTile1ID.setImage(new Image(getClass().getResourceAsStream("/images/" + virtualModel.getBoard()[row][column].toString() + ".png")));
                        setItemTileClicked(itemTile1ID);
                        System.out.println("Board 1");
                    }
                    else if(coordinates.size()==2){
                        itemTile2ID.setImage(new Image(getClass().getResourceAsStream("/images/" + virtualModel.getBoard()[row][column].toString() + ".png")));
                        setItemTileClicked(itemTile2ID);
                        System.out.println("Board 2");
                    }
                    else if(coordinates.size()==3){
                        itemTile3ID.setImage(new Image(getClass().getResourceAsStream("/images/" + virtualModel.getBoard()[row][column].toString() + ".png")));
                        setItemTileClicked(itemTile3ID);
                        System.out.println("Board 3");
                    }
                    ImageView imageView = (ImageView) t.getSource();
                    imageView.setImage(new Image(getClass().getResourceAsStream("/images/EMPTY.png")));
                    System.out.println("You clicked " + imageView.getImage());
                }
                else{
                    setErrorsTextIDText("You can pic Max 3 Item Tiles");
                }
            }
        }
    };
    EventHandler clickItemTileLibraryHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            System.out.println("handle Library Clicl Pre E PRE CICLO");
            //personal card index needed
            if(youTurn==true&&coordinates.size()>0){
                Node node = (Node) t.getTarget();
                int column = GridPane.getColumnIndex(node);
                //inviare al server colonna scelta
                System.out.println("handle Library Clicl Pre");
                gui.getClient().handle(new Move(coordinates,column));
                System.out.println("handle Library Clicl Pre");
                coordinates.clear();
            }
        }
    };

    public void setItemTileHBox(){
        itemTileLabelID.setText("Item Tile selected [Max 3]:");

    }
    public void setItemTileClicked(ImageView imageView){
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(gui.getMaxX()*(0.030));
        imageView.setFitWidth(gui.getMaxX()*(0.030));
    }
    public void setErrorsTextIDText(String error){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Text text = new Text(error);
                errorsTextID.getChildren().add(text);
                errorsTextID.setVisible(true);
            }
        });
    }


    public void fullLibrary(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(int c=0; c<5; c++){
                    for(int r=0; r<6; r++){
                        ImageView imageView= new ImageView(new Image(getClass().getResourceAsStream("/images/" + virtualModel.getLibrary()[r][c].toString() + ".png")));
                        imageView.setX(90);
                        imageView.setY(90);
                        imageView.setPreserveRatio(true);
                        imageView.setFitHeight(gui.getMaxX()*(0.030));
                        imageView.setFitWidth(gui.getMaxX()*(0.030));
                        libraryID.add(imageView,c,r);
                        //libraryID.getChildren().get(r+6*c).setOnMouseClicked(clickItemTileLibraryHandler);
                    }
                }
                libraryID.setLayoutX(gui.getMaxX()*0.05);
                libraryID.setLayoutY(gui.getMaxY()*0.55);
                libraryID.setHgap(2);
                libraryID.setVgap(2);
                libraryID.setOnMouseClicked(clickItemTileLibraryHandler);
                libraryID.setVisible(true);
            }
        });

    }

    public void fullBoard(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(int c=0; c<9; c++){
                    for(int r=0; r<9; r++ ){
                        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/" + virtualModel.getBoard()[r][c].toString() + ".png")));
                        imageView.setX(90);
                        imageView.setY(90);
                        imageView.setPreserveRatio(true);
                        imageView.setFitHeight(gui.getMaxX()*(0.040));
                        imageView.setFitWidth(gui.getMaxX()*(0.040));
                        boardID.add(imageView,c,r);
                        //boardID.getChildren().get(c*9+r).setOnMouseClicked(clickItemTileBoardHandler);
                        //boardOwID.add(aImgViewBoard.get(c*9+r),c,r);
                    }
                }
                boardID.setLayoutX(gui.getMaxX()*0.55);
                boardID.setLayoutY(gui.getMaxY()*0.35);
                boardID.setHgap(2);
                boardID.setVgap(2);
                boardID.setOnMouseClicked(clickItemTileBoardHandler);
                boardID.setVisible(true);
            }
        });

    }


    public void printBoardOw(){
        for(int c=0; c<9; c++){
            for(int r=0; r<9; r++){
                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/" + virtualModel.getBoard()[r][c].toString() + ".png")));
                imageView.setX(90);
                imageView.setY(90);
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(gui.getMaxX()*(0.040));
                imageView.setFitWidth(gui.getMaxX()*(0.040));
                boardOwID.add(imageView,c,r);
            }
        }
        boardOwID.setLayoutX(gui.getMaxX()*0.55);
        boardOwID.setLayoutY(gui.getMaxY()*0.35);
        boardOwID.setHgap(2);
        boardOwID.setVgap(2);
        boardOwID.setVisible(true);
    }
    public void printAllLibrary(){
        int i=0;
        for(Map.Entry<String, ItemTileType[][]> libraryMap : virtualModel.getClientUsernameLibrary().entrySet()){
            for(int c=0; c<5; c++){
                for(int r=0; r<6; r++){
                    ImageView imageViewLibOw = new ImageView(new Image(getClass().getResourceAsStream("/images/" + libraryMap.getValue()[r][c].toString() + ".png")));
                    imageViewLibOw.setX(90);
                    imageViewLibOw.setY(90);
                    imageViewLibOw.setPreserveRatio(true);
                    imageViewLibOw.setFitHeight(gui.getMaxX()*(0.040));
                    imageViewLibOw.setFitWidth(gui.getMaxX()*(0.040));
                    aLibraryGridsOw.get(i).add(imageViewLibOw,c,r);
                }
            }
            aLibraryGridsOw.get(i).setLayoutX(gui.getMaxX()*0.05);
            aLibraryGridsOw.get(i).setLayoutY(gui.getMaxY()*0.55);
            aLibraryGridsOw.get(i).setHgap(2);
            aLibraryGridsOw.get(i).setVgap(2);
            aLibraryGridsOw.get(i).setVisible(true);
            aLabelLib.get(i).setText(libraryMap.getKey());
            aLabelLib.get(i).setVisible(true);
            i++;
        }
        library1ID.setLayoutX(gui.getMaxX()*0.01);
        library1ID.setLayoutY(gui.getMaxY()*0.10);
        library2ID.setLayoutX(gui.getMaxX()*0.27);
        library2ID.setLayoutY(gui.getMaxY()*0.10);
        library3ID.setLayoutX(gui.getMaxX()*0.01);
        library3ID.setLayoutY(gui.getMaxY()*0.60);
        library4ID.setLayoutX(gui.getMaxX()*0.27);
        library4ID.setLayoutY(gui.getMaxY()*0.6);
        labelOwL1ID.setLayoutX(gui.getMaxX()*0.01);
        labelOwL1ID.setLayoutY(gui.getMaxY()*0.05);
        labelOwL2ID.setLayoutX(gui.getMaxX()*0.27);
        labelOwL2ID.setLayoutY(gui.getMaxY()*0.05);
        labelOwL3ID.setLayoutX(gui.getMaxX()*0.05);
        labelOwL3ID.setLayoutY(gui.getMaxY()*0.50);
        labelOwL4ID.setLayoutX(gui.getMaxX()*0.27);
        labelOwL4ID.setLayoutY(gui.getMaxY()*0.50);
    }
    public void notYourTurn(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(int c=0; c<9; c++) {
                    for (int r = 0; r < 9; r++) {
                        boardID.getChildren().get(c*9+r).setOnMouseClicked(null);
                    }
                }
                for(int c=0; c<5; c++){
                    for(int r=0; r<6; r++) {
                        libraryID.getChildren().get(c*6+r).setOnMouseClicked(null);
                    }
                }
            }
        });
    }

    public void yourTurn(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                errorsTextID.getChildren().clear();
                for(int c=0; c<9; c++) {
                    for (int r = 0; r < 9; r++) {
                        boardID.getChildren().get(c*9+r).setOnMouseClicked(clickItemTileBoardHandler);
                    }
                }
                for(int c=0; c<5; c++){
                    for(int r=0; r<6; r++) {
                        libraryID.getChildren().get(c*6+r).setOnMouseClicked(clickItemTileLibraryHandler);
                    }
                }
            }
        });
    }
    public void printError (String error){
        //errorsTextID.setText(null);
        //errorsTextID.setText(error);
        errorsTextID.setVisible(true);
    }

    public void personalCardInizializer(){
        personalCardImgID.setImage(new Image(getClass().getResourceAsStream("/images/Personal_Goals"+ virtualModel.getNumberPersonalCard() +".png")));
        vBoxPersonalID.setLayoutX(gui.getMaxX()*0.35);
        vBoxPersonalID.setLayoutY(gui.getMaxY()*0.60);
        vBoxPersonalID.setFillWidth(true);
        //vBoxPersonalID.maxWidth(GUI.getMaxX()*0.17);
        vBoxPersonalID.setMaxWidth(gui.getMaxX()*0.17);
        vBoxPersonalID.setMinWidth(gui.getMaxX()*0.17);
        personalCardImgID.setPreserveRatio(true);
        personalCardImgID.setFitWidth(gui.getMaxX()*0.10);
        personalCardImgID.setFitHeight(gui.getMaxX()*0.10*(756*1110));
    }

    public void commonCardInizializzer(){
        commonCard1ID.setImage(new Image(getClass().getResourceAsStream("/images/CC"+ String.valueOf(virtualModel.getCommonGoalCards().get(0).getValue0()) + ".jpg")));
        commonCard1OwID.setImage(new Image(getClass().getResourceAsStream("/images/CC"+ String.valueOf(virtualModel.getCommonGoalCards().get(0).getValue0()) + ".jpg")));
        commonCard2ID.setImage(new Image(getClass().getResourceAsStream("/images/CC"+ String.valueOf(virtualModel.getCommonGoalCards().get(1).getValue0()) + ".jpg")));
        commonCard2OwID.setImage(new Image(getClass().getResourceAsStream("/images/CC"+ String.valueOf(virtualModel.getCommonGoalCards().get(1).getValue0()) + ".jpg")));
        commonCard1ID.setPreserveRatio(true);
        commonCard1OwID.setPreserveRatio(true);
        commonCard2ID.setPreserveRatio(true);
        commonCard2OwID.setPreserveRatio(true);
        vBoxCommonID.setMaxHeight(gui.getMaxY()*0.25);
        vBoxCommonOwID.setMaxHeight(gui.getMaxY()*0.25);
        vBoxCommonID.setMaxWidth(gui.getMaxX()*0.35);
        vBoxCommonOwID.setMaxWidth(gui.getMaxX()*0.35);
        hBoxCommonID.setMaxHeight(gui.getMaxX()*0.35*(1385/913));
        hBoxCommonOwID.setMaxHeight(gui.getMaxX()*0.35*(1385/913));
        hBoxCommonID.setMaxWidth(gui.getMaxX()*0.35);
        hBoxCommonOwID.setMaxWidth(gui.getMaxX()*0.35);
        commonCard1ID.setFitWidth(gui.getMaxX()*0.15);
        commonCard1OwID.setFitWidth(gui.getMaxX()*0.15);
        commonCard1ID.setFitHeight(gui.getMaxX()*0.15*(913/1365));
        commonCard1OwID.setFitHeight(gui.getMaxX()*0.15*(913/1365));
        commonCard2ID.setFitWidth(gui.getMaxX()*0.15);
        commonCard2OwID.setFitWidth(gui.getMaxX()*0.15);
        commonCard2ID.setFitHeight(gui.getMaxX()*0.15*(913/1365));
        commonCard2OwID.setFitHeight(gui.getMaxX()*0.15*(913/1365));
        vBoxCommonID.setLayoutY(gui.getMaxY()*0.05);
        vBoxCommonOwID.setLayoutY(gui.getMaxY()*0.05);
        vBoxCommonID.setLayoutX(gui.getMaxX()*0.05);
        vBoxCommonOwID.setLayoutX(gui.getMaxX()*0.55);
        vBoxCommonID.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.YELLOW,null,null)));
        vBoxCommonOwID.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.YELLOW,null,null)));
    }
    /*
    public void prova(){
        personalCardInizializer();
        commonCardInizializzer();
        fullBoard();
        fullLibrary();
        fillLibrarySelectionID();
        ItemTileType[][] board = virtualModel.getBoard();
        ItemTileType[][] currentLibrary = virtualModel.getLibrary();
        ItemTileType[][] personalCardLibrary = virtualModel.getPersonalGoalCard();
    }
     */


    public void setErrorBox(){
        errorsTextID.setMaxWidth(gui.getMaxX()*0.14);
        errorsTextID.setMaxHeight(gui.getMaxY()*0.14);
        errorsTextID.setLayoutX(gui.getMaxX()*0.43);
        errorsTextID.setLayoutY(gui.getMaxY()*0.10);
    }




    public void rePrintAll(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                resetPrint();
                fullBoard();
                printAllLibrary();
                printBoardOw();
                fullLibrary();
                boardID.setOnMouseClicked(clickItemTileBoardHandler);
                libraryID.setOnMouseClicked(clickItemTileLibraryHandler);
            }
        });
    }

    public void resetPrint(){
        libraryID.setVisible(false);
        boardOwID.setVisible(false);
        boardID.setVisible(false);
        libraryID.getChildren().clear();
        boardOwID.getChildren().clear();
        boardID.getChildren().clear();
        int i=0;
        for(Map.Entry<String, ItemTileType[][]> libraryMap : virtualModel.getClientUsernameLibrary().entrySet()){
            aLibraryGridsOw.get(i).setVisible(false);
            aLibraryGridsOw.get(i).getChildren().clear();
            //aLibraryGridsOw.get(i).getChildren().remove(r+6*c);
            i++;
        }
        /*
        for(int c=0; c<5; c++){
            for(int r=0; r<6; r++) {
                //libraryID.getChildren().remove(r+6*c);
                libraryID.getChildren().clear();

            }
        }


        for(int c=0; c<9; c++) {
            for (int r = 0; r < 9; r++) {
                boardID.getChildren().remove(c*9+r);
                boardOwID.getChildren().remove(c*9+r);
            }
        }

         */
    }
    public void setUp() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setErrorBox();
                printAllLibrary();
                personalCardInizializer();
                commonCardInizializzer();
                fullLibrary();
                fullBoard();
                printBoardOw();
                libraryID.setOnMouseClicked(clickItemTileLibraryHandler);
                itemTileBoxID.setLayoutX(gui.getMaxX()*0.60);
                itemTileBoxID.setMaxHeight(gui.getMaxY()*0.7);
                ItemTileType[][] board = virtualModel.getBoard();
                ItemTileType[][] currentLibrary = virtualModel.getLibrary();
                ItemTileType[][] personalCardLibrary = virtualModel.getPersonalGoalCard();
            }
        });


    /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //libraryID dovrebbe numerare verticalmente

        personalCardInizializer();
        commonCardInizializzer();
        fullBoard();
        fullLibrary();
        fillLibrarySelectionID();
        /*
        ItemTileType[][] board = virtualModel.getBoard();
        ItemTileType[][] currentLibrary = virtualModel.getLibrary();
        ItemTileType[][] personalCardLibrary = virtualModel.getPersonalGoalCard();

        //boardID.setPadding(new Insets(5,5,5,5));



        /*
        for(int c=0; c<9; c++){
            for(int r=0; r<9; r++){
                Image image= new Image(getClass().getResourceAsStream("FRAME.png"));
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
        Image image= new Image(getClass().getResourceAsStream("FRAME.png"));
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
        } */


    }

    public void setYouTurn(boolean youTurn) {
        this.youTurn = youTurn;
    }

    public boolean isYouTurn() {
        return youTurn;
    }
}
