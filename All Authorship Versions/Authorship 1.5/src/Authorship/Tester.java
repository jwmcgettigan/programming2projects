/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.util.Scanner;

/**
 * @project Authorship 1.5
 * @author Justin McGettigan
 */
public class Tester {

    /**
     * This method runs the program and prompts the user for if they would like
     * to restart the program.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Library library;
        String choice;
        boolean restart = true, chooseAgain = true, extraCredit = true;
        //System.out.printf("Welcome %n");
        System.out.printf("Include experimental extra credit features? (y/n): ");
        while (chooseAgain) {
            choice = input.nextLine();
            if (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")) {
                System.out.println("Please enter a valid input (y/n): ");
            } else {
                if (choice.equalsIgnoreCase("Y")) {
                    System.out.printf("Disclamer: Common word frequency may not be correct.%n%n");
                    extraCredit = true;
                } else {
                    extraCredit = false;
                }
                chooseAgain = false;
            }
        }
        while (restart) {
            library = new Library(extraCredit);
            library.run();
            chooseAgain = true;
            System.out.printf("%nWould you like to restart the program? (y/n): ");
            while (chooseAgain) {
                choice = input.nextLine();
                if (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")) {
                    System.out.println("Please enter a valid input (y/n): ");
                } else {
                    if (choice.equalsIgnoreCase("Y")) {
                        System.out.printf("%n- - - - - - - - - - - - - - - - - - - - - - - - -%n");
                        restart = true;
                    } else {
                        System.out.println("Goodbye! Have a nice day!");
                        restart = false;
                    }
                    chooseAgain = false;
                }
            }
        }

    }
}
/*

String bookTitle;

String DELIMITER = "(\\s+|\\s*,\\t+|\\n)"; //for splitting tokens
String punc = "\\p{Punct}+";   //punctuation only


 */
