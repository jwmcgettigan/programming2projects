/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.util.Scanner;

/**
 *
 * @author Justin McGettigan
 */
public class TTT_Test {
    public static void main(String[] args) {
        TTT ttt = new TTT();
        Scanner input = new Scanner(System.in);
        
        System.out.println("Poly Tic-tac-toe has started.");
        System.out.printf("Enter a name for the Human Player: ");
        String name = input.next();
        
        ttt.startGame(name);
        
    }
}
