/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.util.Scanner;

/**
 *
 * @author monster
 */
public class Interface {
    
    private Scanner input;
    private Data data;
    private Analysis analyze;
    
    public Interface(){
        input = new Scanner(System.in);
        data = new Data();
        analyze = new Analysis();
    }
    
    public void printSignature() {
        //data.wordLengths();
        data.linesCombined();
        data.words();
        System.out.println();
        data.sentences();
        
        //System.out.printf("%nNumber of words: " + data.numWords() + "%n");
        //System.out.printf("Authors  |  # of Books  | a1 | a2 | a3 | a4 | a5%n");
        //System.out.printf("");
    }
    
    public void prompt() {
        String author, text;
        Boolean resume = true;
        //System.out.printf("Author? ");
        //author = input.next();
        while (resume) {
            //System.out.printf("Text? ");
            //text = input.next();
            text = "Great Expectations.txt";
            data.readText(text);
            resume = false;
            /*
            if(text.equals("STOP")){
                resume = false;
            }*/
        }
    }
}
