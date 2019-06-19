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
public class Human {
    private Scanner input;
    private String name;
    private int decision;

    public Human() {
        input = new Scanner(System.in);
    }

    /**
     * This method asks the human for his/her name.
     */
    public void name() {
        System.out.printf("%nPoly Tic-tac-toe is initiating...%n");
        System.out.printf("Enter a name for the Human Player: ");
        name = input.next();
    }

    /**
     * This method presents the human with a navigable menu
     * for determining their desired game.
     */
    public void menu() {
        int choice;
        boolean valid = true;
        System.out.printf("%n-----Game Modes-----%n");
        System.out.printf("1. Human vs Human%n");
        System.out.printf("2. Human vs Computer%n");
        System.out.printf("3. Shortcut%n");
        System.out.printf("%nPlease choose a mode from above (1-2): ");
        do {
            choice = input.nextInt();
            if (choice == 1) {
                System.out.printf("%nStart Human v. Human game");
                valid = true;
            } else if (choice == 2) {
                System.out.printf("%n---Difficulties---%n");
                System.out.printf("1. Easy%n");
                System.out.printf("2. Normal%n");
                System.out.printf("3. Impossible%n");
                System.out.printf("%nPlease choose a difficulty from above (1-3): "); //ask for difficulty with human v. computer game
                do {
                    choice = input.nextInt();
                    if (choice == 1) {
                        System.out.printf("%nStart Easy game"); //start easy game
                        valid = true;
                    } else if (choice == 2) {
                        System.out.printf("%nStart Normal game"); //start normal game
                        valid = true;
                    } else if (choice == 3) {
                        System.out.printf("%nStart Impossible game"); //start impossible game
                        valid = true;
                    } else {
                        System.out.printf("%nThat is not a valid. Please try again: ");
                        valid = false;
                    }
                } while (valid == false);

            } else if (choice == 3){
                
            } else{
                System.out.printf("%nThat is not a valid. Please try again: ");
                valid = false;
            }
        } while (valid == false);

    }

    /**
     * This method prompts the user to decide where to go and,
     * if that decision is not valid, the user is informed of
     * his/her error and prompted to decide again.
     * @param occupiedTiles This parameter is the list of tiles that are currently occupied as well as a record of all decisions made by players for the current game.
     */
    public void decide(String occupiedTiles) {
        boolean valid;
        System.out.printf("%n" + name + ", please enter a move (1-9): ");
        do {
            valid = true;
            while(!input.hasNextInt()){
                input.next();
                System.out.printf("That is not valid " + name + ". Please try again: ");
            }
            decision = input.nextInt();
            //System.out.println(occupiedTiles);
            if(decision < 1 || decision > 9){
                System.out.printf("That is not valid " + name + ". Please try again: ");
                valid = false;
            }
            if(occupiedTiles.contains(String.valueOf(decision))){
                System.out.printf("That space is occupied. Please try again: ");
                valid = false;
            }
        } while(valid == false);
    }
    
    /**
     * This method prompts the human for if they want to play another game.
     * @return A boolean value based upon whether the human wants to start another game.
     */
    public boolean restart(){
        String answer;
        boolean valid;
        System.out.printf("%nWould you like to start a new game? (Y/N) ");
        do{
            valid = true;
            answer = input.next().toUpperCase();
            if(answer.equals("Y")){
                return true;
            } else if (answer.equals("N")){
                System.out.printf("%nGoodbye! Thanks for playing!%n");
            } else{
                System.out.printf("%nThat is not a valid. Please try again: ");
                valid = false;
            }
        } while (valid == false);
        return false;
    }

    public String getName() {
        return name;
    }

    public int getDecision() {
        return decision;
    }
}

//misc notes
//if more than one condition is true at the same time, choose randomly between the two (for winning)

//have a memory for spaces that are available as well as what has already been chosen
//check what the result would be for each available space if the other player went there
//promote bad moves by the enemy and prevent good moves by the enemy