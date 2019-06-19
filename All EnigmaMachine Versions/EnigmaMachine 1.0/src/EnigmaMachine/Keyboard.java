/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author Justin McGettigan
 */
public class Keyboard {
    
    private String message;
    private List<Character> letters;
    
    public Keyboard(){
        
    }
    
    public Keyboard(String msg){
        message = msg;
        letters = new ArrayList<>();
    }
    
    public void type(){
        message = message.toUpperCase();
        message = message.replaceAll("0", "ZERO ");
        message = message.replaceAll("1", "ONE ");
        message = message.replaceAll("2", "TWO ");
        message = message.replaceAll("3", "THREE ");
        message = message.replaceAll("4", "FOUR ");
        message = message.replaceAll("5", "FIVE ");
        message = message.replaceAll("6", "SIX ");
        message = message.replaceAll("7", "SEVEN ");
        message = message.replaceAll("8", "EIGHT ");
        message = message.replaceAll("9", "NINE ");
        message = message.replaceAll(" \\.", ".");
        for(char c : message.toCharArray()){
            letters.add(c);
        }
        //System.out.println(letters);
    }
    
    public List<Character> getLetters(){
        return letters;
    }
}
