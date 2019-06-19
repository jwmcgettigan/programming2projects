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
public class Testing {
    public static void main(String[] args) {
        Game game = new Game();
        Human human = new Human();
        
        game.play(human.menu());
    }
}
