/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @version 1.2
 * @author Justin McGettigan
 */
public class FXMLDocumentController implements Initializable {
    
    private Stage stage;
    public void setStage(Stage s) {
        stage = s;
    }
    public Stage getStage() {
        return stage;
    }
    
    @FXML
    private BorderPane bPane;
    @FXML
    private MenuBar menu;
    @FXML
    private Pane pane;
    @FXML
    private TextField from, to;
    
    //private Image[] sprites = new Image[5];
    //private ImageView[] tokens = new ImageView[5];
    //private final double spriteSize = 0.05, spriteAdjust = 1 - spriteSize;
    
    //private Board boardObject;
    private GameController game;
    
    private ViewController view;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameController(bPane, pane, menu);
        view = new ViewController(bPane, pane, menu, game);
        
    }
    
    @FXML
    private void moveToken(){
        game.getBoard().initializeTiles();
        game.getPlayer().move(game.getBoard().getTiles()[Integer.valueOf(from.getText())], game.getBoard().getTiles()[Integer.valueOf(to.getText())]);
    }
    @FXML
    private void rotateBoard(){
        game.getBoard().rotate();
    }

    @FXML
    private void printDebugMessage() {
        System.out.println("Board width: " + game.getBoard().display().getFitWidth());
        System.out.println("Board height: " + game.getBoard().display().getFitHeight());
        System.out.println("Board x: " + game.getBoard().display().getX());
        System.out.println("Board y: " + game.getBoard().display().getY());
        System.out.println("Board transX: " + game.getBoard().display().getTranslateX());
        System.out.println("Board transY: " + game.getBoard().display().getTranslateY());
        System.out.println("Board scaleX: " + game.getBoard().display().getScaleX());
        System.out.println("Board scaleY: " + game.getBoard().display().getScaleY());
        System.out.println("Board layoutX: " + game.getBoard().display().getLayoutX());
        System.out.println("Board layoutY: " + game.getBoard().display().getLayoutY());
        //System.out.println("BorderPane width: " + bPane.widthProperty().get());
        //System.out.println("BorderPane height: " + bPane.heightProperty().get());
        System.out.println("Token width: " + game.getTokens()[2].getFitWidth());
        System.out.println("Token height: " + game.getTokens()[2].getFitHeight());
        System.out.println("Token x: " + game.getTokens()[2].getX());
        System.out.println("Token y: " + game.getTokens()[2].getY());
        System.out.println("Token transX: " + game.getTokens()[2].getTranslateX());
        System.out.println("Token transY: " + game.getTokens()[2].getTranslateY());
        System.out.println("Token scaleX: " + game.getTokens()[2].getScaleX());
        System.out.println("Token scaleY: " + game.getTokens()[2].getScaleY());
        System.out.println("Token layoutX: " + game.getTokens()[2].getLayoutX());
        System.out.println("Token layoutY: " + game.getTokens()[2].getLayoutY());
        
        //System.out.println("Title: " + stage.titleProperty().get());
    }
    
    @FXML
    private MenuItem small, medium, large, huge;
    @FXML
    private void changeResolution(ActionEvent event) {

    }
    
    //private Stage stage = Monopoly.mainStage;
    //private Scene scene;
    /*Questions
        -How to refer to the scene?
            -.getScene()
            -https://stackoverflow.com/questions/13015537/javafx-class-controller-stage-window-reference
            -Do I even need to?
        -How do I detect keypresses at any time?
            -KeyListener?
            -https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
    
     */
    /*Implement 
        -fullscreen option
        -custom mouse cursor (hand?)
        -The board must look good in fullscreen mode
        -when lands on corner, zooms out, board rotates, zooms in
        -optional zoom feature
     */
    
    /*
    @FXML
    private void changeResolution(ActionEvent event){
        System.out.println("source:" + ((Control)event.getSource()).getId());
        int[][] list = null;
        String[][] List = new String[4][2];
        List[0] = small.textProperty().get().split("x");
        List[1] = medium.textProperty().get().split("x");
        List[2] = large.textProperty().get().split("x");
        List[3] = huge.textProperty().get().split("x");
        for(String[] item : List){
            for(String i : item){
                System.out.println(i + " ");
            }
        }
        for(int i = 0; i < List.length; i++){
            for(int k = 0; i < List[0].length; k++){
                list[i][k] = Integer.valueOf(List[i][k]);
            }
        }
        
        //stage.setWidth(spriteSize);
        //stage.setHeight(spriteSize);
    }*/
 /*
    void move(KeyEvent key) {
        switch (key.getCode()) {
            case RIGHT:
                x.valueProperty().add(10);
            case LEFT:
                x.valueProperty().subtract(10);
            case UP:
                y.valueProperty().add(10);
            case DOWN:
                y.valueProperty().subtract(10);
            default:
                break;
        }
    }*/

    /*
        bp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                if(key.getCode()==KeyCode.W){
                    System.out.println("up");
                    y.valueProperty().subtract(10);
                }
                if(key.getCode()==KeyCode.A){
                    System.out.println("left");
                    x.valueProperty().subtract(10);
                }
                if(key.getCode()==KeyCode.S){
                    System.out.println("down");
                    y.valueProperty().add(10);
                }
                if(key.getCode()==KeyCode.D){
                    System.out.println("right");
                    x.valueProperty().add(10);
                }
                /*
                switch (key.getCode()) {
                    case W: //up
                        System.out.println("up");
                        y.valueProperty().subtract(10);
                    case A: //left
                        System.out.println("left");
                        x.valueProperty().subtract(10);
                    case S: //down
                        System.out.println("down");
                        y.valueProperty().add(10);
                    case D: //right
                        System.out.println("right");
                        x.valueProperty().add(10);
                    default:
                        break;
                }
            }
        });*/
}
