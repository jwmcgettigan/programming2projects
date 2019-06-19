/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import static javafx.util.Duration.seconds;

/**
 *
 * @author Justin
 */
public class FXMLDocumentController implements Initializable {
    
    private RotateTransition rotateTransition;
    private TranslateTransition transTransition;
    
    @FXML
    private Label label;
    
    @FXML
    private Button bounce, stop, spin;
    
    @FXML
    private ImageView frank;
    
    @FXML
    void spin(){
        rotateTransition.setNode(frank);
        rotateTransition.setDuration(seconds(1));
        rotateTransition.setByAngle(200);
        
        rotateTransition.setAutoReverse(true);
        rotateTransition.setCycleCount(100);
        rotateTransition.play();
    }
    
    @FXML
    void bounce(){
        transTransition.setNode(frank);
        
        transTransition.setToY(-200);
        transTransition.setToX(200);
        
        transTransition.setAutoReverse(true);
        transTransition.setCycleCount(100);
        transTransition.play();
    }
    
    @FXML
    void stop(){
        rotateTransition.pause();
        transTransition.pause();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rotateTransition = new RotateTransition();
        transTransition = new TranslateTransition();
    }    
    
}
