/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmamachine;

import java.util.Collections;
import java.util.List;

/**
 * @version 1.7
 * @author Justin McGettigan
 */
public class Rotors {

    private List<List<Character>> rotors;
    private List<Character> inner, middle, outer;

    public Rotors() {

    }

    public Rotors(List<List<Character>> rotrs, int[] Choices) {
        rotors = rotrs;
        inner = rotors.get(Choices[0]);
        middle = rotors.get(Choices[1]);
        outer = rotors.get(Choices[2]);
    }

    /**
     * This method utilizes the inner-outer-middle-outer
     * cipher wheel encryption method.
     * @param letter This parameter is the character being translated.
     * @return The character after translation.
     */
    public char innerToOuter(char letter) {
        return outer.get(middle.indexOf(outer.get(inner.indexOf(letter))));
    }

    /**
     * This method utilizes the outer-middle-outer-inner
     * cipher wheel encryption method.
     * @param letter This parameter is the character being translated.
     * @return The character after translation.
     */
    public char outerToInner(char letter) {
        return inner.get(outer.indexOf(middle.get(outer.indexOf(letter))));
    }

    /**
     * This method will rotate the rotors
     * in the correct intervals.
     * @param index This parameter is the number of times the inner rotor has rotated.
     */
    public void rotate(int index) {
        Collections.rotate(inner, 1);
        if (index > 1 && index % 28 == 0) {
            Collections.rotate(middle, 1);
        }
        if (index > 1 && index % (28 * 28) == 0) {
            Collections.rotate(outer, 1);
        }
    }
}
