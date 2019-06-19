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
    
    /*
    How to use knowledge/data from past games to determine where to move?
    
    1.If the opponent moves such that it matches an existing dataset, play to that dataset.
    */
    
    public static void main(String[] args) {
        //Board board = new Board();
        //Player player = new Player();
        Game game = new Game();
        //Computer bot = new Computer();
        
        game.play();
        System.out.println("- - session ended - -");
        
    }
    
    //each game, save the state of the board after each move
    
    //a maximum of 5 moves can be made if you are player 1
    //a maximum of 4 moves can be made if you are player 2
    
    //keep a list of the moves made for both players
    //add the winner's moves to an array of randomly chosen board positions for board state
    //discard the losers move list and discard both in the instance of a tie
    
    
    //needs to play against itself 10,000 times
        //take the list of moves made by the winner each time
        //and add those numbers to an arraylist for each turn (4 turns for bot 2, and 5 turns for bot 1)
        //during the next game, a number in the arraylist is randomly chosen
        //at each board state
        //how to keep/save the intelligence
        //how to utilize intelligence against the player
}
