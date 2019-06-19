/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * @version 1.2
 * @author Justin McGettigan
 */
public class Board{
    
    /*
    private ImageView board, sprite;
    private BorderPane bp;
    private Tile[] tiles;
    private Token player;
    private TranslateTransition[] translate;
    private double xRatio, yRatio;*/
    
    private int angle;
    
    private RotateTransition rotate;
    
    private Tile[] tiles;
    
    private ImageView display;
    
    private BorderPane bPane;
    
    private MenuBar menu;
    
    public Board(BorderPane bp, MenuBar m){
        angle = 0;
        tiles = new Tile[40];
        bPane = bp;
        menu = m;
        display = new ImageView(new Image("./resources/BoardAttempt5.jpg", 2930, 2930, false, false));
        initialize(bPane, menu);
    }
    
    public void initialize(BorderPane bPane, MenuBar menu){
        display.fitHeightProperty().bind(bPane.heightProperty().subtract(menu.heightProperty()));
        display.fitWidthProperty().bind(display.fitHeightProperty());
        display.xProperty().bind(bPane.widthProperty().divide(2).subtract(display.fitWidthProperty().divide(2)));
    }
    
    public void initializeTiles(){
        //display.setFitHeight(bPane.getHeight()-menu.getHeight());
        //display.setFitWidth(display.getFitHeight());
        double width = display.getFitWidth()*0.9, height = display.getFitHeight()*0.9;
        double centerX = width/2, centerY = height/2;
        for(int i = 0; i < tiles.length; i++){
            tiles[i] = new Tile(i);
        }
        //need to take transformations into account with coordinates.....
        tiles[0].setCoord(width*0, 0);
        //tiles[1].setCoord(-centerX, -centerY);
        tiles[1].setCoord(width*-0.1, 0);
        tiles[2].setCoord(width*-0.2, 0);
        tiles[3].setCoord(width*-0.3, 0);
        tiles[4].setCoord(width*-0.4, 0);
        tiles[5].setCoord(width*-0.5, 0);
        tiles[6].setCoord(width*-0.6, 0);
        tiles[7].setCoord(width*-0.7, 0);
        tiles[8].setCoord(width*-0.8, 0);
        tiles[9].setCoord(width*-0.9, 0);
        tiles[10].setCoord(-1, 0);
    }
    
    public ImageView display(){
        return display;
    }
    
    public void rotate(){
        //angle-=90;
        rotate = new RotateTransition(Duration.millis(1000), display);
        rotate.setByAngle(-90);
        rotate.play();
        //display.rotateProperty().set(90);
    }
    
    public Tile[] getTiles(){
        return tiles;
    }
    
    
    
    
    
    
    
    /*
    public Board(ImageView b, BorderPane bP, ImageView s){
        board = b;
        bp = bP;
        sprite = s;
        translate = new TranslateTransition[9];
        tiles = new Tile[40];
        initialSpritePosition();
    }
    
    private void initializeTiles(){
        double bWidth = board.getFitWidth(), bHeight = board.getFitHeight();
        double normal = 0.1, corner = 0.11;
        System.out.println("Board Width: " + bWidth);
        System.out.println("Board Height: " + bHeight);
        
    }
    
    public void initialSpritePosition(){
        xRatio = 0.95;
        yRatio = 0.95;
        sprite.xProperty().bind(board.fitWidthProperty().multiply(xRatio-0.025));
        sprite.yProperty().bind(board.fitHeightProperty().multiply(yRatio-0.025));
    }
    
    public void move1(){
        initializeTiles();
        player = new Token(sprite, tiles);
        player.move(tiles[0], tiles[20]);
        
        *//*
        double bWidth = board.getFitWidth(), bHeight = board.getFitHeight();
        double[] tile = new double[10];
        for(int i = 0; i < tile.length; i++){
            tile[i] = bWidth * -(0.095*i);
        }
        SequentialTransition seqTransition = new SequentialTransition(new PauseTransition(Duration.millis(1000)));
        int target = 5;
        
        for(int i = 0; i < target; i++){
            tiles[i].getTranslate().setFromX(tile[i]);
            tiles[i].getTranslate().setToX(tile[i+1]);
            seqTransition.getChildren().add(tiles[i].getTranslate());
        }
        seqTransition.play();*//*
    }
    
    public void move2(){
        //translate.setFromX(board.getFitWidth()*-0.11);
        //translate.setToX(board.getFitWidth()*-0.2);
        //translate.play();
        
        //tiles[1].translate();
    }*/
    
    /*
        for(int i = 0; i < tiles.length; i++){
            tiles[i] = new Tile(i);
            if(i < 1){
                tiles[i].setCoord(bWidth*-corner, 0);
            } else if(i < 10){
                tiles[i].setCoord(tiles[i-1].getX()-(bWidth*normal), 0);
            } else if(i < 11){
                tiles[i].setCoord(tiles[i-1].getX()-(bWidth*corner), 0);
            } else if(i < 20){
                tiles[i].setCoord(tiles[i-1].getX(), bHeight*normal);
            } else if(i < 21){
                tiles[i].setCoord(tiles[i-1].getX(), tiles[i-1].getY()-(bHeight*corner));
            } else if(i < 30){
                tiles[i].setCoord(tiles[i-1].getX()+(bWidth*normal), tiles[i-1].getY());
            } else if(i < 31){
                tiles[i].setCoord(tiles[i-1].getX()+(bWidth*corner), tiles[i-1].getY());
            } else if(i < 40){
                tiles[i].setCoord(tiles[i-1].getX(), tiles[i-1].getY()+(bHeight*normal));
            }
        }*/
    
    /*
    assign coord value to each tile.
    from(current position)
    to(target position)
    
    move(from, to);
    move(current, target)
    
    //translate[0].setFromX(tile[0]);
    translate[0].setToX(tile[1]);
    translate.play();
    
    translate[1].setFromX(translate[0].getToX());
    translate[1].setToX(tile[2]);
    
    translate.setFromX(tile before moving);
    translate.setToX(tile after moving);
    translate.play();
    */
    
    /*
    0->1
    
    [1->9]
    
    9->10
    10->11
    
    [11->19]
    
    19->20
    20->21
    
    [21->29]
    
    29->30
    30->31
    
    [31->39]
    
    39->0
    */
    
    /*
      
    20->21->22->23->24->25->26->27->28->29->30
    /\                                      \/
    19                                      31
    /\                                      \/
    18                                      32
    /\                                      \/
    17                                      33
    /\                                      \/
    16                                      34
    /\                                      \/
    15                                      35
    /\                                      \/
    14                                      36
    /\                                      \/
    13                                      37
    /\                                      \/
    12                                      38
    /\                                      \/
    11                                      39
    /\                                      \/
    10<-09<-08<-07<-06<-05<-04<-03<-02<-01<-00
    */
}
