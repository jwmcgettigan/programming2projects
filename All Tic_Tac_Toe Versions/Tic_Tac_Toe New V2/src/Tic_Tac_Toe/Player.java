/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;


/**
 * @project Tic Tac Toe New V1
 * @author Justin McGettigan
 */
public class Player {
    private Human human;
    private Computer bot;
    private Board board;
    private final int botGames = 1000; //This variable is the set number of games for which the computer will face itself.
    private int bot1Wins, bot2Wins, botDraws;

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
    public boolean decide(int orderNum, int gameNum) {
        int player1, player2 = 0, playerDecision;
        //System.out.println(board.getOccupiedTiles());
        if (orderNum == 1) {
            if (gameNum <= botGames) {
                bot.decide(board.getOccupiedTiles(), board.getVacantTiles(), gameNum);
                player1 = bot.getDecision();
            } else {
                if (gameNum == botGames + 1 && board.getOccupiedTiles().length() < 1) {
                    //System.out.println("Brain: " + (bot.getBrain().size() - 1));
                    System.out.printf("__________________________________%n");
                    //System.out.printf("| Bot 1 Wins: " + bot1Wins + " | Bot 2 Wins: " + bot2Wins + " | Bot Draws: " + botDraws + " |");
                    System.out.printf("| Bot 1: " + bot1Wins + " | Bot 2: " + bot2Wins + " | Draws: " + botDraws + " |");
                    System.out.printf("%n¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯%n");
                    System.out.printf("Total Games: " + (gameNum-1) + ", Brain Size: " + bot.getBrain().size() + "%n");
                    //System.out.printf("%n" + bot.getBrain() + "%n");
                    //System.out.printf("%n" + Collections.frequency(bot.getBrain(), bot.getBrain().get(0)));
                    human.name(); //need to only run once
                    board.print();
                }
                //look at Tic Tac Toe 5.0 for reference here
                human.decide(board.getOccupiedTiles());
                player1 = human.getDecision();
            }
            playerDecision = player1;
            //System.out.println("Player1: " + playerDecision);
        } else{
            if (board.getOccupiedTiles().length() < 8) {
                bot.decide(board.getOccupiedTiles(), board.getVacantTiles(), gameNum);
                player2 = bot.getDecision();
            }
            playerDecision = player2;
            //System.out.println("Player2: " + playerDecision);
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
                if (decision == board.getIntBoard()[r][c]) {
                    if (orderNum == 1) {
                        board.getCharBoard()[r][c] = 'X';
                    } else {
                        board.getCharBoard()[r][c] = 'O';
                    }
                    if (gameNum > botGames) {
                        if (orderNum == 2) {
                            System.out.printf("%nComputer chooses position " + decision + "%n");
                        }
                        board.print();
                    }
                    //board.print();
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
        boolean[] X = new boolean[10], O = new boolean[10], S = new boolean[10];
        board.positions(X, O, S);
        boolean player1Wins, player2Wins;
        player1Wins = X[1] && X[2] && X[3] || X[4] && X[5] && X[6] || X[7] && X[8] && X[9] || X[1] && X[4] && X[7] || X[2] && X[5] && X[8] || X[3] && X[6] && X[9] || X[1] && X[5] && X[9] || X[7] && X[5] && X[3];
        player2Wins = O[1] && O[2] && O[3] || O[4] && O[5] && O[6] || O[7] && O[8] && O[9] || O[1] && O[4] && O[7] || O[2] && O[5] && O[8] || O[3] && O[6] && O[9] || O[1] && O[5] && O[9] || O[7] && O[5] && O[3];
        
        //int bot1Wins, bot2Wins, botDraws;
        String endGameState = "";
        if (gameNum <= botGames) {
            if (player1Wins) {
                for (int i = 0; i < board.getOccupiedTiles().length(); i++) {
                    endGameState += board.getOccupiedTiles().charAt(i);
                }
                bot.getBrain().add(endGameState);
                //System.out.println("Bot 1 Wins!");
                bot1Wins++;
                return true;
            } else if (player2Wins) {
                for (int i = 0; i < board.getOccupiedTiles().length(); i++) {
                    endGameState += board.getOccupiedTiles().charAt(i);
                }
                bot.getBrain().add(endGameState);
                //System.out.println("Bot 2 Wins!");
                bot2Wins++;
                return true;
            } else if (board.getOccupiedTiles().length() == 9) {
                //System.out.println("It's a Draw!");
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
            //System.out.println("Brain: "+bot.getBrain());
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
