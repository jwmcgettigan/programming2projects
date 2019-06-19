/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

import java.util.List;

/**
 * @version 1.3
 * @author Justin McGettigan
 */
public class Transposer {

    private List<String> settings;
    private char letter;

    public Transposer() {

    }

    public Transposer(List<String> Settings, char ltr) {
        settings = Settings;
        letter = ltr;
    }

    public void substitute() {
        outer:
        for (String pair : settings) {
            if (pair.contains(String.valueOf(letter))) {
                for (int i = 0; i < pair.length(); i++) {
                    if (letter != pair.charAt(i)) {
                        letter = pair.charAt(i);
                        break outer;
                    }
                }
            }
        }
    }

    public char getLetter() {
        return letter;
    }
}
