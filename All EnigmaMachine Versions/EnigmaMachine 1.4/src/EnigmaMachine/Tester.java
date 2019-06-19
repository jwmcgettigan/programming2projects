/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @version 1.4
 * @author Justin McGettigan
 */
public class Tester extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EnigmaFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Enigma Machine");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
        
        int[] choices = {5 - 1, 2 - 1, 3 - 1};//inner wheel, middle wheel, outer wheel DAY 16
        int[] startSettings = {8 - 1, 16 - 1, 13 - 1};//inner start, middle start, outer start DAY 16
        String plugboardSetting = "HM JO DI NR BY XZ GS PU FQ CT"; //DAY 16
        String reflectorSetting = "AI BT MV HU FW EL DG KN YZ OQ CP SX J. R "; //DAY 16
        String message = "I went 1 on a 6 journey to a 8 the 5 past...";
               message = "LZikxoQXMANvdB .JDPV.yljoieNatFoEBZRM.JyjpXOFHCLciaaREE";
        boolean isEncrypting = false;
        
        //EnigmaFXMLController controller = new EnigmaFXMLController();
        //System.out.println(controller.getTextArea());
        /*
        EnigmaMachine eMachine = new EnigmaMachine(choices, startSettings, plugboardSetting, reflectorSetting, message, isEncrypting);
        eMachine.run();*/
    }
}
