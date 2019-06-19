/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * @v5
 * @author Justin
 */
public class Computer {

    private Random rand;
    private LinkedList<String> brain;

    public Computer() {
        rand = new SecureRandom();
        brain = new LinkedList<>(Arrays.asList("123456789"));
    }

    /*
    How to use knowledge/data from past games to determine where to move?
        
    1.
    2.If the opponent moves such that it matches an existing dataset, play to that dataset.
     */
    public String choice(String boardMemory, int gameNum, int who) {
        String choice = "";
        LinkedList<String> tempBrain = new LinkedList<String>(brain);
        String methodChoice;
        int turnNum = boardMemory.length() + 1;

        //System.out.println(brain);
        /*
            search through all of the winning solutions
            remove those that are unable to be followed; Remove if:
                - the enemy has already chosen numbers within that solution
            choose a random winning solution among those that remain
            restart this process each time
        
            if otherPlayerChoice = tempBrain.get(i).charAt(this turn)
        
        the length of the winning solution
        
        turn1: can choose anything
        turn2: can only choose those whose first value is not equal to turn 1's value
        turn3: can only choose those whose first and second va
        
        //tempBrain.size() starts out as 1 and increases after some games
            //gameNum starts out as 1 and increases after each game
            //turnNum starts out at 1 each game and increases throughout the game to a maximum of 10
            //if tempBrain.get(i).indexOf(boardMemory) == 0
        
        
        if it includes the number, remove the number from it
        
        19275
        
        if first and charAt 0 is equal to boardMemory of 0
        if second and charAt 0 is equal to boardMemory of 1 
         */
        do {
            //System.out.println(tempBrain);
            /*
            if (who == 1) {
                for (int i = 1; i < tempBrain.size(); i++) {
                    int k = 0;
                    int p = 0;
                    while (k < boardMemory.length()) {
                        if (tempBrain.get(i).charAt(p) == boardMemory.charAt(k)) { //when should a solution be removed? when should it be used?
                            
                            tempString = tempString.replace(String.valueOf(boardMemory.charAt(i)), "");
                            //tempBrain.remove(i);
                        }
                        k += 2;
                        p++;
                    }
                }
            } else {
                for (int i = 1; i < tempBrain.size(); i++) {
                    for (int k = 1; k < boardMemory.length(); k = k + 2) {
                        if (tempBrain.get(i).charAt(turnNum / 2) == boardMemory.charAt(k)) { //when should a solution be removed? when should it be used?
                            tempBrain.remove(i);
                        }
                    }
                }
            }*/
            /*
            for (int i = 1; i < tempBrain.size(); i++) {
                if (!(tempBrain.get(i).charAt(turnNum) == boardMemory.charAt(turnNum))) { //when should a solution be removed? when should it be used?
                    tempBrain.remove(i);
                }
            }*/
            //System.out.println(tempBrain);
            methodChoice = tempBrain.get(rand.nextInt(tempBrain.size()));
            if (tempBrain.size() > 1) {
                String tempString = tempBrain.get(0);
                for (int i = 0; i < boardMemory.length() / 2; i++) {
                    if (tempString.contains(String.valueOf(boardMemory.charAt(i)))) {
                        tempString = tempString.replace(String.valueOf(boardMemory.charAt(i)), "");
                    }
                }
                
                choice = String.valueOf(methodChoice.charAt());
            } else { //this function takes the random selection of numbers 1-9 and lessons the choices by one after each move
                String tempString = tempBrain.get(0);
                for (int i = 0; i < boardMemory.length(); i++) {
                    if (tempString.contains(String.valueOf(boardMemory.charAt(i)))) {
                        tempString = tempString.replace(String.valueOf(boardMemory.charAt(i)), "");
                    }
                }
                choice = String.valueOf(tempString.charAt(rand.nextInt(tempString.length()))); //last resort
            }
        } while (boardMemory.contains(choice)); //how to exclude already searched for numbers from the next search?
        return choice;
    }

    public LinkedList<String> getBrain() {
        return brain;
    }
}

//check all strings
//only choose randomly among strings that, for the current turn, do not use the value the opponent chose

/*
//System.out.println(tempBrain);
            for (int i = 1; i < tempBrain.size(); i++) {
                if (tempBrain.get(i).indexOf(boardMemory) == 0) {
                    tempBrain.remove(i);
                }
            }
            //System.out.println(tempBrain);
            methodChoice = tempBrain.get(rand.nextInt(tempBrain.size()));
            if (tempBrain.size() > 1 && methodChoice.length() <= boardMemory.length()) {
                choice = String.valueOf(methodChoice.charAt(methodChoice.lastIndexOf(boardMemory)+1));
            } else {
                String tempString = tempBrain.get(0);
                for (int i = 0; i < boardMemory.length(); i++) {
                    if (tempString.contains(String.valueOf(boardMemory.charAt(i)))) {
                        tempString = tempString.replace(String.valueOf(boardMemory.charAt(i)), "");
                    }
                }
                choice = String.valueOf(tempString.charAt(rand.nextInt(tempString.length()))); //last resort
            }
 */
