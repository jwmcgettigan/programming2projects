package soda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Justin
 */
public class WinnerController implements Initializable {

    @FXML
    private ImageView winner;
    
    private Image coke, pepsi;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coke = new Image("coke.jpg");
        pepsi = new Image("pepsi.jpg");
    }    
    
    public void setWinner(int win){
        System.out.println("test");
        if(win==1){
            winner.setImage(coke);
        } else{
            winner.setImage(pepsi);
        }
    }
}
