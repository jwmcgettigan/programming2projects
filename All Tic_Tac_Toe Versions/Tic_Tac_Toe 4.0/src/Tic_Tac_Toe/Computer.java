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
 *
 * @author Justin
 */
public class Computer {

    private Random rand;
    private LinkedList<String> brain1, brain2;

    public Computer() {
        rand = new SecureRandom();
        brain1 = new LinkedList<>(Arrays.asList("123456789"));
        brain1 = new LinkedList<>(Arrays.asList("123456789"));
    }

    public String choice(String boardMemory, int gameNum, int who) {
        String choice = "";
        LinkedList<String> tempBrain1, tempBrain2;
        String methodChoice;
        tempBrain1 = new LinkedList<String>(brain1);
        tempBrain2 = new LinkedList<String>(brain2);
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
         */
        do {
            //the method, past the equivalent length of boardMemory, cannot contain numbers that are within boardMemory
            //the method chosen must be the shortest
            //after 20 games
            //round1: search through existing methods
            //remove those that have characters from boardMemory beyond the equivalent turn in boardMemory
            //EX. 
            //
            //
            
            /*
            from brain1: 1473 [t1, t3, t5, t7]
            from brain2: 8295 [t2, t4, t6, t8]
            
            when searching through brain1 solutions, compare each character to every other character starting at turn1
            when searching through brain2 solutions, compare each character to every other character starting at turn 2
            
            for every character in each solution, if any character beyond the turn is equal to any character within boardMemory, remove that solution
            */
            
            if (who == 1) {
                for (int i = 0; i < boardMemory.length(); i = i + 2) {
                    boardMemory.charAt(i);
                }
                for (int i = 1; i < board.getMemory().length(); i = i + 2) {
                    movesToWin += board.getMemory().charAt(i);
                }
                
                for (int i = 1; i < tempBrain1.size(); i++) {
                    for (int k = 0; k < boardMemory.length(); k++) {
                        if (tempBrain1.get(i).substring(boardMemory.length()).contains(String.valueOf(boardMemory.charAt(k)))) {
                            tempBrain1.remove(i);
                        }
                    }

                }

                for (int i = 1; i < tempBrain1.size(); i++) {
                    if (!(tempBrain1.get(i).indexOf(boardMemory) == 0)) {
                        tempBrain1.remove(i);
                    }
                }

                methodChoice = tempBrain1.get(rand.nextInt(tempBrain1.size())); //randomly chooses a solution after they have been filtered

            } else {

            }

            if (tempBrain.size() > 1 && methodChoice.length() <= boardMemory.length()) {
                choice = String.valueOf(methodChoice.charAt(methodChoice.lastIndexOf(boardMemory) + 1));
            } else {
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

    public LinkedList<String> getBrain1() {
        return brain1;
    }

    public LinkedList<String> getBrain2() {
        return brain2;
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
