/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.security.SecureRandom;
//import java.util.LinkedList;

/**
 * @project Tic Tac Toe 2.4
 * @author Justin McGettigan
 */
public class Computer {

    private SecureRandom rand;
    //private LinkedList<String> brain;
    private int decision;

    public Computer() {
        //brain = new LinkedList<>();
    }

    /**
     * This method uses data from past games to determine the decision that will
     * most likely lead to victory and when unable to draw upon past games, the
     * decision will be random; This method also verifies the decision.
     *
     * @param occupiedTiles This parameter is the list of tiles that are
     * currently occupied as well as a record of all decisions made by players
     * for the current game.
     * @param vacantTiles This parameter is the list of tiles that are currently empty.
     * @param gameNum This parameter is a number that indicates how many games have been played.
     */
    public void decide(String occupiedTiles, String vacantTiles, int gameNum, int[][] values, int[][] coords, int orderNum, int difficulty) {
        String basic = "123456789";
        //LinkedList<String> tempBrain = brain;

        if (difficulty == 1) { //easy
            easy(occupiedTiles, basic, gameNum);
        } else if (difficulty == 2) {
            normal(occupiedTiles, basic, values, coords, orderNum, gameNum);
        } else if (difficulty == 3) {
            //this would have been the 'impossible' difficulty but I ended up giving up on this after trying way to hard
            //to get the machine learning working and then before i knew it I was out of time to figure out the non-machine learning method
        }
    }
    
    /**
     * This method has the bot randomly choose a tile.
     * @param occupiedTiles This parameter is the list of tiles that are
     * currently occupied as well as a record of all decisions made by players
     * for the current game.
     * @param basic This parameter is the set of tiles to choose from by default.
     * @param gameNum This parameter is a number that indicates how many games have been played.
     */
    public void easy(String occupiedTiles, String basic, int gameNum) {
        rand = new SecureRandom();
        for (int i = 0; i < occupiedTiles.length(); i++) {
            if (basic.contains(String.valueOf(occupiedTiles.charAt(i)))) {
                basic = basic.replace(String.valueOf(occupiedTiles.charAt(i)), "");
            }
        }
        decision = Character.getNumericValue(basic.charAt(rand.nextInt(basic.length())));
    }
    
    /**
     * This method has the bot make slightly intelligent decisions when choosing a tile.
     * @param occupiedTiles This parameter is the list of tiles that are
     * currently occupied as well as a record of all decisions made by players
     * for the current game.
     * @param basic This parameter is the set of tiles to choose from by default.
     * @param values This parameter contains the values for each tile.
     * @param coords This parameter contains the coordinates for the tiles.
     * @param orderNum This parameter determines player 1 and player 2.
     * @param gameNum This parameter is a number that indicates how many games have been played.
     */
    public void normal(String occupiedTiles, String basic, int[][] values, int[][] coords, int orderNum, int gameNum) {
        int[] val = new int[10];
        boolean[] winningTile = new boolean[10];
        int num;
        boolean first;
        
        for (int r = 0; r <= 2; r++) {
            for (int c = 0, k = 2; c <= 2; c++, k--) {
                val[coords[r][c]] = values[r][c];
            }
        }
        
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
    
    /*
    public LinkedList<String> getBrain() {
        return brain;
    }*/

    public int getDecision() {
        return decision;
    }
}
