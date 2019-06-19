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
public class Board {
    private int[][] intBoard;
    private char[][] charBoard;
    private String occupiedTiles; //This variable is the list of tiles that are currently occupied as well as a record of all decisions made by players for the current game.
    private String vacantTiles; //This variable is the list of tiles that are currently empty.
    
    public Board(){
        occupiedTiles = "";
        vacantTiles = "";
    }
    
    /**
     * This method is used to generate
     * both intBoard and charBoard.
     */
    public void generate(){
        intBoard = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}};
        
        reset();
    }
    
    /**
     * This method is used to reset charBoard
     * to its original blank template.
     */
    public void reset(){
        charBoard = new char[][]{
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}};
    }
    
    /**
     * This method is used to print out the
     * current state of the board.
     */
    public void print(){
        System.out.printf("%nGame Board: %16s%n", " Positions:");
        System.out.printf(" %c | %c | %c  %16s%n",charBoard[0][0],charBoard[0][1],charBoard[0][2], " 1 | 2 | 3 ");
        System.out.printf("----------- %16s%n", "-----------");
        System.out.printf(" %c | %c | %c  %16s%n",charBoard[1][0],charBoard[1][1],charBoard[1][2], " 4 | 5 | 6 ");
        System.out.printf("----------- %16s%n", "-----------");
        System.out.printf(" %c | %c | %c  %16s%n",charBoard[2][0],charBoard[2][1],charBoard[2][2], " 7 | 8 | 9 ");
    }
    
    /**
     * This method assigns to the equivalent boolean value whether a tile is occupied by a certain value.
     * @param X This parameter tells which tiles are occupied by X.
     * @param O This parameter tells which tiles are occupied by O.
     * @param S This parameter tells which tiles are vacant.
     */
    public void positions(boolean[] X, boolean[] O, boolean[] S){
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                X[intBoard[r][c]] = (charBoard[r][c] == ('X'));
                O[intBoard[r][c]] = (charBoard[r][c] == ('O'));
                S[intBoard[r][c]] = (charBoard[r][c] == (' '));
            }
        }
    }
    
    public void boardState(){
        
    }
    
    public void setOccupiedTiles(String oTiles){
        occupiedTiles = oTiles;
    }
    
    public void setVacantTiles(String vTiles){
        vacantTiles = vTiles;
    }
    
    public int[][] getIntBoard(){
        return intBoard;
    }
    
    public char[][] getCharBoard(){
        return charBoard;
    }
    
    public String getOccupiedTiles(){
        return occupiedTiles;
    }
    
    public String getVacantTiles(){
        return vacantTiles;
    }
}
