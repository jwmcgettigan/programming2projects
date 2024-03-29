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
public class Board {
    private int[][] coords; //This variable contains the coordinates for the tiles.
    private char[][] marks; //This variable contains the characters for each tile.
    private int[][] values; //This variable contains the values for each tile (if occupied and occupied by who?).
    private String occupiedTiles; //This variable is the list of tiles that are currently occupied as well as a record of all decisions made by players for the current game.
    private String vacantTiles; //This variable is the list of tiles that are currently empty.
    
    public Board(){
        occupiedTiles = "";
        vacantTiles = "123456789";
    }
    
    /**
     * This method is used to generate
     * both 'coords' and 'values'
     * ('values' is generated by
     * calling reset() ).
     */
    public void generate(){
        coords = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}};
        
        reset();
    }
    
    /**
     * This method is used to reset the board
     * and tiles to their original values.
     */
    public void reset(){
        values = new int[][]{
            {0,0,0},
            {0,0,0},
            {0,0,0}};
        
        occupiedTiles = "";
        vacantTiles = "123456789";
    }
    
    /**
     * This method is used to print out the
     * current state of the board.
     */
    public void print(){
        marks = new char[3][3];
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                if(values[r][c] == 0){
                    marks[r][c] = ' ';
                } else if(values[r][c] == 1){
                    marks[r][c] = 'X';
                } else if(values[r][c] == 5){
                    marks[r][c] = 'O';
                }
            }
        }
        
        System.out.printf("%nGame Board: %16s%n", " Positions:");
        System.out.printf(" %c | %c | %c  %16s%n",marks[0][0],marks[0][1],marks[0][2], " 1 | 2 | 3 ");
        System.out.printf("----------- %16s%n", "-----------");
        System.out.printf(" %c | %c | %c  %16s%n",marks[1][0],marks[1][1],marks[1][2], " 4 | 5 | 6 ");
        System.out.printf("----------- %16s%n", "-----------");
        System.out.printf(" %c | %c | %c  %16s%n",marks[2][0],marks[2][1],marks[2][2], " 7 | 8 | 9 ");
    }
    
    public void setOccupiedTiles(String oTiles){
        occupiedTiles = oTiles;
    }
    
    public void setVacantTiles(String vTiles){
        vacantTiles = vTiles;
    }
    
    public int[][] getCoords(){
        return coords;
    }
    
    public int[][] getValues(){
        return values;
    }
    
    public String getOccupiedTiles(){
        return occupiedTiles;
    }
    
    public String getVacantTiles(){
        return vacantTiles;
    }
}
