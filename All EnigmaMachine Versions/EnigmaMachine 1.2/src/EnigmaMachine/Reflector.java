/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.2
 * @author Justin McGettigan
 */
public class Reflector {
    
    private List<String> settings;
    private List<Character> letters;
    
    public Reflector(String reflectorSettings, List<Character> ltrs){
        settings = new ArrayList<>(Arrays.asList(reflectorSettings.split("[\\s]")));
        letters = ltrs;
    }
    
    public void substitute(){
        for (String reflection : settings) {
            for (int k = 0; k < letters.size(); k++){
                char letter = letters.get(k);
                if (reflection.contains(String.valueOf(letter))) {
                    for(int i = 0; i < reflection.length(); i++){
                        if(letter != reflection.charAt(i)){
                            letters.set(k, reflection.charAt(i));
                        }
                    }
                }
            }
        }
        System.out.println(letters);
    }
    
    public List<Character> getLetters(){
        return letters;
    }
}
