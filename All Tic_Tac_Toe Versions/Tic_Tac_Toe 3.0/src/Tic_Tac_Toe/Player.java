/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Justin
 */
public class Player {
    private Scanner input;
    private String name;
    private int choice;
    
    public Player() {
        input = new Scanner(System.in);
    }
    
    public void name(){
        System.out.println("Poly Tic-tac-toe has started.");
        System.out.printf("Enter a name for the Human Player: ");
        name = input.next();
    }
    
    public void choice(ArrayList<Integer> boardMemory){
        System.out.printf("%n" + name + ", please enter a move (1-9): ");
        choice = input.nextInt();
        System.out.printf("%n");

        while (choice < 1 || choice > 9) { //if user input is not between 1-9, the user must attempt input again
            System.out.printf("That is not a valid move " + name + ". %nYou must enter a move (1-9): %n");
            choice = input.nextInt();
        }
        while (boardMemory.contains(choice)) { //checks whether a position is occupied
            System.out.printf("That space has already been chosen. %nPlease enter a move (1-9): %n");
            choice = input.nextInt();
        }
    }
    
    public int getChoice(){
        return choice;
    }
    
    public String getName(){
        return name;
    }
}
