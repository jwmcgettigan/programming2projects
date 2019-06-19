/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;


/**
 *
 * @author Justin
 */
public class Board {
    private int[][] intBoard;
    private char[][] charBoard;
    private String memory;
    
    public Board(){
        memory = "";
    }
    
    public void generate(){
        intBoard = new int[][]{ //used to be called 'positions'
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}};
        
        reset();
    }
    
    public void print(){
        System.out.printf("Game Board: %16s%n", " Positions:");
        System.out.printf(" %c | %c | %c  %16s%n",charBoard[0][0],charBoard[0][1],charBoard[0][2], " 1 | 2 | 3 ");
        System.out.printf("----------- %16s%n", "-----------");
        System.out.printf(" %c | %c | %c  %16s%n",charBoard[1][0],charBoard[1][1],charBoard[1][2], " 4 | 5 | 6 ");
        System.out.printf("----------- %16s%n", "-----------");
        System.out.printf(" %c | %c | %c  %16s%n",charBoard[2][0],charBoard[2][1],charBoard[2][2], " 7 | 8 | 9 ");
    }
    
    public void reset(){
        charBoard = new char[][]{
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}};
    }
    /*
    public void boardMemory(int player1Choice, int player2Choice){
        memory = memory + String.valueOf(player1Choice);
        if (memory.length() < 9) {
            memory = memory + String.valueOf(player2Choice);
        }
    }
    */
    public void boardMemory(int choice, int who){
        //System.out.println("THIS IS: " + String.valueOf(choice));
        if(who==1){
            memory += String.valueOf(choice);
        }
        if (who==2 && memory.length() < 9) {
            memory += String.valueOf(choice);
        }
    }
    
    public void positions(boolean[] X, boolean[] O, boolean[] S){
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                X[intBoard[r][c]] = (charBoard[r][c] == ('X'));
                O[intBoard[r][c]] = (charBoard[r][c] == ('O'));
                S[intBoard[r][c]] = (charBoard[r][c] == (' '));
            }
        }
    }
    
    public void setMemory(String mem){
        memory = mem;
    }
    
    public String getMemory(){
        return memory;
    }
    
    public int[][] getIntBoard(){
        return intBoard;
    }
    
    public char[][] getCharBoard(){
        return charBoard;
    }
}
