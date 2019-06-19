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
        brain = new LinkedList<>();
    }

    /**
     * This method uses data from past games to determine the decision that will
     * most likely lead to victory and when unable to draw upon past games, the
     * decision will be random; This method also verifies the decision.
     *
     * @param occupiedTiles This parameter is the list of tiles that are
     * currently occupied as well as a record of all decisions made by players
     * for the current game.
     * @param vacantTiles
     * @param gameNum
     */
    public void decide(String occupiedTiles, String vacantTiles, int gameNum) {
        String basic = "123456789";
        LinkedList<String> tempBrain = brain;
        int difficulty = 1;

        if (difficulty == 1) { //easy
            easy(occupiedTiles, basic);
        } else if (difficulty == 2) {
            normal(occupiedTiles, vacantTiles, basic);
        } else if (difficulty == 3) {
            impossibleTest1(occupiedTiles, tempBrain);
        } else if (difficulty == 4) {
            impossibleTest2(occupiedTiles, vacantTiles, tempBrain);
        }
    }

    public void easy(String occupiedTiles, String basic) {
        for (int i = 0; i < occupiedTiles.length(); i++) {
            if (basic.contains(String.valueOf(occupiedTiles.charAt(i)))) {
                basic = basic.replace(String.valueOf(occupiedTiles.charAt(i)), "");
            }
        }
        decision = Character.getNumericValue(basic.charAt(rand.nextInt(basic.length())));
    }

    public void normal(String occupiedTiles, String vacantTiles, String basic) {
        
    }

    public void impossibleTest1(String occupiedTiles, LinkedList<String> tempBrain) {
        String basic = "123456789";
        String chosenGameState = "";
        boolean random = true;
        //for each boardstate that does not contain 

        //supergoal: if gamestate would lead to a win, choose it, if it would lead to a loss, don't choose it.
        //goal: if gamestate would lead to a win & it contains occupiedTiles
        //System.out.println(tempBrain);
        for (String gameState : tempBrain) {
            if (!gameState.contains(occupiedTiles)) {
                tempBrain.remove(gameState);
            } else {
                gameState = gameState.replace(occupiedTiles, "");
            }
        }
        //System.out.println(tempBrain);
        if (occupiedTiles.length() > 1 && tempBrain.size() > 0) {
            chosenGameState = tempBrain.get(rand.nextInt(tempBrain.size()));
            random = false;
        } else {
            chosenGameState = basic;
            random = true;
        }
        /*
            for (String gameState : tempBrain) {
                if (occupiedTiles.length() > 0 && gameState.contains(occupiedTiles)) {
                    chosenGameState = gameState;
                    random = false;
                } else {
                    chosenGameState = basic;
                    random = true;
                }
            }*/
        if (random == false) {
            decision = Character.getNumericValue(chosenGameState.charAt(occupiedTiles.length()));
        } else {
            easy(occupiedTiles, basic);
        }
    }

    public void impossibleTest2(String occupiedTiles, String vacantTiles, LinkedList<String> tempBrain) {
        String basic = "";
        //if a char is in vacantTiles and a boardState for the upcoming decision, increase likelyhood of choosing that number for each occurance
        //System.out.println("before:"+basic);
        //System.out.println("VacantTiles: " + vacantTiles);
        for (String gameState : tempBrain) {
            for (int i = 1; i <= 9; i++) {
                //System.out.println(vacantTiles.contains(String.valueOf(i)));
                if (vacantTiles.contains(String.valueOf(i)) && gameState.contains(String.valueOf(i))) {
                    basic += i;
                }
            }
        }
        if (basic.equals("")) {
            basic = "123456789";
        }
        //System.out.println("after: "+basic);
        easy(occupiedTiles, basic);
    }

    //if possible, have an adjustable difficulty such as 1-10
    public LinkedList<String> getBrain() {
        return brain;
    }

    public int getDecision() {
        return decision;
    }
}
