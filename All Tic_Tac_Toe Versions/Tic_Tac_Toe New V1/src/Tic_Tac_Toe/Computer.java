/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.security.SecureRandom;
import java.util.LinkedList;

/**
 * @project Tic Tac Toe New V1
 * @author Justin McGettigan
 */
public class Computer {

    private SecureRandom rand;
    private LinkedList<String> brain;
    private int decision;

    public Computer() {
        rand = new SecureRandom();
    }
    
    /**
     * This method uses data from past games to determine the decision that will
     * most likely lead to victory and when unable to draw upon past games, the
     * decision will be random; This method also verifies the decision.
     *
     * @param occupiedTiles This parameter is the list of tiles that are
     * currently occupied as well as a record of all decisions made by players
     * for the current game.
     */
    public void decide(String occupiedTiles) {
        String basic = "123456789";
        if (basic.length() > 2) {
            for (int i = 0; i < occupiedTiles.length(); i++) {
                if (basic.contains(String.valueOf(occupiedTiles.charAt(i)))) {
                    basic = basic.replace(String.valueOf(occupiedTiles.charAt(i)), "");
                }
            }
            decision = Character.getNumericValue(basic.charAt(rand.nextInt(basic.length())));
        }
    }

    public LinkedList<String> getBrain() {
        return brain;
    }

    public int getDecision() {
        return decision;
    }
}
