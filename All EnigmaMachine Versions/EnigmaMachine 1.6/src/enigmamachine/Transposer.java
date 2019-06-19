/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmamachine;

import java.util.List;

/**
 * @version 1.6
 * @author Justin McGettigan
 */
public class Transposer {

    private List<String> settings;
    private boolean isDebugging;

    public Transposer() {

    }

    public Transposer(List<String> Settings, boolean debugging) {
        settings = Settings;
        isDebugging = debugging;
    }

    /**
     * 
     * @param letter
     * @return 
     */
    public char substitute(char letter) {
        System.out.printf((isDebugging) ? letter + ((settings.size() == 10) ? " -> plugboard -> " : " -> reflector -> ") : "");
        outer:
        for (String pair : settings) {
            if (pair.contains(String.valueOf(letter))) {
                for (int i = 0; i < pair.length(); i++) {
                    if (letter != pair.charAt(i)) {
                        System.out.printf((isDebugging) ? String.valueOf(pair.charAt(i)) : "");
                        return pair.charAt(i);
                    }
                }
            }
        }
        System.out.printf((isDebugging) ? String.valueOf(letter) : "");
        return letter;
    }
}
