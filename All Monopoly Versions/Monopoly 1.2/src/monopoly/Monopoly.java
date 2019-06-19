/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @version 1.2
 * @author Justin McGettigan
 */
public class Monopoly extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController();
        
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        
        Parent menuRoot = FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
        Scene mainMenu = new Scene(menuRoot, screen.getHeight()*0.95-25, screen.getHeight()*0.95);
        
        
        Scene scene = new Scene(root, screen.getHeight()*0.95-25, screen.getHeight()*0.95);
        //System.out.println("Full-screen property: " + stage.fullScreenProperty());
        stage.setFullScreenExitHint("Press \"F\" to exit full-screen mode.");
        //stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("F"));
        //stage.setFullScreen(true);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.F)){
                    if(stage.isFullScreen()){
                        stage.setFullScreen(false);
                    } else{
                        stage.setFullScreen(true);
                    }
                }
            }
        });
        
        
        
        //stage.setScene(scene);
        //stage.show();
        //stage.setScene(mainMenu);
        //stage.show();
        
        stage.setScene(scene);
        stage.titleProperty().set("Dungeonopoly - A work in progress");
        stage.setResizable(false);
        controller.setStage(stage);
        //System.out.println("Title: " + controller.getStage().titleProperty().get());
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
