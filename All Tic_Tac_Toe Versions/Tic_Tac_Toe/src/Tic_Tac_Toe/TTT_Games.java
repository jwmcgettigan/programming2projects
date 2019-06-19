/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

/**
 *
 * @author monster
 */
public class TTT_Games /*extends TTT*/ {
    
    public void easyGameV2(String name) { //contains everything regarding gameplay
        /*
        int humanPosition;
        int cpuPosition;
        
        boolean first = true;
        String player;
            do {
                System.out.printf("Do you want to go first or second? X or O?");
                player = input.next();
            
                if(player.equals("X") || player.equals("x")){
                    first = true;
                } else if(player.equals("O") || player.equals("o")){
                    first = false;
                } else{
                    System.out.printf("That is not a valid input.%n");
                }
            } while(player.equals("X") && player.equals("x") && player.equals("Y") && player.equals("y"));
        
        generateBoards();
        printBoards();
        
        boolean anotherTurn;
        boolean gameFinished = false;
        while (gameFinished == false) {
            do {
                System.out.printf("%n" + name + ", please enter a move (1-9): ");
                humanPosition = input.nextInt(); System.out.printf("%n");
                
                while(humanPosition < 1 || humanPosition > 9){ //if user input is not between 1-9, the user must attempt input again
                    System.out.printf("That is not a valid move " + name + ".  %nYou must enter a move (1-9): ");
                    humanPosition = input.nextInt();
                }
                if(memory.contains(humanPosition)){ //checks whether a position is occupied
                    System.out.printf("That space has already been chosen.%n");
                    System.out.printf(". . .");
                }
                if(memory.size() < 8){ //there is one move left and the game will end afterward
                    anotherTurn = memory.contains(humanPosition);
                } else { 
                    anotherTurn = false; gameFinished = true; 
                }
            } while(anotherTurn);
            do { //the computer will keep generating numbers between 1-9 until the number is both not what the human chose this turn and not something that is already occupied
                cpuPosition = cpuPosition();
                if(memory.size() < 8){
                    anotherTurn = memory.contains(cpuPosition) || cpuPosition == humanPosition;
                } else { anotherTurn = false; }
            } while(anotherTurn);
            
            if(first == true){
                if(moves(humanPosition, cpuPosition, name)){break;}
            } else {
                if(moves(cpuPosition, humanPosition, name)){break;}
            }
        }
        if(memory.size() == 9) //if the board is full and nobody won, then a draw is declared
            System.out.println("It's a draw!");
        */
    }
    
}
