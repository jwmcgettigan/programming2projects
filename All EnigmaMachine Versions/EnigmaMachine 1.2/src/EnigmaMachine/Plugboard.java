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
public class Plugboard {

    private List<String> settings;
    private List<Character> letters;

    public Plugboard() {

    }

    public Plugboard(String plugboardSettings, List<Character> ltrs) {
        settings = new ArrayList<>(Arrays.asList(plugboardSettings.split("[\\s]")));
        letters = ltrs;
    }

    public void substitute() {
        for (String plug : settings) {
            for (int k = 0; k < letters.size(); k++){
                char letter = letters.get(k);
                if (plug.contains(String.valueOf(letter))) {
                    for(int i = 0; i < plug.length(); i++){
                        if(letter != plug.charAt(i)){
                            letters.set(k, plug.charAt(i));
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
