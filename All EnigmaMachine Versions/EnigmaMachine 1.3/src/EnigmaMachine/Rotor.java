/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.3
 * @author Justin McGettigan
 */
public class Rotor {

    private List<List<Character>> rotors;
    private int[] choices;
    private int index;
    private char letter;
    private List<Character> inner, middle, outer;

    public void Rotor() {

    }

    public Rotor(List<List<Character>> rotrs, int[] Choices, char ltr) {
        rotors = rotrs;
        choices = Choices;
        inner = rotors.get(choices[0]);
        middle = rotors.get(choices[1]);
        outer = rotors.get(choices[2]);
        letter = ltr;
    }

    public void innerToOuter() {
        letter = outer.get(middle.indexOf(outer.get(inner.indexOf(letter))));
    }

    public void outerToInner() {
        letter = inner.get(outer.indexOf(middle.get(outer.indexOf(letter))));
    }

    public void rotateMe(int index) {
        Collections.rotate(rotors.get(choices[0]), 1);
        if (index % 27 == 0) {
            Collections.rotate(rotors.get(choices[1]), 1);
        } else if (index % (27 * 27) == 0) {
            Collections.rotate(rotors.get(choices[2]), 1);
        }
    }

    public char getLetter() {
        return letter;
    }
}
