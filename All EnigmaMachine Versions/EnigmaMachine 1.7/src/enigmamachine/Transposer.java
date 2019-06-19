/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmamachine;

import java.util.List;

/**
 * @version 1.7
 * @author Justin McGettigan
 */
public class Transposer {

    private List<String> settings;

    public Transposer() {

    }

    public Transposer(List<String> Settings) {
        settings = Settings;
    }

    /**
     * This method can function as a plugboard or
     * as a reflector depending on the settings
     * used.
     * @param letter This parameter is the character attempting to be substituted.
     * @return The character after the attempted substitution.
     */
    public char substitute(char letter) {
        for (String pair : settings) {
            if (pair.contains(String.valueOf(letter))) {
                for (int i = 0; i < pair.length(); i++) {
                    if (letter != pair.charAt(i)) {
                        return pair.charAt(i);
                    }
                }
            }
        }
        return letter;
    }
}
