/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmamachine;

import java.util.Collections;
import java.util.List;

/**
 * @version 1.6
 * @author Justin McGettigan
 */
public class Rotors {

    private List<List<Character>> rotors;
    private List<Character> inner, middle, outer;
    boolean isDebugging;

    public void Rotor() {

    }

    /**
     * 
     * @param rotrs
     * @param Choices
     * @param debugging 
     */
    public Rotors(List<List<Character>> rotrs, int[] Choices, boolean debugging) {
        rotors = rotrs;
        inner = rotors.get(Choices[0]);
        middle = rotors.get(Choices[1]);
        outer = rotors.get(Choices[2]);
        isDebugging = debugging;
    }

    /**
     * 
     * @param letter
     * @return 
     */
    public char innerToOuter(char letter) {
        System.out.printf((isDebugging) ? " -> innerToOuter -> " : "");
        letter = outer.get(middle.indexOf(outer.get(inner.indexOf(letter))));
        System.out.printf((isDebugging) ? letter + "\n" : "");
        return letter;
    }

    /**
     * 
     * @param letter
     * @return 
     */
    public char outerToInner(char letter) {
        System.out.printf((isDebugging) ? " -> outerToInner -> " : "");
        letter = inner.get(outer.indexOf(middle.get(outer.indexOf(letter))));
        System.out.printf((isDebugging) ? letter + "\n" : "");
        return letter;
    }

    /**
     * 
     * @param index 
     */
    public void rotate(int index) {
        System.out.printf((isDebugging) ? "\nInner Rotated\n" : "");
        Collections.rotate(inner, 1);
        if (index > 1 && index % 28 == 0) {
            System.out.printf((isDebugging) ? "\nMiddle Rotated\n" : "");
            Collections.rotate(middle, 1);
        }
        if (index > 1 && index % (28 * 28) == 0) {
            System.out.printf((isDebugging) ? "\nOuter Rotated\n" : "");
            Collections.rotate(outer, 1);
        }
    }
}
