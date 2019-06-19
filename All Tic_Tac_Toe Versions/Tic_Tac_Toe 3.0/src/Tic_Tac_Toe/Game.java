/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Justin
 */
public class Game {
    private Scanner input;
    private Board board;
    private Player player;
    private Computer bot;
    private int numGames = 1;
    private final int gamesForBrain = 10000;

    public Game() {
        input = new Scanner(System.in);
        board = new Board();
        player = new Player();
        bot = new Computer();
        board.generate();
    }

    public void play() {
        int first = 1, second;
        
        outer:
        while (board.getMemory().size() < 9) {
            System.out.println("round" + numGames);
            if (numGames <= gamesForBrain) {
                first = bot.choice(board.getMemory(), -999); //need to make sure that each call calls a different number
            }
            if (numGames > gamesForBrain) {
                if(numGames == gamesForBrain + 1){
                 player.name(); //need to only run once   
                }
                board.print(); //need to make sure this is reset
                player.choice(board.getMemory());
                first = player.getChoice();
            }

            second = bot.choice(board.getMemory(), first);

            board.boardMemory(first, second);
            for (int r = 0; r <= 2; r++) {
                for (int c = 0; c <= 2; c++) {
                    if (first == board.getIntBoard()[r][c]) {
                        board.getCharBoard()[r][c] = 'X';
                        if(numGames > gamesForBrain){
                            board.print();
                        }
                        if (endgame(numGames)) {
                            break outer;
                        }
                    }
                }
            }
            if (board.getMemory().size() <= 8) {
                for (int r = 0; r <= 2; r++) {
                    for (int c = 0; c <= 2; c++) {
                        if (second == board.getIntBoard()[r][c]) {
                            board.getCharBoard()[r][c] = 'O';
                            if(numGames > gamesForBrain){
                                System.out.printf("%nComputer chooses position " + second + "%n%n");
                                board.print();
                            }
                            if (endgame(numGames)) {
                                break outer;
                            }
                        }
                    }
                }
            } numGames++;
        }
        if (numGames <= gamesForBrain) {
            board.getMemory().clear();
            board.reset();
            play();
        }
        if(numGames == gamesForBrain + 1){
            board.getMemory().clear();
            board.reset();
            play();
        }
        if (numGames > gamesForBrain + 1) {
            restart();
        }
    }
    
    public boolean endgame(int numGames) {
        boolean[] X = new boolean[10], O = new boolean[10], S = new boolean[10];
        board.positions(X, O, S);
        boolean firstWins, secondWins, playerInteraction = false;
        firstWins = X[1] && X[2] && X[3] || X[4] && X[5] && X[6] || X[7] && X[8] && X[9] || X[1] && X[4] && X[7] || X[2] && X[5] && X[8] || X[3] && X[6] && X[9] || X[1] && X[5] && X[9] || X[7] && X[5] && X[3];
        secondWins = O[1] && O[2] && O[3] || O[4] && O[5] && O[6] || O[7] && O[8] && O[9] || O[1] && O[4] && O[7] || O[2] && O[5] && O[8] || O[3] && O[6] && O[9] || O[1] && O[5] && O[9] || O[7] && O[5] && O[3];
        //figure out how to recognize an early draw
        
        if (numGames > gamesForBrain) {
            if (firstWins) {
                System.out.printf("%nPlayer " + player.getName() + " has won!%n");
                playerInteraction = true;
            } else if (secondWins) {
                System.out.printf("%nThe computer is the winner!%n");
                playerInteraction = true;
            } else if (board.getMemory().size() == 9) {
                System.out.printf("%nIt's a draw!%n");
                playerInteraction = true;
            }
        }
        if(numGames <= gamesForBrain){
            if (firstWins) {
                for(int i = 0; i <= board.getMemory().size()-1; i = i + 2){
                    bot.getMasterBrain().get(i).add(board.getMemory().get(i));
                }
                playerInteraction = false;
            } else if (secondWins) {
                for(int i = 1; i <= board.getMemory().size()-1; i = i + 2){
                    bot.getMasterBrain().get(i).add(board.getMemory().get(i));
                }
                playerInteraction = false;
            } else if (board.getMemory().size() == 9) {
                playerInteraction = false;
            }
        }

        return playerInteraction;
        //program a way for it to know early on when it is a draw
    }

    public void restart() {
        String answer;
        do { //this loop allows the question to be asked repeatedly for if the input is incorrect
            System.out.printf("%nWould you like to start a new game? (Y/N) ");
            answer = input.next().toUpperCase();
            System.out.printf("%n");
            if (answer.equals("Y")) {
                board.getMemory().clear();
                board.reset();
                play();
            } else if (answer.equals("N")) {
                System.out.printf("Goodbye! Thanks for playing!%n");
                break;
            } else {
                System.out.printf("That is not a valid input.%n");
            }
        } while (!(answer.equals("Y") || answer.equals("N")));
    }

    public void endgameAttempt(ArrayList<Integer> playerMemory, ArrayList<Integer> botMemory) {
        /*
        String playerWin = player.getName() + " has won!";
        String botWin = "The computer has won!";
        
        for (int i = 1; i <= 7; i=i+3){
            if(playerMemory.contains(i) && playerMemory.contains(i+1) && playerMemory.contains(i+2)){
                System.out.println(playerWin);
            }
            if(botMemory.contains(i) && botMemory.contains(i+1) && botMemory.contains(i+2)){
                System.out.println(botWin);
            }
        }
        for (int i = 1; i <= 7; i++){
            if(playerMemory.contains(i) && playerMemory.contains(i+3) && playerMemory.contains(i+6)){
                System.out.println(playerWin);
            }
            if(botMemory.contains(i) && botMemory.contains(i+3) && botMemory.contains(i+6)){
                System.out.println(botWin);
            }
        }
        if(playerMemory.contains(1) && playerMemory.contains(5) && playerMemory.contains(9) ||
           playerMemory.contains(3) && playerMemory.contains(5) && playerMemory.contains(7)){
                System.out.println(playerWin);
        }
        if(botMemory.contains(1) && botMemory.contains(5) && botMemory.contains(9) ||
           botMemory.contains(3) && botMemory.contains(5) && botMemory.contains(7)){
                System.out.println(botWin);
        }
         */
    }
}

//whatever r and c are when input int is equal to an int in intBoard, 
//the equivalent position on charBoard is converted to the character
//respective to the source of input

/*
    
    O |   |   
   ---\-------
      | O |   
   -------\---
      |   | O 
    
      |   |   
   -----------
   -O-|-O-|-O-
   -----------
      |   |   
    
      | O |   
   -----|-----
      | O |   
   -----|-----
      | O |   
    
    */