/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @version 1.4
 * @author Justin McGettigan
 */
public class EnigmaFXMLController {
    
    @FXML
    private URL location;
    
    @FXML
    private ResourceBundle resources;
    
    @FXML
    private Button button;

    @FXML
    private TextArea textArea;
    
    @FXML
    private Text text;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @FXML
    public void initialize() {
        
    }
    
    
    /*
    public String getTextArea(){
        return textArea.getText();
    }*/
}
