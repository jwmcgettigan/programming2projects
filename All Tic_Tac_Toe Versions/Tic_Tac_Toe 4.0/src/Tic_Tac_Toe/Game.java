/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.util.Scanner;

/**
 * @v4
 * @author Justin
 */
public class Game {

    private Scanner input;
    private Board board;
    private Player player;
    private Computer bot;
    private int gameNum;
    private final int gamesForBrain = 5;

    public Game() {
        input = new Scanner(System.in);
        board = new Board();
        player = new Player();
        bot = new Computer();
        board.generate();
        gameNum = 1;
    }

    public void play() {
        int first, second = 0;
        System.out.println("game" + gameNum);
        while (board.getMemory().length() < 9) {
            //===================================================================!!!!!
            if (gameNum <= gamesForBrain) {
                first = Integer.valueOf(bot.choice(board.getMemory(), gameNum, 1)); //need to make sure that each call calls a different number
            } else {
                if (gameNum == gamesForBrain + 1 && board.getMemory().length() < 1) {
                    System.out.println("Brain: " + (bot.getBrain1().size() + bot.getBrain2().size() - 1));
                    player.name(); //need to only run once
                    board.print();
                }
                player.choice(board.getMemory());
                first = player.getChoice();
            }
            //===================================================================!!!!!
            board.boardMemory(first, 1); //could insert an incremental int within and have the method return with it's next value
            System.out.println("move" + board.getMemory().length() + ": " + first);
            if (findAndAssignPosition(first, 1)) {
                break;
            }
            if (board.getMemory().length() < 8) {
                second = Integer.valueOf(bot.choice(board.getMemory(), gameNum, 2));
                board.boardMemory(second, 2);
                System.out.println("move" + board.getMemory().length() + ": " + second);
                if (findAndAssignPosition(second, 2)) {
                    break;
                }
            }
            //System.out.println("Board memory length: " + board.getMemory().length());
            //===================================================================!!!!!
        }
        //===================================================================
        gameNum++;

        if (gameNum <= gamesForBrain + 1) {
            board.setMemory("");
            board.reset();
            play();
        } else {
            restart();
        }
        //===================================================================
    }

    public boolean findAndAssignPosition(int choice, int who) {
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                if (choice == board.getIntBoard()[r][c]) {
                    //System.out.println(who);
                    if (who == 1) {
                        board.getCharBoard()[r][c] = 'X';
                    } else {
                        board.getCharBoard()[r][c] = 'O';
                    }
                    if (gameNum > gamesForBrain) {
                        if (who == 2) {
                            System.out.printf("%nComputer chooses position " + choice + "%n%n");
                        }
                        board.print();
                    }
                    //board.print();
                    return endgame(gameNum);
                }
            }
        }
        return false;
    }

    public boolean endgame(int gameNum) {
        boolean[] X = new boolean[10], O = new boolean[10], S = new boolean[10];
        board.positions(X, O, S);
        boolean firstWins, secondWins;
        firstWins = X[1] && X[2] && X[3] || X[4] && X[5] && X[6] || X[7] && X[8] && X[9] || X[1] && X[4] && X[7] || X[2] && X[5] && X[8] || X[3] && X[6] && X[9] || X[1] && X[5] && X[9] || X[7] && X[5] && X[3];
        secondWins = O[1] && O[2] && O[3] || O[4] && O[5] && O[6] || O[7] && O[8] && O[9] || O[1] && O[4] && O[7] || O[2] && O[5] && O[8] || O[3] && O[6] && O[9] || O[1] && O[5] && O[9] || O[7] && O[5] && O[3];
        //figure out how to recognize an early draw
        if (gameNum > gamesForBrain) {
            if (firstWins) {
                System.out.printf("%nPlayer " + player.getName() + " has won!%n");
                return true;
            } else if (secondWins) {
                System.out.printf("%nThe computer is the winner!%n");
                return true;
            } else if (board.getMemory().length() == 9) {
                System.out.printf("%nIt's a draw!%n");
                return true;
            }
        }
        if (gameNum <= gamesForBrain) {
            String movesToWin = "";
            if (firstWins) {
                for (int i = 0; i < board.getMemory().length(); i = i + 2) {
                    movesToWin += board.getMemory().charAt(i);
                }
                bot.getBrain1().add(movesToWin);
                //System.out.println("Bot 1 Wins!");
                return true;
            } else if (secondWins) {
                for (int i = 1; i < board.getMemory().length(); i = i + 2) {
                    movesToWin += board.getMemory().charAt(i);
                }
                bot.getBrain2().add(movesToWin);
                //System.out.println("Bot 2 Wins!");
                return true;
            } else if (board.getMemory().length() == 9) {
                //System.out.println("It's a Draw!");
                return true;
            }
        }
        return false;
        //program a way for it to know early on when it is a draw
    }

    public void restart() {
        String answer;
        boolean invalid;

        outer:
        do { //this loop allows the question to be asked repeatedly for if the input is incorrect
            System.out.printf("%nWould you like to start a new game? (Y/N) ");
            answer = input.next().toUpperCase();
            System.out.printf("%n");
            if (answer.equals("Y")) {
                invalid = false;
                board.setMemory("");
                board.reset();
                board.print();
                play();
            } else if (answer.equals("N")) {
                invalid = false;
                System.out.printf("Goodbye! Thanks for playing!%n");
                System.out.println(bot.getBrain1() + "-" + bot.getBrain2());
            } else {
                invalid = true;
                System.out.printf("That is not a valid input.%n");
            }
        } while (invalid);
    }

    public void notes() {
        /*
        String movesToWin = "";
            if (firstWins) {
                for (int i = 0; i <= board.getMemory().length() - 1; i = i + 2) {
                    movesToWin += board.getMemory().charAt(i);
                }
                bot.getBrain().add(movesToWin);
                //System.out.println("Bot 1 Wins!");
                return true;
            } else if (secondWins) {
                for (int i = 1; i <= board.getMemory().length() - 1; i = i + 2) {
                    movesToWin += board.getMemory().charAt(i);
                }
                bot.getBrain().add(movesToWin);
                //System.out.println("Bot 2 Wins!");
                return true;
            } else if (board.getMemory().length() == 9) {
                //System.out.println("It's a Draw!");
                return true;
            }
        */
        
        
        /*====================================================================
        while  the current turn is less than the 9th one, (re)start the process below
        -
        if during 10,000 training games, computer supplies first choice
        if after 10,000 training games, player supplies first choice
        insert the first choice into BOARD MEMORY
        computer supplies second choice
        insert the second choice BOARD MEMORY
        check through intBoard to find the position of the first move //try to turn these into a method to save space
        if the the current turn is less than the 9th one, check through intBoard to find the position of the second move
        -
        if during the 10,000 training games, automatically restart
        if after the 10,000 trainings games, ask the player if they would like to restart
    
    turn number can be derived from boardMemory length
        =====================================================================*/
        //whatever r and c are when input int is equal to an int in intBoard, 
        //the equivalent position on charBoard is converted to the character
        //respective to the source of input

        /*
    If I so choose, I can add this nice art to winning boards. I would just place the changable variables into the respective positions on printBoard()
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
    }
}
