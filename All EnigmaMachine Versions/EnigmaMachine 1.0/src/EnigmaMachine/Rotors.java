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
public class Rotors {

    private List<String> rotors;
    private int[] choices, startSettings;
    private List<Character> letters;

    public Rotors() {

    }

    public Rotors(int[] rotorChoices, int[] rotorStartSettings, List<Character> ltrs) {
        rotors = new ArrayList<>();
        rotors.add("AUNGHOVBIPWCJQXDKRY ELSZFMT.");
        rotors.add("O J.ETYCHMRWAFKPUZDINSXBGLQV");
        rotors.add("FBDHJLNPRTVXZ.ACEGI KMOQSUWY");
        rotors.add(".HKPDEAC WTVQMYNLXSURZOJFBGI");
        rotors.add("YDNGLCIQVEZRPTAOXWBMJSUH.K F");
        choices = rotorChoices;
        startSettings = rotorStartSettings;
        letters = ltrs;
    }

    /*
    Find the index of the matching letter in the inner row.
    Find the index of the matching outer row in the middle row.
    Change the letter to the index for the outer row.
    */
    public void run() {
        int index = 0;
        System.out.println(letters);
        for (char letter : letters) {
            for(int i = 0; i < rotors.get(choices[0]).length(); i++){
                if(letter == rotors.get(choices[0]).charAt(i)){
                    index = i;
                }
            }
        } System.out.println(index);
        for (char letter : letters) {
            for(int i = 0; i < rotors.get(choices[1]).length(); i++){
                if(rotors.get(choices[0]).charAt(index) == rotors.get(choices[1]).charAt(i)){
                    index = i;
                }
            }
        } System.out.println(index);
        for (char letter : letters) {
            for(int i = 0; i < rotors.get(choices[2]).length(); i++){
                if(rotors.get(choices[1]).charAt(index) == rotors.get(choices[2]).charAt(i)){
                    System.out.println(rotors.get(choices[2]).charAt(i));
                    letters.set(letters.indexOf(letter), rotors.get(choices[2]).charAt(i));
                }
            }
        }
        
        //letters.set(letters.indexOf(letter), rotors.get(choices[0]).charAt(letters.indexOf(letter)));
        System.out.println(letters);
    }
}
