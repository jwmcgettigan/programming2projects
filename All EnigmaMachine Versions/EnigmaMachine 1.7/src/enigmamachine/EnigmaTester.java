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
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @version 1.7
 * @author Justin McGettigan
 */
public class EnigmaTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EnigmaFXML.fxml"));
        
        stage.setMinWidth(1066);
        stage.setMinHeight(735);
        stage.getIcons().add(new Image("file:enigmaMachine.JPG"));
        stage.setTitle("Enigma Machine");
        stage.setScene(new Scene(root));
        stage.show();
    }
}