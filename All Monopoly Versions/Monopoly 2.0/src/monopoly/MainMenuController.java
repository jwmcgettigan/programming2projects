/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @version 2.0
 * @author Justin McGettigan
 */
public class MainMenuController implements Initializable {

    private GameController controller;
    private Image[] sprites = new Image[4];
    private ArrayList<Player> players;
    private ArrayList<Integer> usedTokens;
    private int pos, player;

    @FXML
    private ImageView token, token1, token2, token3, token4, background;
    private ImageView[] tokens = new ImageView[4];// = {token1, token2, token3, token4};

    @FXML
    private Pane rootPane;
    
    @FXML
    private HBox mainHBox;

    @FXML
    private Label enterText, spaceText, playerChooseText, leftArrow, rightArrow;

    @FXML
    private void start() throws IOException {
        Stage stage = (Stage) spaceText.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameFXML.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        controller.setPlayers(players);
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screen.getHeight() * 0.95, screen.getHeight() * 0.95 - 25);
        scene.getRoot().requestFocus();
        stage.setTitle("Game!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        //implement ability to change token after initial selection
        enterText.setText("Hit ENTER to select a token.");
        if (event.getCode() == KeyCode.SPACE) {
            System.out.println("Test1 pass");
            if (players.size() < 2) {
                enterText.setText("You need at least 2 players to start the game.");
            } else {
                System.out.println("Test2 pass");
                try {
                    start();
                } catch (IOException ex) {System.out.println("IO exception");
                }
            }
        }

        if (event.getCode() == KeyCode.DIGIT1) {
            playerChooseText.setText("Choosing Player 1");
            player = 1;
        } else if (event.getCode() == KeyCode.DIGIT2) {
            playerChooseText.setText("Choosing Player 2");
            player = 2;
        } else if (event.getCode() == KeyCode.DIGIT3) {
            playerChooseText.setText("Choosing Player 3");
            player = 3;
        } else if (event.getCode() == KeyCode.DIGIT4) {
            playerChooseText.setText("Choosing Player 4");
            player = 4;
        }

        //IMPLEMENT RESPONSIVE ARROWS (change text arrows to bold when keys are pressed)
        if (event.getCode() == KeyCode.LEFT && pos > 0) {
            leftArrow.styleProperty().set("-fx-font-weight:bold; -fx-font-size: 30;");
            token.setImage(sprites[--pos]);
            if (usedTokens.contains(pos)) {
                token.opacityProperty().set(0.5);
            } else {
                token.opacityProperty().set(1);
            }
        }
        if (event.getCode() == KeyCode.RIGHT && pos < sprites.length - 1) {
            rightArrow.styleProperty().set("-fx-font-weight:bold; -fx-font-size: 30;");
            token.setImage(sprites[++pos]);
            if (usedTokens.contains(pos)) {
                token.opacityProperty().set(0.5);
            } else {
                token.opacityProperty().set(1);
            }
        }
        if (event.getCode() == KeyCode.ENTER) {
            if (usedTokens.contains(pos)) {
                enterText.setText("Token already chosen!");
            } else {
                token.opacityProperty().set(0.5);
                players.add(player - 1, new Player(sprites[pos], player));

                tokens[player - 1].setImage(sprites[pos]);
                usedTokens.add(pos);
                enterText.setText("Token Selected!");
                if(player == 4){
                    playerChooseText.setText("Player Selection Complete.");
                } else{
                    playerChooseText.setText("Choosing Player " + (++player));
                }
            }
        }
    }

    @FXML
    private void onKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            leftArrow.styleProperty().set("");
        }
        if (event.getCode() == KeyCode.RIGHT) {
            rightArrow.styleProperty().set("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        players = new ArrayList<>();
        usedTokens = new ArrayList<>();
        mainHBox.layoutXProperty().bind(rootPane.widthProperty().multiply(0.5).subtract(mainHBox.widthProperty().multiply(0.5)));
        mainHBox.layoutYProperty().bind(rootPane.heightProperty().multiply(0.5).subtract(mainHBox.heightProperty().multiply(0.5)));
        //background = new ImageView(new Image("./resources/menuBackground.gif", 300, 300, false, false));
        //background.toBack();

        //rootPane.getChildren().add(background);
        //background.fitWidthProperty().bind(rootPane.widthProperty());
        //background.fitHeightProperty().bind(rootPane.heightProperty());
        tokens[0] = token1;
        tokens[1] = token2;
        tokens[2] = token3;
        tokens[3] = token4;
        //initialize things for the game in the background
        initializeSprites();
    }

    private void initializeSprites() {
        pos = 0;
        player = 1;
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Image("./resources/tokens/token" + (i + 1) + ".png");
        }
        token.setImage(sprites[pos]);
    }
}
