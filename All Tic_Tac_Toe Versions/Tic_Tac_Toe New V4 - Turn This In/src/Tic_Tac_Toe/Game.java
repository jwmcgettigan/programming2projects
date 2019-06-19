/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.util.Scanner;

/**
 * @project Tic Tac Toe 2.4
 * @author Justin McGettigan
 */
public class Game {
    private Scanner input;
    private Board board;
    private Player player;
    private int gameNum; //This variable is a number that indicates how many games have been played.
    
    public Game(){
        input = new Scanner(System.in);
        board = new Board();
        player = new Player();
        board.generate();
        gameNum = 1;
    }
    
    /**
     * This method starts a game of Tic Tac Toe.
     */
    public void play(int difficulty){
        
        while(board.getOccupiedTiles().length() < 9){
            if(player.decide(1, gameNum, difficulty) == true){break;}
            if(player.decide(2, gameNum, difficulty) == true){break;}
        }
        gameNum++;
        if(player.humanStart(gameNum)){
            play(difficulty);
        } else {
            if(player.restart()){
                board.print();
                play(difficulty);
            }
        }
    }
}