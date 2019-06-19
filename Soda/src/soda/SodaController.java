/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soda;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Justin
 */
public class SodaController implements Initializable {
    
    private int win = 0;
    
    @FXML
    private Button coke, pepsi;
    
    @FXML
    private Pane sodapane;
    
    @FXML
    private void drinkCoke(ActionEvent event) {
        System.out.println("Coke Wins");
        win = 1;
        launchWinner();
    }
    
    @FXML
    private void toosweet(ActionEvent event) {
        System.out.println("Pepsi Wins");
        win = 2;
        launchWinner();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void launchWinner(){
        System.out.println("winner is " + win);
        
        Parent root = null;
        FXMLLoader root1 = null;
        root1 = new FXMLLoader(getClass().getResource("Winner.fxml"));
        try{
            root = (Parent)root1.load();
        }catch(IOException io){}
        WinnerController controller = root1.<WinnerController>getController();
        System.out.println("test2");
        controller.setWinner(win);
        System.out.println("test3");
        Scene sc = new Scene(root);
        Stage st = new Stage();
        st.setScene(sc);
        st.show();
        sodapane.getScene().getWindow().hide();
    }
}
