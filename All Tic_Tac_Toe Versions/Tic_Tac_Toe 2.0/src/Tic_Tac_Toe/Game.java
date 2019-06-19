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

    public Game() {
        input = new Scanner(System.in);
        board = new Board();
        player = new Player();
        bot = new Computer();
    }

    public void start() {
        board.generate();
        player.name();
    }

    public void play() {
        board.print();
        outer:
        while (board.getMemory().size() < 9) {
            player.choice(board.getMemory());
            int playerChoice = player.getChoice();
            bot.choice(board.getMemory(), playerChoice);
            int botChoice = bot.getChoice();
            
            board.boardMemory(playerChoice, bot.getChoice());
            for (int r = 0; r <= 2; r++) {
                for (int c = 0; c <= 2; c++) {
                    if (playerChoice == board.getIntBoard()[r][c]) {
                        board.getCharBoard()[r][c] = 'X';
                        board.print();
                        player.playerMemory();
                        if(endgame()){break outer;}
                    }
                }
            }
            if (board.getMemory().size() <= 8) {
                for (int r = 0; r <= 2; r++) {
                    for (int c = 0; c <= 2; c++) {
                        if (bot.getChoice() == board.getIntBoard()[r][c]) {
                            System.out.printf("%nComputer chooses position " + bot.getChoice() + "%n%n");
                            board.getCharBoard()[r][c] = 'O';
                            board.print();
                            bot.computerMemory();
                            if(endgame()){break outer;}
                        }
                    }
                }
            }
        }
        restart();
    }
    
    public boolean endgame() {
        boolean[] X = new boolean[10], O = new boolean[10], S = new boolean[10];
        board.positions(X, O, S);
        
        if (X[1] && X[2] && X[3] || X[4] && X[5] && X[6] || X[7] && X[8] && X[9] || X[1] && X[4] && X[7] || X[2] && X[5] && X[8] || X[3] && X[6] && X[9] || X[1] && X[5] && X[9] || X[7] && X[5] && X[3]) {
            System.out.printf("%nPlayer " + player.getName() + " has won!%n");
            return true;
        } else if (O[1] && O[2] && O[3] || O[4] && O[5] && O[6] || O[7] && O[8] && O[9] || O[1] && O[4] && O[7] || O[2] && O[5] && O[8] || O[3] && O[6] && O[9] || O[1] && O[5] && O[9] || O[7] && O[5] && O[3]) {
            System.out.printf("%nThe computer is the winner!%n");
            return true;
        } else if(board.getMemory().size() == 9){
            System.out.printf("%nIt's a draw!%n");
            return true;
        } return false;
        //program a way for it to know early on when it is a draw
    }
    
    public void restart(){
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
    
    
    public void endgameAttempt(ArrayList<Integer> playerMemory, ArrayList<Integer> botMemory){
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
