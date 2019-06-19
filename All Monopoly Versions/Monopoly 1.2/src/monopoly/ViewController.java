/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @version 1.2
 * @author Justin McGettigan
 */
public class ViewController {

    private double pressedX, pressedY;

    private BorderPane bPane;
    private Pane pane;
    private MenuBar menu;
    private GameController game;

    public ViewController(BorderPane bp, Pane p, MenuBar m, GameController g) {
        bPane = bp;
        pane = p;
        menu = m;
        game = g;

        pane.setLayoutY(25);
        zooming();
        panning();
    }

    
    
    public void zoom(Node node, double factor, double x, double y) {
        double oldScale = node.getScaleX();
        double scale = oldScale * factor;
        if (scale < 0.8) {
            scale = 0.8;
        }
        if (scale > 1.8) {
            scale = 1.8;
        }
        node.setScaleX(scale);
        node.setScaleY(scale);
        
        double f = (scale / oldScale) - 1;
        Bounds bounds = node.localToScene(node.getBoundsInLocal());
        double dx = (x - (bounds.getWidth() / 2 + bounds.getMinX()));
        double dy = (y - (bounds.getHeight() / 2 + bounds.getMinY()));
        node.setTranslateX(node.getTranslateX() - f * dx);
        node.setTranslateY(node.getTranslateY() - f * dy);
    }

    public void zoom(Node node, ScrollEvent event) {
        zoom(node, Math.pow(1.005, event.getDeltaY()), event.getSceneX(), event.getSceneY());
    }

    public void zoom(Node node, ZoomEvent event) {
        zoom(node, event.getZoomFactor(), event.getSceneX(), event.getSceneY());
    }
    
    private void zooming() { //make zoom smoother if possible
        
        //pane.setOnScroll(event -> zoom((Node)pane, event)); // mouse scroll wheel zoom
        //pane.setOnZoom(event -> zoom(pane, event)); //pinch to zoom
        
        pane.setOnScroll(event -> zoom(game.getBoard().display(), event)); // mouse scroll wheel zoom
        game.getBoard().display().setOnScroll(event -> zoom(game.getTokens()[2], event)); // mouse scroll wheel zoom
        //pane.setOnZoom(event -> zoom(game.getBoard().display(), event)); //pinch to zoom
        
    }

    private void panning() { //determine limits
        game.getBoard().display().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pressedX = event.getX();
                pressedY = event.getY();
            }
        });
        game.getBoard().display().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {/*
                System.out.println("TranslateX: " + pane.getTranslateX());
                System.out.println("TranslateY: " + pane.getTranslateY());
                System.out.println("eventX: " + event.getX());
                System.out.println("eventY: " + event.getY());
                System.out.println("pressedX: " + pressedX);
                System.out.println("pressedY: " + pressedY);/*
                if((pane.getTranslateX() > (game.getBoard().display().getFitWidth()/2) && (event.getX() - pressedX) == 1) || (pane.getTranslateX() < -(game.getBoard().display().getFitWidth()/2) && (event.getX() - pressedX) == -1)){
                    //pane.setTranslateX(pane.getTranslateX());
                }else{
                    pane.setTranslateX(pane.getTranslateX() + event.getX() - pressedX);
                }
                if((pane.getTranslateY() > (game.getBoard().display().getFitHeight()/2) && (event.getY() - pressedY) == 1) || (pane.getTranslateY() < -(game.getBoard().display().getFitHeight()/2) && (event.getY() - pressedY) == -1)){
                    //pane.setTranslateY(pane.getTranslateY());
                }else{
                    pane.setTranslateY(pane.getTranslateY() + event.getY() - pressedY);
                }*/
                pane.setTranslateX(pane.getTranslateX() + ((event.getX() - pressedX) / 2));
                pane.setTranslateY(pane.getTranslateY() + ((event.getY() - pressedY) / 2));
                event.consume();
            }
        });
    }
}
