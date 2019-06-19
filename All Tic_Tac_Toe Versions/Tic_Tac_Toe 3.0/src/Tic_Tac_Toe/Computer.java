/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Justin
 */
public class Computer {

    private Random rand;
    private ArrayList<Integer> brain1, brain2, brain3, brain4, brain5, brain6, brain7, brain8, brain9;
    private ArrayList<ArrayList<Integer>> masterBrain;

    public Computer() {
        rand = new SecureRandom();
        brain1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)); //https://stackoverflow.com/questions/21696784/how-to-declare-an-arraylist-with-values
        brain2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        brain3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        brain4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        brain5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        brain6 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        brain7 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        brain8 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        brain9 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        masterBrain = new ArrayList<>(Arrays.asList(brain1, brain2, brain3, brain4, brain5, brain6, brain7, brain8, brain9));
    }

    public int choice(ArrayList<Integer> boardMemory, int otherPlayerChoice) {
        /*
        if (boardMemory.size() <= 7) {
            do {
                choice = rand.nextInt(9) + 1;
            } while (boardMemory.contains(choice) || choice == otherPlayerChoice);
        }*/
        int choice = 0;
        if (boardMemory.size() <= 7) {
            do {
                choice = masterBrain.get(boardMemory.size()).get(rand.nextInt(masterBrain.get(boardMemory.size()).size()));
            } while (boardMemory.contains(choice) || choice == otherPlayerChoice); //how to exclude already searched for numbers from the next search?
        }
        return choice;
    }

    public ArrayList<ArrayList<Integer>> getMasterBrain() {
        return masterBrain;
    }
}
