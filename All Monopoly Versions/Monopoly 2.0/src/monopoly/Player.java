/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 * @version 2.0
 * @author Justin McGettigan
 */
public class Player {
    //private Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    private ImageView token;
    private Board board;
    private int player;
    private TranslateTransition translate;
    private Tile currentTile;
    private int gold, revolution;
    private ArrayList<Tile> properties;
    private boolean bankrupt;
    
    public Player(Image sprite, int p){
        token = new ImageView(sprite);
        token.setPreserveRatio(false);
        player = p;
        properties = new ArrayList<>();
        gold = 1500;
        revolution = 0;
        bankrupt = false;
    }
    
    public void move(Tile current, Tile target){
        translate = new TranslateTransition(Duration.millis(1000), token);
        translate.setFromX(current.getX());
        translate.setFromY(current.getY());
        translate.setToX(target.getX());
        translate.setToY(target.getY());
        translate.play();
        currentTile = target;
    }
    
    public void addRevolution(){
        revolution++;
    }
    
    public int getRevolution(){
        return revolution;
    }
    
    public void buy(){
        gold -= currentTile.getValue();
        addProperty();
    }
    
    public void payPenalty(){
        gold -= currentTile.getValue();
    }
    
    public void payRent(){
        gold -= currentTile.getRent();
    }
    
    public int getCurrentTileRent(){
        return currentTile.getRent();
    }
    
    public void sell(){
        gold += currentTile.getValue();
        removeProperty();
    }
    
    private void addProperty(){
        properties.add(currentTile);
    }
    
    private void removeProperty(){
        properties.remove(currentTile);
    }
    
    public ArrayList<Tile> getProperties(){
        return properties;
    }
    
    public void setGold(int g){
        gold = g;
    }
    
    public void addGold(int g){
        gold += g;
    }
    
    public void removeGold(int g){
        gold -= g;
        if(gold <= 0){
            bankrupt = true;
        }
    }
    
    public boolean isBankrupt(){
        return bankrupt;
    }
    
    public int getGold(){
        return gold;
    }
    
    public Tile getCurrentTile(){
        return currentTile;
    }
    
    public ImageView token(){
        return token;
    }
    
    public void setBoard(Board b){
        board = b;
        token.fitWidthProperty().bind(board.display().fitWidthProperty().multiply(0.05).multiply(board.display().scaleXProperty()));
        token.fitHeightProperty().bind(board.display().fitHeightProperty().multiply(0.05).multiply(board.display().scaleYProperty()));
        token.xProperty().bind(board.display().fitWidthProperty().multiply(0.925));
        token.yProperty().bind(board.display().fitHeightProperty().multiply(0.925));
    }
}
