/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 * @version 2.0
 * @author Justin McGettigan
 */
public class GameController implements Initializable {

    //IMPLEMENT POPUP SQUARE AND PLAYER IDENTIFICATION
    //dice glow when hovered over
    private Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    private ArrayList<Player> players;
    private Board board;
    private Tile[] tiles;
    private SecureRandom rand;
    private int playerTurn, turn, stayInJail;
    private Image[] dice = new Image[6];
    private ImageView die1Display, die2Display;
    private boolean owned, paid, gameover;

    @FXML
    private BorderPane rootPane, bankPane;

    @FXML
    private Pane boardPane;

    @FXML
    private ImageView boardDisplay, p1Popup, p2Popup, p3Popup, p4Popup;
    private ImageView[] popups = new ImageView[4];

    @FXML
    private Button buyButton, payButton, sellButton;

    @FXML
    private TabPane tabPane, playersTabPane;

    @FXML
    private Label wealthText, cardText, propertiesText;

    @FXML
    private void rollDice() {
        if (paid) {
            if (playerTurn == players.size()) {
                playerTurn = 0;
                turn++;
            }
            if (!players.get(playerTurn).isBankrupt()) {
                int diceTotal;
                owned = false;
                paid = false;
                wealthText.setVisible(true);
                buyButton.setVisible(true);
                payButton.setVisible(true);
                sellButton.setVisible(true);
                buyButton.setDisable(false);
                payButton.setDisable(false);
                sellButton.setDisable(false);
                cardText.setText("");
                for (int i = 0; i < players.size(); i++) {
                    playersTabPane.getTabs().get(i).styleProperty().set("");
                }

                diceTotal = diceAnimation();

                board.setTileCoords();

                tabPane.getSelectionModel().select(1);
                playersTabPane.getSelectionModel().select(playerTurn);
                playersTabPane.getTabs().get(playerTurn).styleProperty().set("-fx-background-color: palevioletred;");
                for (int k = 0; k < players.get(playerTurn).getProperties().size(); k++) {
                    players.get(playerTurn).getProperties().get(k);
                }
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).getProperties().contains(players.get(playerTurn).getCurrentTile())) {
                        owned = true;
                        buyButton.setDisable(true);
                        payButton.setDisable(false);
                    } else {
                        payButton.setDisable(true);
                        paid = true;
                    }
                }
                if (players.get(playerTurn).getProperties().contains(players.get(playerTurn).getCurrentTile())) {
                    payButton.setDisable(true);
                    paid = true;
                } else {
                    sellButton.setDisable(true);
                }

                if (turn == 0) {
                    players.get(playerTurn).move(tiles[0], tiles[diceTotal]);
                    popups[playerTurn].setImage(tiles[diceTotal].sprite());
                } else {
                    int nextLocation;
                    if ((players.get(playerTurn).getCurrentTile().getID() + diceTotal) > (39 * players.get(playerTurn).getRevolution())) {
                        stayInJail = 3;
                        players.get(playerTurn).addGold(200);
                        nextLocation = (players.get(playerTurn).getCurrentTile().getID() + diceTotal) - (39 * players.get(playerTurn).getRevolution());
                        players.get(playerTurn).addRevolution();
                    } else if ((players.get(playerTurn).getCurrentTile().getID() + diceTotal) == 30 && stayInJail > 0) { //go to jail
                        nextLocation = 10;
                        stayInJail--;
                    } else {
                        stayInJail = 3;
                        nextLocation = players.get(playerTurn).getCurrentTile().getID() + diceTotal;
                    }
                    players.get(playerTurn).move(players.get(playerTurn).getCurrentTile(), tiles[nextLocation]);
                    popups[playerTurn].setImage(tiles[players.get(playerTurn).getCurrentTile().getID()].sprite());
                }
                if (players.get(playerTurn).getCurrentTile().getType() == 3) {
                    buyButton.setDisable(true);
                    payButton.setDisable(true);
                    paid = true;
                } else if (players.get(playerTurn).getCurrentTile().getType() == 2) {
                    buyButton.setDisable(true);
                    payButton.setDisable(false);
                }
                if (players.get(playerTurn).getCurrentTile().getSpecialType() == 1) {
                    exploreDungeon();
                } else if (players.get(playerTurn).getCurrentTile().getSpecialType() == 2) {
                    chance();
                }
                wealthText.setText("Player Wealth: " + players.get(playerTurn).getGold() + " Gold");
                properties(playerTurn);
            }
            int temp = 0;
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).isBankrupt()) {
                    temp++;
                }
                if (temp == 3) {
                    gameover = true;
                    wealthText.setText("GAME OVER!");
                    cardText.setText("GAME OVER!");
                    propertiesText.setText("GAME OVER!");
                    //gameover screen?
                }
            }
            playerTurn++;
        }

        System.out.println("turn: " + turn);
        System.out.println("playerturn: " + playerTurn);
    }

    private void properties(int k) {
        String temp = "Owned Properties:\n";
        for (int i = 0; i < players.get(k).getProperties().size(); i++) {
            temp += players.get(k).getProperties().get(i).getName() + "\n";
        }
        propertiesText.setText(temp);
    }

    private void exploreDungeon() {
        int randCard = rand.nextInt(5);
        if (randCard == 0) {
            cardText.setText("You successfully loot a dungeon.\nYou gain 100 gold!");
            players.get(playerTurn).addGold(100);
        } else if (randCard == 1) {
            cardText.setText("You successfully loot a dungeon.\nYou gain 100 gold!");
            players.get(playerTurn).addGold(100);
        } else if (randCard == 2) {
            cardText.setText("You successfully loot a dungeon.\nYou gain 100 gold!");
            players.get(playerTurn).addGold(100);
        } else if (randCard == 3) {
            cardText.setText("You successfully loot a dungeon.\nYou gain 100 gold!");
            players.get(playerTurn).addGold(100);
        } else if (randCard == 4) {
            cardText.setText("You successfully loot a dungeon.\nYou gain 100 gold!");
            players.get(playerTurn).addGold(100);
        } else if (randCard == 5) {
            cardText.setText("You successfully loot a dungeon.\nYou gain 100 gold!");
            players.get(playerTurn).addGold(100);
        }
    }

    private void chance() {
        int randCard = rand.nextInt(5);
        if (randCard == 0) {
            cardText.setText("You're party is eaten by a slime.\nYou loot them and gain 50 gold!");
            players.get(playerTurn).addGold(50);
        } else if (randCard == 1) {
            cardText.setText("You're party is eaten by a slime.\nYou loot them and gain 50 gold!");
            players.get(playerTurn).addGold(50);
        } else if (randCard == 2) {
            cardText.setText("You're party is eaten by a slime.\nYou loot them and gain 50 gold!");
            players.get(playerTurn).addGold(50);
        } else if (randCard == 3) {
            cardText.setText("You're party is eaten by a slime.\nYou loot them and gain 50 gold!");
            players.get(playerTurn).addGold(50);
        } else if (randCard == 4) {
            cardText.setText("You're party is eaten by a slime.\nYou loot them and gain 50 gold!");
            players.get(playerTurn).addGold(50);
        } else if (randCard == 5) {
            cardText.setText("You're party is eaten by a slime.\nYou loot them and gain 50 gold!");
            players.get(playerTurn).addGold(50);
        }
    }

    private int diceAnimation() {
        int die1 = rand.nextInt(5), die2 = rand.nextInt(5);
        Timeline roll = new Timeline(
                new KeyFrame(Duration.seconds(0.2), e -> {
                    die1Display.setImage(dice[rand.nextInt(5)]);
                    die2Display.setImage(dice[rand.nextInt(5)]);
                }),
                new KeyFrame(Duration.seconds(0.3), e -> {
                    die1Display.setImage(dice[rand.nextInt(5)]);
                    die2Display.setImage(dice[rand.nextInt(5)]);
                })
        );
        roll.setCycleCount(5);
        roll.play();
        die1Display.setImage(dice[die1]);
        die2Display.setImage(dice[die2]);
        return die1 + die2 + 2;
    }

    @FXML
    private void move(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            rollDice();
        }
    }

    @FXML
    private void buy() {
        if (!owned) {
            players.get(playerTurn - 1).buy();
            wealthText.setText("Player Wealth: " + players.get(playerTurn - 1).getGold() + " Gold");
            properties(playerTurn - 1);
            buyButton.setDisable(true);
        }
    }

    @FXML
    private void pay() {
        if (players.get(playerTurn - 1).getCurrentTile().getType() == 1) {
            players.get(playerTurn - 1).payRent();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i) != players.get(playerTurn - 1) && players.get(i).getProperties().contains(players.get(playerTurn - 1).getCurrentTile())) {
                    players.get(i).addGold(players.get(playerTurn - 1).getCurrentTileRent());
                }
            }
        } else if (players.get(playerTurn - 1).getCurrentTile().getType() == 2) {
            players.get(playerTurn - 1).payPenalty();
        }
        wealthText.setText("Player Wealth: " + players.get(playerTurn - 1).getGold() + " Gold");
        paid = true;
        payButton.setDisable(true);
    }

    @FXML
    private void sell() {
        if (players.get(playerTurn - 1).getProperties().contains(players.get(playerTurn - 1).getCurrentTile())) {
            players.get(playerTurn - 1).sell();
            wealthText.setText("Player Wealth: " + players.get(playerTurn - 1).getGold() + " Gold");
            properties(playerTurn - 1);
            sellButton.setDisable(true);
        }
    }

    @FXML
    private void onMouseReleased() {
        System.out.println("Mouse Released!");
        if (tabPane.getSelectionModel().getSelectedIndex() != 1) {
            buyButton.setVisible(false);
            payButton.setVisible(false);
            sellButton.setVisible(false);
            wealthText.setVisible(false);
        } else {
            buyButton.setVisible(true);
            payButton.setVisible(true);
            sellButton.setVisible(true);
            wealthText.setVisible(true);
        }
    }

    @FXML
    private void onMousePressed() {
        System.out.println("Mouse Pressed!");
        if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
            wealthText.setText("Player Wealth: " + players.get(0).getGold() + " Gold");
        } else if (tabPane.getSelectionModel().getSelectedIndex() == 1) {
            wealthText.setText("Player Wealth: " + players.get(1).getGold() + " Gold");
        } else if (tabPane.getSelectionModel().getSelectedIndex() == 2) {
            wealthText.setText("Player Wealth: " + players.get(2).getGold() + " Gold");
        } else if (tabPane.getSelectionModel().getSelectedIndex() == 3) {
            wealthText.setText("Player Wealth: " + players.get(3).getGold() + " Gold");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = new Board(boardPane, boardDisplay);
        tiles = board.getTiles();
        rand = new SecureRandom();
        playerTurn = 0;
        turn = 0;
        stayInJail = 3;
        paid = true;
        owned = false;
        gameover = false;
        initializePopups();
        initializeDice();
        tabSettings();
        buttonSettings();
        textSettings();
        //board.display().toBack();
    }

    private void initializePopups() {
        popups[0] = p1Popup;
        popups[1] = p2Popup;
        popups[2] = p3Popup;
        popups[3] = p4Popup;
        for (int i = 0; i < popups.length; i++) {
            popups[i].fitWidthProperty().bind(board.display().fitWidthProperty().multiply(0.3));
            popups[i].fitHeightProperty().bind(board.display().fitHeightProperty().multiply(0.45));
        }
    }

    private void initializeDice() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Image("./resources/dice/die" + (i + 1) + ".png");
        }
        die1Display = new ImageView(dice[0]);
        die2Display = new ImageView(dice[1]);
        boardPane.getChildren().addAll(die1Display, die2Display);
        die1Display.fitWidthProperty().bind(board.display().fitWidthProperty().multiply(0.05));
        die1Display.fitHeightProperty().bind(board.display().fitWidthProperty().multiply(0.05));
        die2Display.fitWidthProperty().bind(board.display().fitWidthProperty().multiply(0.05));
        die2Display.fitHeightProperty().bind(board.display().fitWidthProperty().multiply(0.05));
        die1Display.xProperty().bind(board.display().fitWidthProperty().multiply(0.14));
        die1Display.yProperty().bind(board.display().fitHeightProperty().multiply(0.77));
        die2Display.xProperty().bind(board.display().fitWidthProperty().multiply(0.2));
        die2Display.yProperty().bind(board.display().fitHeightProperty().multiply(0.77));
    }

    private void buttonSettings() {
        buyButton.prefWidthProperty().bind(board.display().fitWidthProperty().multiply(0.075));
        buyButton.prefHeightProperty().bind(board.display().fitHeightProperty().multiply(0.05));
        payButton.prefWidthProperty().bind(buyButton.prefWidthProperty());
        payButton.prefHeightProperty().bind(buyButton.prefHeightProperty());
        sellButton.prefWidthProperty().bind(buyButton.prefWidthProperty());
        sellButton.prefHeightProperty().bind(buyButton.prefHeightProperty());

        buyButton.layoutXProperty().bind(board.display().fitWidthProperty().multiply(0.15));
        buyButton.layoutYProperty().bind(board.display().fitHeightProperty().multiply(0.6));
        payButton.layoutXProperty().bind(board.display().fitWidthProperty().multiply(0.25));
        payButton.layoutYProperty().bind(buyButton.layoutYProperty());
        sellButton.layoutXProperty().bind(board.display().fitWidthProperty().multiply(0.35));
        sellButton.layoutYProperty().bind(buyButton.layoutYProperty());
        buyButton.setVisible(false);
        payButton.setVisible(false);
        sellButton.setVisible(false);
    }

    private void tabSettings() {
        playersTabPane.tabMinWidthProperty().bind(board.display().fitWidthProperty().multiply(0.05));
        playersTabPane.tabMinHeightProperty().bind(board.display().fitHeightProperty().multiply(0.10));
        playersTabPane.tabMaxWidthProperty().bind(board.display().fitWidthProperty().multiply(0.10));
        playersTabPane.tabMaxHeightProperty().bind(board.display().fitHeightProperty().multiply(0.10));
        tabPane.prefWidthProperty().bind(board.display().fitWidthProperty().multiply(0.73));
        tabPane.prefHeightProperty().bind(board.display().fitHeightProperty().multiply(0.734));
        tabPane.layoutXProperty().bind(board.display().fitWidthProperty().divide(2).subtract(tabPane.widthProperty().divide(2)));
        tabPane.layoutYProperty().bind(board.display().fitHeightProperty().divide(2).subtract(tabPane.heightProperty().divide(2)));
    }

    private void textSettings() {
        //wealthText.prefWidthProperty().bind(board.display().fitWidthProperty().multiply(0.2));
        //wealthText.prefHeightProperty().bind(board.display().fitHeightProperty().multiply(0.075));
        wealthText.layoutXProperty().bind(board.display().fitWidthProperty().multiply(0.15));
        wealthText.layoutYProperty().bind(board.display().fitHeightProperty().multiply(0.65));
        wealthText.setText("");
        //wealthText.setVisible(false);

        //cardText.prefHeightProperty().bind(board.display().fitHeightProperty().multiply(0.075));
        cardText.layoutXProperty().bind(board.display().fitWidthProperty().multiply(0.3));
        cardText.layoutYProperty().bind(board.display().fitHeightProperty().multiply(0.7));
        cardText.setText("");

        propertiesText.layoutXProperty().bind(board.display().fitWidthProperty().multiply(0.5));
        propertiesText.layoutYProperty().bind(board.display().fitHeightProperty().multiply(0.15));
        propertiesText.setText("");
    }

    public void setPlayers(ArrayList<Player> p) {
        players = p;
        for (Player player : players) {
            player.setBoard(board);
            boardPane.getChildren().add(player.token());
        }
        //ImageView test = new ImageView(players.get(i).token().getImage());
        for (int i = 0; i < players.size(); i++) {
            //popups[i].setImage(tiles[0].sprite());
            ImageView temp = new ImageView(players.get(i).token().getImage());
            temp.fitWidthProperty().bind(playersTabPane.tabMaxWidthProperty());
            temp.fitHeightProperty().bind(playersTabPane.tabMaxHeightProperty());
            playersTabPane.getTabs().get(i).setGraphic(temp);

        }
    }

    public Board getBoard() {
        return board;
    }
}
