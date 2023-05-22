package it.polimi.ingsw.view.gui.controllers;
import it.polimi.ingsw.model.gameEntity.Coordinate;
import it.polimi.ingsw.view.events.Move;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import it.polimi.ingsw.client.VirtualModel;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import it.polimi.ingsw.view.gui.GUI;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private AnchorPane anchorPaneID;
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
    @FXML
    private ImageView commonCard1OwID;
    @FXML
    private ImageView commonCard2OwID;
    @FXML
    private Circle turnCircleID;

    @FXML
    private VBox vBox_messages;
    @FXML
    private ScrollPane sp_main;
    @FXML
    private Label itemTileLabelID;
    @FXML
    private ImageView itemTile1ID;
    @FXML
    private ImageView itemTile2ID;
    @FXML
    private ImageView itemTile3ID;
    @FXML
    private GridPane boardOwID;
    @FXML
    private Label labelOwL1ID;
    @FXML
    private Label labelOwL2ID;
    @FXML
    private Label labelOwL3ID;
    @FXML
    private Label labelOwL4ID;
    @FXML
    private GridPane library1ID;
    @FXML
    private GridPane library2ID;
    @FXML
    private GridPane library3ID;
    @FXML
    private GridPane library4ID;
    @FXML
    private HBox itemTileBoxID;

    @FXML
    private VBox vBoxLibraryID;
    @FXML
    private Label labelLibraryID;


    private List<Coordinate> coordinates=new ArrayList<>();
    private GUI gui;
    private  VirtualModel virtualModel;
    private boolean youTurn=false;
    private ArrayList<GridPane> aLibraryGridsOw = new ArrayList<GridPane>();
    private ArrayList<Label> aLabelLib =new ArrayList<Label>();
    private String libraryBaseText = new String("Your Library: ");
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
        vBox_messages.setVisible(false);
        sp_main.setVisible(false);
        commonCardLabelID.setVisible(false);
        personalCardLabelID.setVisible(false);
        itemTileLabelID.setVisible(false);
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
                labelLibraryID.setText(libraryBaseText+"You selected row number "+ String.valueOf(column+1));
                //System.out.println("handle Library Clicl Pre");
                gui.getClient().handle(new Move(coordinates,column));
                //System.out.println("handle Library Clicl Pre");
                coordinates.clear();
                itemTile1ID.setImage(null);
                itemTile2ID.setImage(null);
                itemTile3ID.setImage(null);
            }
        }
    };

    public void setItemTileClicked(ImageView imageView){
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(gui.getMaxX()*(0.030));
        imageView.setFitWidth(gui.getMaxX()*(0.030));
        imageView.setVisible(true);
    }
    public void setErrorsTextIDText(String error){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                HBox hBox=new HBox();
                hBox.setAlignment(Pos.CENTER_LEFT);
                hBox.setPadding(new Insets(3,3,3,3));
                Text text = new Text(error);
                text.setFill(Color.color(0,0,0));
                TextFlow textFlow =new TextFlow(text);
                textFlow.setStyle("-fx-text-fill: black; -fx-background-color: lime; -fx-background-radius: 10px ;");
                textFlow.setPadding(new Insets(3,3,3,3));
                hBox.getChildren().add(textFlow);
                vBox_messages.getChildren().add(hBox);
                vBox_messages.setVisible(true);
            }
        });
    }


    public void fullLibrary(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                labelLibraryID.setText(libraryBaseText);
                labelLibraryID.setMinHeight(30);
                for(int c=0; c<5; c++){
                    for(int r=0; r<6; r++) {
                        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/" + virtualModel.getLibrary()[r][c].toString() + ".png")));
                        imageView.setX(90);
                        imageView.setY(90);
                        imageView.setPreserveRatio(true);
                        imageView.setFitHeight(gui.getMaxX() * (0.030));
                        imageView.setFitWidth(gui.getMaxX() * (0.030));
                    libraryID.add(imageView,c,r);
                    }
                }
                vBoxLibraryID.setLayoutX(gui.getMaxX()*0.05*0.9);
                vBoxLibraryID.setLayoutY(gui.getMaxY()*0.50);
                libraryID.setHgap(2);
                libraryID.setVgap(2);
                libraryID.setMinSize((gui.getMaxX()*(0.030)*5+4*5),(gui.getMaxX()*(0.030)*6+4*6));
                libraryID.setMaxSize((gui.getMaxX()*(0.030)*5+4*5),(gui.getMaxX()*(0.030)*6+4*6));
                libraryID.setOnMouseClicked(clickItemTileLibraryHandler);
                vBoxLibraryID.setMinWidth(gui.getMaxX()*0.40);
                vBoxLibraryID.setMinHeight(gui.getMaxY()*0.50);
                vBoxLibraryID.setMaxHeight(gui.getMaxY()*0.50);
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
                        imageView.setFitHeight(gui.getMaxX()*(0.035));
                        imageView.setFitWidth(gui.getMaxX()*(0.035));
                        imageView.setOnMouseClicked(mouseEvent -> {
                            if(youTurn==true &&coordinates.size()<3){
                                imageView.setImage(new Image(getClass().getResourceAsStream("/images/BLACK.png")));
                                imageView.setFitHeight(gui.getMaxX()*(0.035));
                                imageView.setFitWidth(gui.getMaxX()*(0.035));
                            }
                        });
                        boardID.add(imageView,c,r);
                    }
                }

                boardID.setHgap(2);
                boardID.setVgap(2);
                boardID.setMinSize((gui.getMaxX()*(0.035)*9+2*9),(gui.getMaxX()*(0.035)*9+2*9));
                boardID.setMaxSize((gui.getMaxX()*(0.035)*9+2*9),(gui.getMaxX()*(0.035)*9+2*9));
                //boardID.setOnMouseClicked(clickItemTileBoardHandler);
                boardID.setLayoutX(gui.getMaxX()*0.55);
                //boardID.setLayoutY(gui.getMaxY()*0.30);
                boardID.setLayoutY(gui.getMaxY()*0.35);
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
                imageView.setFitHeight(gui.getMaxX()*(0.035));
                imageView.setFitWidth(gui.getMaxX()*(0.035));
                boardOwID.add(imageView,c,r);
            }
        }
        boardOwID.setHgap(2);
        boardOwID.setVgap(2);
        boardOwID.setMinSize((gui.getMaxX()*(0.035)*9+2*9),(gui.getMaxX()*(0.035)*9+2*9));
        boardOwID.setMaxSize((gui.getMaxX()*(0.035)*9+2*9),(gui.getMaxX()*(0.035)*9+2*9));
        boardOwID.setLayoutX(gui.getMaxX()*0.55);
        boardOwID.setLayoutY(gui.getMaxY()*0.35);
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
                    imageViewLibOw.setFitHeight(gui.getMaxX()*(0.030));
                    imageViewLibOw.setFitWidth(gui.getMaxX()*(0.030));
                    aLibraryGridsOw.get(i).add(imageViewLibOw,c,r);
                }
            }
            aLibraryGridsOw.get(i).setLayoutX(gui.getMaxX()*0.05);
            aLibraryGridsOw.get(i).setLayoutY(gui.getMaxY()*0.55);
            aLibraryGridsOw.get(i).setHgap(2);
            aLibraryGridsOw.get(i).setVgap(2);
            aLibraryGridsOw.get(i).setMinSize((gui.getMaxX()*(0.030)*5+2*5),(gui.getMaxX()*(0.030)*6+2*6));
            aLibraryGridsOw.get(i).setMaxSize((gui.getMaxX()*(0.030)*5+2*5),(gui.getMaxX()*(0.030)*6+2*6));
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



    public void personalCardInizializer(){
        personalCardImgID.setImage(new Image(getClass().getResourceAsStream("/images/Personal_Goals"+ virtualModel.getNumberPersonalCard() +".png")));
        vBoxPersonalID.setLayoutX(gui.getMaxX()*0.35);
        vBoxPersonalID.setLayoutY(gui.getMaxY()*0.60);
        vBoxPersonalID.setFillWidth(true);
        personalCardImgID.setPreserveRatio(true);
        personalCardImgID.setFitWidth(gui.getMaxX()*0.10);
        personalCardImgID.setFitHeight(gui.getMaxX()*0.10*(756*1110));
        vBoxPersonalID.setMaxWidth(personalCardImgID.getFitWidth()*1.1);
        vBoxPersonalID.setMinWidth(personalCardImgID.getFitWidth()*1.1);
        vBoxPersonalID.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
        personalCardLabelID.setVisible(true);
        itemTileLabelID.setMinWidth(125);
        itemTileLabelID.setVisible(true);

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
        vBoxCommonID.setLayoutY(gui.getMaxY()*0.01);
        vBoxCommonOwID.setLayoutY(gui.getMaxY()*0.05);
        vBoxCommonID.setLayoutX(gui.getMaxY()*0.01);
        vBoxCommonOwID.setLayoutX(gui.getMaxX()*0.55);
        vBoxCommonID.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.YELLOW,null,null)));
        vBoxCommonOwID.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.YELLOW,null,null)));
        vBoxCommonID.setPadding(new Insets(10,10,10,10));
        vBoxCommonOwID.setPadding(new Insets(10,10,10,10));
        commonCardLabelID.setVisible(true);
    }


    public void setErrorBox(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox_messages.setMaxWidth(gui.getMaxX()*0.14);
                vBox_messages.setMaxHeight(gui.getMaxY()*0.12);
                vBox_messages.setMinWidth(gui.getMaxX()*0.14);
                vBox_messages.setMinHeight(gui.getMaxY()*0.12);
                sp_main.setMaxWidth(gui.getMaxX()*0.16);
                sp_main.setMaxHeight(gui.getMaxY()*0.14);
                sp_main.setMinWidth(gui.getMaxX()*0.16);
                sp_main.setMinHeight(gui.getMaxY()*0.14);
                vBox_messages.setLayoutX(gui.getMaxX()*0.45);
                vBox_messages.setLayoutY(gui.getMaxY()*0.01);
                sp_main.setMinWidth(gui.getMaxX()*0.14);
                sp_main.setMinHeight(gui.getMaxY()*0.12);
                sp_main.setLayoutX(gui.getMaxX()*0.46);
                sp_main.setLayoutY(gui.getMaxY()*0.02);
                vBox_messages.setVisible(true);
                sp_main.setVisible(true);
            }
        });
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
            i++;
        }
    }
    public void setUp() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                anchorPaneID.setBackground(new Background(
                        new BackgroundImage(
                                new Image(getClass().getResourceAsStream("/images/Parquet.jpg")),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                        )));
                setErrorBox();
                printAllLibrary();
                personalCardInizializer();
                commonCardInizializzer();
                fullLibrary();
                fullBoard();
                printBoardOw();
                libraryID.setOnMouseClicked(clickItemTileLibraryHandler);
                itemTileBoxID.setLayoutX(gui.getMaxX()*0.70);
                itemTileBoxID.setMaxHeight(gui.getMaxY()*0.7);
            }
        });



    }
    public void loadFinalPage(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gui.changeStage("/fxml/FinalPage.fxml");
            }
        });
    }

    public void setYouTurn(boolean youTurn) {
        this.youTurn = youTurn;
    }

}
