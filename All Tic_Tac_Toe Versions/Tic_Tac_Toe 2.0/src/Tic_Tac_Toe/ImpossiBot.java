/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author monster
 */
public class ImpossiBot {

    private ArrayList<Integer> bot1Memory, bot2Memory;
    private ArrayList<Integer> bot1Turn1, bot1Turn2, bot1Turn3, bot1Turn4, bot1Turn5, bot2Turn1, bot2Turn2, bot2Turn3, bot2Turn4;
    private int Bot1Turn1Choice, Bot1Turn2Choice, Bot1Turn3Choice, Bot1Turn4Choice, Bot1Turn5Choice,
                Bot2Turn1Choice, Bot2Turn2Choice, Bot2Turn3Choice, Bot2Turn4Choice;
    private Computer mainBot;

    public ImpossiBot() {
        bot1Memory = new ArrayList<>();
        bot2Memory = new ArrayList<>();
        mainBot = new Computer();
    }

    public void impossiBot() {

        //needs to play against itself 10,000 times
        //take the list of moves made by the winner each time
        //and add those numbers to an arraylist for each turn (4 turns for bot 2, and 5 turns for bot 1)
        //during the next game, a number in the arraylist is randomly chosen
        //at each board state
        //how to keep/save the intelligence
        //how to utilize intelligence against the player
        
        //--------think about how to revise the bot1-5turns and bot2-4 turns method into the generalBot-9 turns method-------------------------
    }

    public void bot1Choice() {
        Bot1Turn1Choice = bot1Turn1.get(new Random().nextInt(bot1Turn1.size()));

    }

    public void botMemories() { //only lasts until recorded at end of game
        if(turn 1){
            bot1Memory.add(Bot1Turn1Choice);
        }
        
        bot2Memory.add();
    }

    public void recordWinningMoves() {
        bot1Turn1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        bot1Turn2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        bot1Turn3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        bot1Turn4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        bot1Turn5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        bot2Turn1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        bot2Turn2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        bot2Turn3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        bot2Turn4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<ArrayList<Integer>> bot1, bot2;

        bot1Turn1.add(bot1Memory.get(0));
        bot1Turn2.add(bot1Memory.get(1));
        bot1Turn3.add(bot1Memory.get(2));
        bot1Turn4.add(bot1Memory.get(3));
        bot1Turn5.add(bot1Memory.get(4));

        bot2Turn1.add(bot2Memory.get(0));
        bot2Turn2.add(bot2Memory.get(1));
        bot2Turn3.add(bot2Memory.get(2));
        bot2Turn4.add(bot2Memory.get(3));

        bot1 = new ArrayList<>(Arrays.asList(bot1Turn1, bot1Turn2, bot1Turn3, bot1Turn4, bot1Turn5));
        bot2 = new ArrayList<>(Arrays.asList(bot2Turn1, bot2Turn2, bot2Turn3, bot2Turn4));
    }
}
