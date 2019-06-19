/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
/**
 * @version 2.0
 * @author Justin McGettigan
 */
public class Board {
    
    
    private Pane boardPane;
    private ImageView display;
    private Tile[] tiles = new Tile[40];
    
    public Board(Pane bp, ImageView d){
        boardPane = bp;
        display = d;
        initialize();
        panning();
    }
    
    private void initialize(){
        //display = new ImageView(new Image("./resources/BoardAttempt5.jpg", 2930, 2930, false, false));
        display.fitHeightProperty().bind(boardPane.heightProperty());
        display.fitWidthProperty().bind(boardPane.widthProperty());
        //display.fitHeightProperty().bind(boardPane.heightProperty());
        //display.fitWidthProperty().bind(display.fitHeightProperty());
        display.xProperty().bind(boardPane.widthProperty().divide(2).subtract(display.fitWidthProperty().divide(2)));
    }
    
    public void setTileCoords(){
        double width = display.getFitWidth()*0.9, height = display.getFitHeight()*0.9;
        double corner = 0.135, normal = 0.09125;
        for(int i = 0; i < tiles.length; i++){
            tiles[i] = new Tile(new Image("./resources/tiles/tile" + i + ".jpg"), i);
            tiles[i].setValue(0);
            tiles[i].setType(1);
            tiles[i].setColor(0);
        }
        
        tiles[0].setType(3);
        tiles[10].setType(3);
        tiles[20].setType(3);
        tiles[30].setType(3);
        tiles[2].setType(3);
        tiles[7].setType(3);
        tiles[17].setType(3);
        tiles[22].setType(3);
        tiles[33].setType(3);
        tiles[36].setType(3);
        
        tiles[2].setSpecialType(1);
        tiles[7].setSpecialType(2);
        tiles[17].setSpecialType(1);
        tiles[22].setSpecialType(2);
        tiles[33].setSpecialType(1);
        tiles[36].setSpecialType(2);
        
        tiles[4].setType(2);
        tiles[38].setType(2);
        
        
        
        tiles[1].setColor(1);
        tiles[3].setColor(1);
        
        tiles[6].setColor(2);
        tiles[8].setColor(2);
        tiles[9].setColor(2);
        
        tiles[11].setColor(3);
        tiles[13].setColor(3);
        tiles[14].setColor(3);
        
        tiles[16].setColor(4);
        tiles[18].setColor(4);
        tiles[19].setColor(4);
        
        tiles[21].setColor(5);
        tiles[23].setColor(5);
        tiles[24].setColor(5);
        
        tiles[26].setColor(6);
        tiles[27].setColor(6);
        tiles[29].setColor(6);
        
        tiles[31].setColor(7);
        tiles[32].setColor(7);
        tiles[34].setColor(7);
        
        tiles[37].setColor(8);
        tiles[39].setColor(8);

        tiles[1].setValue(60);
        tiles[3].setValue(60);
        
        tiles[4].setValue(200);
        
        tiles[6].setValue(100);
        tiles[8].setValue(100);
        tiles[9].setValue(120);
        
        tiles[11].setValue(140);
        tiles[13].setValue(140);
        
        tiles[12].setValue(140);
        
        tiles[14].setValue(160);
        
        tiles[16].setValue(180);
        tiles[18].setValue(180);
        tiles[19].setValue(200);
        
        tiles[21].setValue(220);
        tiles[23].setValue(220);
        tiles[24].setValue(240);
        
        tiles[26].setValue(260);
        tiles[27].setValue(260);
        
        tiles[28].setValue(150);
        
        tiles[29].setValue(280);
        
        tiles[31].setValue(220);
        tiles[32].setValue(220);
        tiles[34].setValue(240);
        
        tiles[37].setValue(260);
        
        tiles[38].setValue(100);
        
        tiles[39].setValue(280);
        
        tiles[5].setValue(200);
        tiles[15].setValue(200);
        tiles[25].setValue(200);
        tiles[35].setValue(200);
        
        tiles[1].setName("Anauroch");
        tiles[3].setName("The High Ice");
        
        tiles[5].setName("Carriage Travel");
        tiles[6].setName("Baldur's Gate");
        tiles[8].setName("Amn");
        tiles[9].setName("Tethyr");
        
        tiles[11].setName("Evermeet");
        tiles[13].setName("Moonshae Isles");
        tiles[14].setName("Nelanthar Isles");
        
        tiles[15].setName("Teleport Circle Travel");
        tiles[16].setName("Llorkh");
        tiles[18].setName("Loudwater");
        tiles[19].setName("Secomber");
        
        tiles[21].setName("Triel");
        tiles[23].setName("Scornubel");
        tiles[24].setName("Elturel");
        
        tiles[25].setName("Airship Travel");
        tiles[26].setName("Silverymoon");
        tiles[27].setName("Everlund");
        tiles[29].setName("Sundabar");
        
        tiles[31].setName("Helm's Hold");
        tiles[32].setName("Thornhold");
        tiles[34].setName("Daggerford");
        tiles[35].setName("Flying Castle Travel");
        
        tiles[37].setName("Waterdeep");
        tiles[39].setName("Neverwinter");
        
        for(int i = 0; i < tiles.length; i++){
            if(i==0){
                tiles[0].setCoord(0, 0);
            } else if(i==1){
                tiles[i].setCoord((tiles[i-1].getX()-(width*corner))+0.025, 0);
            } else if(i < 10){
                tiles[i].setCoord((tiles[i-1].getX()-(width*normal))+0.025, 0);
            } else if(i==10){
                tiles[i].setCoord((tiles[i-1].getX()-(width*corner))+0.025, 0);
            } else if(i==11){
                tiles[i].setCoord(tiles[i-1].getX(), (tiles[i-1].getY()-(width*corner))+0.025);
            } else if(i < 20){
                tiles[i].setCoord(tiles[i-1].getX(), (tiles[i-1].getY()-(width*normal))+0.025);
            } else if(i==20){
                tiles[i].setCoord(tiles[i-1].getX(), (tiles[i-1].getY()-(width*corner))+0.025);
            } else if(i==21){
                tiles[i].setCoord((tiles[i-1].getX()+(width*corner))+0.025, tiles[i-1].getY());
            } else if(i < 30){
                tiles[i].setCoord((tiles[i-1].getX()+(width*normal))+0.025, tiles[i-1].getY());
            } else if(i==30){
                tiles[i].setCoord((tiles[i-1].getX()+(width*corner))+0.025, tiles[i-1].getY());
            } else if(i==31){
                tiles[i].setCoord(tiles[i-1].getX(), (tiles[i-1].getY()+(width*corner))+0.025);
            } else {
                tiles[i].setCoord(tiles[i-1].getX(), (tiles[i-1].getY()+(width*normal))+0.025);
            }
        }
        
    }
    
    private double pressedX, pressedY;
    
    private void panning() { //determine limits
        display.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pressedX = event.getX();
                pressedY = event.getY();
            }
        });
        display.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boardPane.setTranslateX(boardPane.getTranslateX() + ((event.getX() - pressedX) / 2));
                boardPane.setTranslateY(boardPane.getTranslateY() + ((event.getY() - pressedY) / 2));
                event.consume();
            }
        });
    }
    
    public Tile[] getTiles(){
        return tiles;
    }
    
    public ImageView display(){
        return display;
    }
}
