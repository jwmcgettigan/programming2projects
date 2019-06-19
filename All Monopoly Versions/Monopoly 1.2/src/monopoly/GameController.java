/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @version 1.2
 * @author Justin McGettigan
 */
public class GameController {
    
    //Need a main menu and a "start" button.
    //This will allow me to apply methods after initialization.
    
    private final double spriteSize = 0.05, spriteAdjust = 1 - spriteSize;
    private Image[] sprites = new Image[5];
    private ImageView[] tokens = new ImageView[5];
    private Board board;
    private BorderPane bPane;
    private Pane pane;
    private MenuBar menu;
    private Player player;
    
    public GameController(BorderPane bp, Pane p, MenuBar m){
        bPane = bp;
        pane = p;
        menu = m;
        board = new Board(bPane, menu);
        addBoardToPane();
        initializeTokens();
        chooseToken();
    }
    
    private void addBoardToPane(){
        pane.toBack();
        pane.getChildren().add(board.display());
    }
    
    private void initializeTokens() {
        sprites[0] = new Image("./resources/tokens/token1.png");
        sprites[1] = new Image("./resources/tokens/token2.png");
        sprites[2] = new Image("./resources/tokens/token3.png");
        sprites[3] = new Image("./resources/tokens/token4.png");
        sprites[4] = new Image("./resources/tokens/token5.png");
        for(int i = 0; i < sprites.length; i++){
            tokens[i] = new ImageView(sprites[i]);
            tokens[i].fitWidthProperty().bind(board.display().fitWidthProperty().multiply(spriteSize).multiply(board.display().scaleXProperty()));
            tokens[i].fitHeightProperty().bind(board.display().fitHeightProperty().multiply(spriteSize).multiply(board.display().scaleYProperty()));
            //pane.getChildren().add(tokens[i]);
            //tokens[i].toFront();
            initialTokenPosition(i);
        }
    }
    
    public void initialTokenPosition(int i){
        tokens[i].xProperty().bind(board.display().fitWidthProperty().multiply(spriteAdjust-0.025));
        tokens[i].yProperty().bind(board.display().fitHeightProperty().multiply(spriteAdjust-0.025));
        /*
        tokens[i].scaleXProperty().bind(board.display().scaleXProperty());
        tokens[i].scaleYProperty().bind(board.display().scaleYProperty());
        tokens[i].translateXProperty().bind(board.display().translateXProperty().multiply(spriteSize));
        tokens[i].translateYProperty().bind(board.display().translateYProperty().multiply(spriteSize));
        tokens[i].layoutXProperty().bind(board.display().layoutXProperty().multiply(spriteSize));
        tokens[i].layoutYProperty().bind(board.display().layoutYProperty().multiply(spriteSize));*/
    }
    
    @FXML
    public void chooseToken(){
        //put the 'token chooser' inside of the monopoly Os
        int choice = 2;
        for(int i = 0; i < tokens.length; i++){
            if(i == choice){
                pane.getChildren().add(tokens[i]);
                tokens[i].toFront();
                tokens[i].mouseTransparentProperty().set(true);
                player = new Player(tokens[i]);
            }
        }
    }
    
    public ImageView[] getTokens(){
        return tokens;
    }
    
    public Board getBoard(){
        return board;
    }
    
    public Player getPlayer(){
        return player;
    }
}
