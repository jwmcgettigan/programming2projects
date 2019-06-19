/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.util.Scanner;

/**
 * @project Tic Tac Toe New V1
 * @author Justin McGettigan
 */
public class Game {
    private Scanner input;
    private Board board;
    private Player player;
    private int gameNum;
    
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
    public void play(){
        //System.out.println(gameNum);
        while(board.getOccupiedTiles().length() < 9){
            if(player.decide(1, gameNum) == true){break;}
            if(player.decide(2, gameNum) == true){break;}
        }
        //System.out.println("Game " + gameNum + " over.");
        gameNum++;
        if(player.humanStart(gameNum)){
            play();
        } else {
            if(player.restart()){
                board.print();
                play();
            }
        }
    }
    
}

/*        = 15
1,  2,  3 = 6
4,  5,  6 = 15
7,  8,  9 = 24
=   =   = = 15
12  15  18

6
12
15 - 4
18
24
*/