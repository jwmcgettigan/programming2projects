/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Justin
 */
public class Computer {

    private Random rand;
    private ArrayList<Integer> memory;
    private int choice;

    public Computer() {
        memory = new ArrayList<>();
        rand = new SecureRandom();
    }

    public void choice(ArrayList<Integer> boardMemory, int otherPlayerChoice) {
        if (boardMemory.size() <= 7) {
            do {
                choice = rand.nextInt(9) + 1;
            } while (boardMemory.contains(choice) || choice == otherPlayerChoice);
        }
    }

    public void computerMemory() {
        memory.add(choice);
    }

    public int getChoice() {
        return choice;
    }

    public ArrayList<Integer> getMemory() {
        return memory;
    }
}
