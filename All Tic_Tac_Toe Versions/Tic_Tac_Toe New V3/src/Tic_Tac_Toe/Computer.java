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
    public void decide(String occupiedTiles, String vacantTiles, int gameNum, int[][] values, int[][] coords, int orderNum) {
        String basic = "123456789";
        LinkedList<String> tempBrain = brain;
        int difficulty = 3;

        if (difficulty == 1) { //easy
            easy(occupiedTiles, basic, gameNum);
        } else if (difficulty == 2) {
            normal(occupiedTiles, basic, values, coords, orderNum, gameNum);
        } else if (difficulty == 3) {
            impossible(occupiedTiles, basic, values, coords, orderNum, gameNum);
            //impossibleTest1(occupiedTiles, tempBrain);
            //impossibleTest2(occupiedTiles, vacantTiles, tempBrain);
        }
    }

    public void easy(String occupiedTiles, String basic, int gameNum) {
        rand = new SecureRandom();
        for (int i = 0; i < occupiedTiles.length(); i++) {
            if (basic.contains(String.valueOf(occupiedTiles.charAt(i)))) {
                basic = basic.replace(String.valueOf(occupiedTiles.charAt(i)), "");
            }
        }
        decision = Character.getNumericValue(basic.charAt(rand.nextInt(basic.length())));
    }

    public void normal(String occupiedTiles, String basic, int[][] values, int[][] coords, int orderNum, int gameNum) {
        int[] val = new int[10];
        boolean[] winningTile = new boolean[10];
        for (int r = 0; r <= 2; r++) {
            for (int c = 0, k = 2; c <= 2; c++, k--) {
                val[coords[r][c]] = values[r][c];
            }
        }
        int num;
        boolean first;
        if (orderNum == 1) {
            first = true;
            num = 2;
        } else {
            first = false;
            num = 10;
        }
        while (num > 1 && num < 11) {
            winningTile[1] = val[1] == 0 && val[2] + val[3] == num || val[5] + val[9] == num || val[4] + val[7] == num;
            winningTile[2] = val[2] == 0 && val[1] + val[3] == num || val[5] + val[8] == num;
            winningTile[3] = val[3] == 0 && val[1] + val[2] == num || val[7] + val[5] == num || val[9] + val[6] == num;
            winningTile[4] = val[4] == 0 && val[1] + val[7] == num || val[5] + val[6] == num;
            winningTile[5] = val[5] == 0 && val[4] + val[6] == num || val[2] + val[8] == num;
            winningTile[6] = val[6] == 0 && val[4] + val[5] == num || val[3] + val[9] == num;
            winningTile[7] = val[7] == 0 && val[1] + val[4] == num || val[5] + val[3] == num || val[8] + val[9] == num;
            winningTile[8] = val[8] == 0 && val[7] + val[9] == num || val[2] + val[5] == num;
            winningTile[9] = val[9] == 0 && val[7] + val[8] == num || val[1] + val[5] == num || val[3] + val[6] == num;

            if (winningTile[1]) {
                decision = 1;
            } else if (winningTile[2]) {
                decision = 2;
            } else if (winningTile[3]) {
                decision = 3;
            } else if (winningTile[4]) {
                decision = 4;
            } else if (winningTile[5]) {
                decision = 5;
            } else if (winningTile[6]) {
                decision = 6;
            } else if (winningTile[7]) {
                decision = 7;
            } else if (winningTile[8]) {
                decision = 8;
            } else if (winningTile[9]) {
                decision = 9;
            } else if (val[5] == 0) {
                decision = 5;
            } else {
                easy(occupiedTiles, basic, gameNum);
            }
            if (first) {
                num += 8;
            } else {
                num -= 8;
            }
        }
    }

    public void impossible(String occupiedTiles, String basic, int[][] values, int[][] coords, int orderNum, int gameNum) {
        int[] val = new int[10], line = new int[22];
        boolean[] winningTile = new boolean[10];

        for (int r = 0; r <= 2; r++) {
            for (int c = 0, k = 2; c <= 2; c++, k--) {
                val[coords[r][c]] = values[r][c];
            }
        }
        int num;
        boolean first;
        if (orderNum == 1) {
            first = true;
            num = 2;
        } else {
            first = false;
            num = 10;
        }
        
        /*
        1-2,1-3,1-4,1-5,1-7
        2-3,2-5,2-8
        3-6,3-9
        4-5,4-6,4-7
        5-3,5-6,5-8,5-9
        6
        7-5,7-8,7-9
        8-9
        9-6
        */
        
        
        line[0] = val[2] + val[3];
        line[1] = val[5] + val[9];
        line[2] = val[4] + val[7];
        line[3] = val[1] + val[3];
        line[4] = val[5] + val[8];
        line[5] = val[1] + val[2];
        line[6] = val[7] + val[5];
        line[7] = val[9] + val[6];
        line[8] = val[1] + val[7];
        line[9] = val[5] + val[6];
        line[10] = val[4] + val[6];
        line[11] = val[2] + val[8];
        line[12] = val[4] + val[5];
        line[13] = val[3] + val[9];
        line[14] = val[1] + val[4];
        line[15] = val[5] + val[3];
        line[16] = val[8] + val[9];
        line[17] = val[7] + val[9];
        line[18] = val[2] + val[5];
        line[19] = val[7] + val[8];
        line[20] = val[1] + val[5];
        line[21] = val[3] + val[6];

        while (num > 1 && num < 11 && decision != Integer.valueOf(occupiedTiles.charAt(occupiedTiles.length()-1))) {
            winningTile[1] = val[1] == 0 && line[0] == num || line[1] == num || line[2] == num;
            winningTile[2] = val[2] == 0 && line[3] == num || line[4] == num;
            winningTile[3] = val[3] == 0 && line[5] == num || line[6] == num || line[7] == num;
            winningTile[4] = val[4] == 0 && line[8] == num || line[9] == num;
            winningTile[5] = val[5] == 0 && line[10] == num || line[11] == num;
            winningTile[6] = val[6] == 0 && line[12] == num || line[13] == num;
            winningTile[7] = val[7] == 0 && line[14] == num || line[15] == num || line[16] == num;
            winningTile[8] = val[8] == 0 && line[17] == num || line[18] == num;
            winningTile[9] = val[9] == 0 && line[19] == num || line[20] == num || line[21] == num;
            /*
            int trueCount = 0;
            for(int i = 1; i < 10; i++){
                if(winningTile[i]){
                    trueCount++;
                }
            }*/
            int tempNum, trueCount;
            boolean test = false;
            outer:
            for (int i = 0; i < 10; i++) {
                tempNum = val[i];
                if (first) {
                    val[i] = 1;
                } else {
                    val[i] = 5;
                }
                for (int k = 1; k < 10; k++) {
                    trueCount = 0;/*
                    for (int p = 1; i < 10; p++) {
                        if (winningTile[p]) {
                            trueCount++;
                        }
                    }*/
                    if (winningTile[k]) {
                        decision = k;
                        test = true;
                        break outer;
                    } else if (trueCount > 1) {

                    }

                }
                val[i] = tempNum;
            }
            if (val[5] == 0 && test == false) {
                decision = 5;
            } else if (test == false) {
                easy(occupiedTiles, basic, gameNum);
            }
            if (first) {
                num += 8;
            } else {
                num -= 8;
            }
        }
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
            //easy(occupiedTiles, basic);
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
        //easy(occupiedTiles, basic);
    }

    //if possible, have an adjustable difficulty such as 1-10
    public LinkedList<String> getBrain() {
        return brain;
    }

    public int getDecision() {
        return decision;
    }
}
