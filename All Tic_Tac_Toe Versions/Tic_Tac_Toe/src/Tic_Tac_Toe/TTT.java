/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Justin McGettigan
 */
public class TTT {

    private int[][] positions;
    private String[][] gameBoard;
    protected ArrayList<Integer> memory;
    public Scanner input;

    public TTT() {
        memory = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public void generateBoards() {
        positions = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}};

        gameBoard = new String[][]{
        {" ", " ", " "},
        {" ", " ", " "},
        {" ", " ", " "}};
    }

    public void printBoards() {

        System.out.printf("%s %17s%n", "Game Board:", "Positions:");
        System.out.printf("%2s %1s %1s %1s %1s", gameBoard[0][0], "|", gameBoard[0][1], "|", gameBoard[0][2]);
        System.out.printf("%10s %1s %1s %1s %1s%n", "1", "|", "2", "|", "3");
        System.out.printf("%s %17s%n", "-----------", "-----------");
        System.out.printf("%2s %1s %1s %1s %1s", gameBoard[1][0], "|", gameBoard[1][1], "|", gameBoard[1][2]);
        System.out.printf("%10s %1s %1s %1s %1s%n", "4", "|", "5", "|", "6");
        System.out.printf("%s %17s%n", "-----------", "-----------");
        System.out.printf("%2s %1s %1s %1s %1s", gameBoard[2][0], "|", gameBoard[2][1], "|", gameBoard[2][2]);
        System.out.printf("%10s %1s %1s %1s %1s%n", "7", "|", "8", "|", "9");
    }

    public void resetBoard() {
        gameBoard = new String[][]{
        {" ", " ", " "},
        {" ", " ", " "},
        {" ", " ", " "}};
    }
    
    public void boardPositions(boolean[] X, boolean[] O, boolean[] S){
        int[][] row = positions;
        int[] column;
        
        for (int r = 0; r <= 2; r++) {
            column = row[r];
            for (int c = 0; c <= 2; c++) {
                X[column[c]] = (gameBoard[r][c].equals("X"));
                O[column[c]] = (gameBoard[r][c].equals("O"));
                S[column[c]] = (gameBoard[r][c].equals(" "));
            }
        }
    }
    
    public void remember(int humanPosition, int botPosition) {
        memory.add(humanPosition);
        if (memory.size() < 9) {
            memory.add(botPosition);
        }
    }

    public boolean winConditions(String name) {
        boolean[] X = new boolean[10], O = new boolean[10], S = new boolean[10];
        boardPositions(X, O, S);
        
        if (X[1] && X[2] && X[3] || X[4] && X[5] && X[6] || X[7] && X[8] && X[9] || X[1] && X[4] && X[7] || X[2] && X[5] && X[8] || X[3] && X[6] && X[9] || X[1] && X[5] && X[9] || X[7] && X[5] && X[3]) {
            System.out.printf("%nCongratulations " + name + "! You won!%n");
            return true;
        }
        if (O[1] && O[2] && O[3] || O[4] && O[5] && O[6] || O[7] && O[8] && O[9] || O[1] && O[4] && O[7] || O[2] && O[5] && O[8] || O[3] && O[6] && O[9] || O[1] && O[5] && O[9] || O[7] && O[5] && O[3]) {
            System.out.printf("%nHow terrible... you've lost!%n");
            return true;
        }
        return false;
    }

    public boolean moves(int humanPosition, String difficulty, String name) { //find the position that matches the player's input and place their piece at that position
        int[][] row = positions;
        int[] column;
        int botPosition = 0;

        for (int r = 0; r <= 2; r++) {
            column = row[r];
            for (int c = 0; c <= 2; c++) {
                if (humanPosition > 0 && humanPosition < 10) {
                    if (humanPosition == column[c]) {
                        gameBoard[r][c] = "X";
                        printBoards();
                        if (winConditions(name)) {
                            return true;
                        }
                    }
                }
            }
        }
        if (difficulty.equals("easy")) {
            botPosition = easyBot(humanPosition);
        } else if (difficulty.equals("normal")) {
            botPosition = normalBot(humanPosition);
        } else if (difficulty.equals("impossible")) {
            //botPosition = inpossibleBot(humanPosition);
        }
        for (int r = 0; r <= 2; r++) {
            column = row[r];
            for (int c = 0; c <= 2; c++) {
                if (botPosition > 0 && botPosition < 10) {
                    if (botPosition == column[c] && memory.size() < 8) {
                        System.out.printf("%nComputer chooses position " + botPosition + "%n%n");
                        gameBoard[r][c] = "O";
                        printBoards();
                        if (winConditions(name)) {
                            return true;
                        }
                    }
                }
            }
        }
        remember(humanPosition, botPosition);
        return false;
    }

    public int easyBot(int humanPosition) {
        SecureRandom rand = new SecureRandom();
        int botPosition;

        do { //the computer will keep generating numbers between 1-9 until the number is both not what the human chose this turn and not something that is already occupied
            botPosition = rand.nextInt(9) + 1;
        } while ((memory.contains(botPosition) || botPosition == humanPosition) && memory.size() < 8);
        return botPosition;
    }

    public int normalBot(int humanPosition) {
        SecureRandom rand = new SecureRandom();
        int botPosition = 0;
        boolean[] X = new boolean[10], O = new boolean[10], S = new boolean[10];
        boardPositions(X, O, S);
        
        do {
            if (S[1] && ((O[2] && O[3]) || (O[4] && O[7]) || (O[5] && O[9]))) {
                botPosition = 1;
            } else if (S[2] && ((O[1] && O[3]) || (O[5] && O[8]))) {
                botPosition = 2;
            } else if (S[3] && ((O[1] && O[2]) || (O[6] && O[9]) || (O[5] && O[7]))) {
                botPosition = 3;
            } else if (S[4] && ((O[1] && O[7]) || (O[5] && O[6]))) {
                botPosition = 4;
            } else if (S[5] && ((O[2] && O[8]) || (O[4] && O[6]) || (O[7] && O[3]) || (O[1] && O[9]))) {
                botPosition = 5;
            } else if (S[6] && ((O[4] && O[5]) || (O[3] && O[9]))) {
                botPosition = 6;
            } else if (S[7] && ((O[1] && O[4]) || (O[8] && O[9]) || (O[5] && O[3]))) {
                botPosition = 7;
            } else if (S[8] && ((O[2] && O[5]) || (O[7] && O[9]))) {
                botPosition = 8;
            } else if (S[9] && ((O[7] && O[8]) || (O[3] && O[6]) || (O[1] && O[5]))) {
                botPosition = 9;
            } else if (S[1] && ((X[2] && X[3]) || (X[4] && X[7]) || (X[5] && X[9]))) {
                botPosition = 1;
            } else if (S[2] && ((X[1] && X[3]) || (X[5] && X[8]))) {
                botPosition = 2;
            } else if (S[3] && ((X[1] && X[2]) || (X[6] && X[9]) || (X[5] && X[7]))) {
                botPosition = 3;
            } else if (S[4] && ((X[1] && X[7]) || (X[5] && X[6]))) {
                botPosition = 4;
            } else if (S[5] && ((X[2] && X[8]) || (X[4] && X[6]) || (X[7] && X[3]) || (X[1] && X[9]))) {
                botPosition = 5;
            } else if (S[6] && ((X[4] && X[5]) || (X[3] && X[9]))) {
                botPosition = 6;
            } else if (S[7] && ((X[1] && X[4]) || (X[8] && X[9]) || (X[5] && X[3]))) {
                botPosition = 7;
            } else if (S[8] && ((X[2] && X[5]) || (X[7] && X[9]))) {
                botPosition = 8;
            } else if (S[9] && ((X[7] && X[8]) || (X[3] && X[6]) || (X[1] && X[5]))) {
                botPosition = 9;
            } else if (S[5]) {
                botPosition = 5;
            } else {
                botPosition = rand.nextInt(9) + 1;
            }
        } while ((memory.contains(botPosition) || botPosition == humanPosition) && memory.size() < 8);
        return botPosition;
    }
    
    //public int impossibleBot(int humanPosition){}

    public void game(String name, String difficulty) { //contains everything regarding gameplay
        int humanPosition;
        
        generateBoards();
        printBoards();

        do {
            do {
                System.out.printf("%n" + name + ", please enter a move (1-9): ");
                humanPosition = input.nextInt();
                System.out.printf("%n");

                while (humanPosition < 1 || humanPosition > 9) { //if user input is not between 1-9, the user must attempt input again
                    System.out.printf("That is not a valid move " + name + ".  %nYou must enter a move (1-9): ");
                    humanPosition = input.nextInt();
                }
                if (memory.contains(humanPosition)) { //checks whether a position is occupied
                    System.out.printf("That space has already been chosen.%n");
                    System.out.printf(". . .");
                }
            } while (memory.contains(humanPosition) && memory.size() < 9);
            if (moves(humanPosition, difficulty, name)) {break;}
        } while (memory.size() < 9);
        if (memory.size() == 9) //if the board is full and nobody won, then a draw is declared
        {
            System.out.printf("%nIt's a draw!%n");
        }
    }

    public void startGame(String name) { //starts the game and restarts the game (optionally)
        boolean goAgain = true;
        String answer;

        do { //this do-while loop starts the game and will restart the game each time the player chooses to
            chooseDifficulty(name);
            do { //this loop allows the question to be asked repeatedly for if the input is incorrect
                System.out.printf("%nWould you like to start a new game? (Y/N) ");
                answer = input.next().toUpperCase();
                if (answer.equals("Y")) {
                    goAgain = true;
                    memory.clear();
                    resetBoard();
                } else if (answer.equals("N")) {
                    goAgain = false;
                    System.out.printf("Goodbye! Thanks for playing!%n");
                } else {
                    System.out.printf("That is not a valid input.%n");
                }
            } while (!(answer.equals("Y") || answer.equals("N")));
        } while (goAgain);
    }

    public void chooseDifficulty(String name) {
        String difficulty;
        do {
            System.out.printf("Choose a difficulty. Easy/Normal/Impossible (E/N/I) ");
            difficulty = input.next().toUpperCase();

            if (difficulty.equals("E")) {
                System.out.printf("%n=========|EASY-MODE|=========%n");
                game(name, "easy");
            } else if (difficulty.equals("N")) {
                System.out.printf("%n========|NORMAL-MODE|========%n");
                game(name, "normal");
            } else if (difficulty.equals("I")) {
                //System.out.printf("%n======|IMPOSSIBLE-MODE|======%n");
                System.out.printf("This difficulty is under construction.");
                //game(name, "impossible");
            } else {
                System.out.printf("That is not a valid input.%n");
            }
        } while (!(difficulty.equals("E") || difficulty.equals("N") /*|| difficulty.equals("I")*/));
    }
}

/*  humanMoves(humanPosition);
    if(winConditions(name)) {break;} //the human is declared winner
    //try {Thread.sleep(1000);} catch (InterruptedException ex) {}
    cpuMoves(botPosition);
    if(winConditions(name)) {break;} //the human is declared loser

THIS WAS REPLACED BY: if(moves(humanPosition, botPosition, name)){break;} */
