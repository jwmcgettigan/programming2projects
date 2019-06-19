/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;


/**
 * @project Tic Tac Toe 2.4
 * @author Justin McGettigan
 */
public class Player {
    private Human human;
    private Computer bot;
    private Board board;
    private final int botGames = 0; //This variable is the set number of games for which the computer will face itself.
    private int bot1Wins, bot2Wins, botDraws; //These variables, respectively, are: the number of times bot 1 won, the number of times bot 2 won, and the number of times neither bot won.

    public Player() {
        human = new Human();
        bot = new Computer();
        board = new Board();
        board.generate();
    }

    /**
     * This method calls all of the necessary methods for having a player take their turn.
     * @param orderNum This parameter determines player 1 and player 2.
     * @param gameNum This parameter is the current number of games that have
     * been played in a single session.
     * @return A boolean value based upon whether the current game is over.
     */
    public boolean decide(int orderNum, int gameNum, int difficulty) {
        
        int player1, player2 = 0, playerDecision;
        if (orderNum == 1) {
            if (gameNum <= botGames) {
                bot.decide(board.getOccupiedTiles(), board.getVacantTiles(), gameNum, board.getValues(), board.getCoords(), orderNum, difficulty);
                player1 = bot.getDecision();
            } else {
                if (gameNum == botGames + 1 && board.getOccupiedTiles().length() < 1) {
                    /*
                    System.out.printf("__________________________________%n");
                    System.out.printf("| Bot 1: " + bot1Wins + " | Bot 2: " + bot2Wins + " | Draws: " + botDraws + " |");
                    System.out.printf("%n¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯%n");
                    System.out.printf("Total Games: " + (gameNum-1) + ", Brain Size: " + bot.getBrain().size() + "%n");*/
                    human.name(); //need to only run once
                    board.print();
                }
                human.decide(board.getOccupiedTiles());
                player1 = human.getDecision();
            }
            playerDecision = player1;
        } else{
            if (board.getOccupiedTiles().length() < 8) {
                bot.decide(board.getOccupiedTiles(), board.getVacantTiles(), gameNum, board.getValues(), board.getCoords(), orderNum, difficulty);
                player2 = bot.getDecision();
            }
            playerDecision = player2;
        }
        rememberDecision(orderNum, playerDecision);
        return enactDecision(orderNum, gameNum, playerDecision);
    }
    
    /**
     * This method adds every player decision into this game's memory.
     * @param orderNum This parameter determines player 1 and player 2.
     * @param decision This parameter is the player's move of choice.
     */
    public void rememberDecision(int orderNum, int decision){
        String occupiedTiles = board.getOccupiedTiles();
        String vacantTiles = board.getVacantTiles();
        if(orderNum == 1){
            occupiedTiles += String.valueOf(decision);
            vacantTiles = vacantTiles.replace(String.valueOf(decision), "");
        } else if(orderNum == 2 && occupiedTiles.length() < 9){
            occupiedTiles += String.valueOf(decision);
            vacantTiles = vacantTiles.replace(String.valueOf(decision), "");
        }
        board.setOccupiedTiles(occupiedTiles);
        board.setVacantTiles(vacantTiles);
    }
    
    /**
     * This method is the act of the player placing their mark on the board.
     * @param orderNum This parameter determines player 1 and player 2.
     * @param gameNum This parameter is the current number of games that have
     * been played in a single session.
     * @param decision This parameter is the player's move of choice.
     * @return A boolean value based upon whether the current game is over.
     */
    public boolean enactDecision(int orderNum, int gameNum, int decision){
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                if (decision == board.getCoords()[r][c]) {
                    if (orderNum == 1) {
                        board.getValues()[r][c] = 1;  //'X'
                    } else {
                        board.getValues()[r][c] = 5; //'O'
                    }
                    if (gameNum > botGames) {
                        if (orderNum == 2) {
                            System.out.printf("%nComputer chooses position " + decision + "%n");
                        }
                        board.print();
                    }
                    return ending(gameNum);
                }
            }
        }
        return false;
    }
    
    /**
     * This method checks to see if a player has won the game or if the game is a draw.
     * @param gameNum This parameter is the current number of games that have
     * been played in a single session.
     * @return A boolean value based upon whether the current game is over.
     */
    public boolean ending(int gameNum){
        boolean player1Wins = false, player2Wins = false;
        int[][] values = board.getValues();
        int check1, check2, check3;
        /*
        [00][01][02] {[0i][0i][0i]} {[i0][i0][i0]} {[ii][ii][ii]}
        [10][11][12] {[1i][1i][1i]} {[i1][i1][i1]} {[ik][ik][ik]}
        [20][21][22] {[2i][2i][2i]} {[i2][i2][i2]}
        */
        for (int r = 0; r <= 2; r++) {
            check1 = 0; check2 = 0; check3 = 0;
            for (int c = 0, k = 2; c <= 2; c++, k--) {
                check1 += values[r][c];
                check2 += values[c][c];
                check3 += values[c][k];
            }
            if(check1 == 3 || check2 == 3 || check3 == 3){
                player1Wins = true;
            } else if(check1 == 15 || check2 == 15 || check3 == 15){
                player2Wins = true;
            }
        }
        String endGameState = "";
        if (gameNum <= botGames) {
            if (player1Wins) {/*
                for (int i = 0; i < board.getOccupiedTiles().length(); i++) {
                    endGameState += board.getOccupiedTiles().charAt(i);
                }
                bot.getBrain().add(endGameState);*/
                bot1Wins++;
                return true;
            } else if (player2Wins) {/*
                for (int i = 0; i < board.getOccupiedTiles().length(); i++) {
                    endGameState += board.getOccupiedTiles().charAt(i);
                }
                bot.getBrain().add(endGameState);*/
                bot2Wins++;
                return true;
            } else if (board.getOccupiedTiles().length() == 9) {
                botDraws++;
                return true;
            }
        } else {
            if (player1Wins) {
                System.out.printf("%nPlayer " + human.getName() + " has won!%n");
                return true;
            } else if (player2Wins) {
                System.out.printf("%nThe computer is the winner!%n");
                return true;
            } else if (board.getOccupiedTiles().length() == 9) {
                System.out.printf("%nIt's a draw!%n");
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method determines whether the game is the first game after the computer has finished facing itself.
     * @param gameNum This parameter is the current number of games that have
     * been played in a single session.
     * @return A boolean value based upon whether gameNum is equal to botGames + 1.
     */
    public boolean humanStart(int gameNum){
        if (gameNum <= botGames + 1) {
            board.reset();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * This method calls the restart() method from the Human class.
     * @return A boolean value based upon whether the human wants to start another game.
     */
    public boolean restart(){
        board.reset();
        return human.restart();
    }
    
    public int getBot1Wins(){
        return bot1Wins;
    }
    
    public int getBot2Wins(){
        return bot2Wins;
    }
    
    public int getBotDraws(){
        return botDraws;
    }
}
