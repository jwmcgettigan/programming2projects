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
public class Testing {
    
    public static void main(String[] args) {
        Board board = new Board();
        //Player player = new Player();
        Game game = new Game();
        //Computer bot = new Computer();
        game.start();
        game.play();
        System.out.println("- - session ended - -");
        
    }
    
    //each game, save the state of the board after each move
    
    //a maximum of 5 moves can be made if you are player 1
    //a maximum of 4 moves can be made if you are player 2
    
    //keep a list of the moves made for both players
    //add the winner's moves to an array of randomly chosen board positions for board state
    //discard the losers move list and discard both in the instance of a tie
}
