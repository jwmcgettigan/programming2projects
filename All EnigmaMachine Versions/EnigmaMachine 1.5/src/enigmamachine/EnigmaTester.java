/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmamachine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @version 1.5
 * @author Justin McGettigan
 */
public class EnigmaTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EnigmaFXML.fxml"));

        Scene scene = new Scene(root);
        
        stage.setResizable(false);
        stage.setTitle("Enigma Machine");
        stage.setScene(scene);
        stage.show();
    }
}
