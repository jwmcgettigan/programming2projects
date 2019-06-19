/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.2
 * @author Justin McGettigan
 */
public class Rotors {

    private List<String> rotors;
    private int[] choices, startSettings;
    private List<Character> letters;
    private boolean decrypt;
    private int iChoice, mChoice, oChoice;
    private String inner, middle, outer;

    public Rotors() {

    }

    public Rotors(int[] rotorChoices, int[] rotorStartSettings, List<Character> ltrs, boolean dcrypt) {
        rotors = new ArrayList<>();
        rotors.add("AUNGHOVBIPWCJQXDKRY ELSZFMT.");
        rotors.add("O J.ETYCHMRWAFKPUZDINSXBGLQV");
        rotors.add("FBDHJLNPRTVXZ.ACEGI KMOQSUWY");
        rotors.add(".HKPDEAC WTVQMYNLXSURZOJFBGI");
        rotors.add("YDNGLCIQVEZRPTAOXWBMJSUH.K F");
        choices = rotorChoices;
        startSettings = rotorStartSettings;
        letters = ltrs;
        decrypt = dcrypt;
        iChoice = choices[0] - 1;
        mChoice = choices[1] - 1;
        oChoice = choices[2] - 1;
        inner = rotors.get(iChoice);
        middle = rotors.get(mChoice);
        outer = rotors.get(oChoice);
    }

    public void adjustToSettings() {
        String first, second;
        for (int choice : choices) { //adjusts the rotors to the desired starting positions
            first = rotors.get(choice - 1).substring(0, startSettings[0]);
            second = rotors.get(choice - 1).substring((startSettings[0]), (rotors.get(choice - 1).length()));
            rotors.set((choice - 1), (second + first));
            System.out.println(rotors.get(choice-1));
        }
        
        /*
        String first = rotors.get(iChoice).substring(0, startSettings[0]);
        String second = rotors.get(iChoice).substring((startSettings[0]), (rotors.get(iChoice).length()));
        rotors.set(iChoice, (second + first));*/
        //System.out.println("First: " + first);
        //System.out.println("Second: " + second);
        //System.out.println(rotors.get(iChoice));
        /*
        for(int i = 0; i < rotors.get(iChoice).length(); i++){
            
        }*/
    }

    /*
    Find the index of the matching letter in the inner row.
    Find the index of the matching outer row in the middle row.
    Change the letter to the index for the outer row.
     */
    //NEED TO IMPLEMENT STARTING SETTINGS
    public void forward() {
        int index = 0;
        for (int k = 0; k < letters.size(); k++) {
            char letter = letters.get(k);
            for (int i = 0; i < inner.length(); i++) {
                if (inner.charAt(i) == letter) {
                    index = i;
                    break;
                }
            }
            for (int i = 0; i < middle.length(); i++) {
                if (middle.charAt(i) == outer.charAt(index)) {
                    index = i;
                    break;
                }
            }
            letters.set(k, outer.charAt(index));
            if (decrypt) {

            } else {
                char last = rotors.get(iChoice).charAt(rotors.get(iChoice).length() - 1);
                rotors.set(0, last + rotors.get(iChoice).substring(0, rotors.get(iChoice).length() - 1));
                //startSettings[0]
                if (k % 27 == 0) { //may need adjusting
                    last = rotors.get(mChoice).charAt(rotors.get(mChoice).length() - 1);
                    rotors.set(1, last + rotors.get(mChoice).substring(0, rotors.get(mChoice).length() - 1));
                } else if (k % (27 * 27) == 0) { //may need adjusting
                    last = rotors.get(oChoice).charAt(rotors.get(oChoice).length() - 1);
                    rotors.set(2, last + rotors.get(oChoice).substring(0, rotors.get(oChoice).length() - 1));
                }
            }
            //System.out.println(k + ": " + rotors.get(iChoice));
            //System.out.println(k + ": " + rotors.get(mChoice));
        }
        System.out.println(letters);
    }

    public void backward() {
        int index = 0;
        //System.out.println(letters);
        for (int k = 0; k < letters.size(); k++) {
            char letter = letters.get(k);
            for (int i = 0; i < outer.length(); i++) {
                if (outer.charAt(i) == letter) {
                    index = i;
                    break;
                }
            }
            for (int i = 0; i < outer.length(); i++) {
                if (outer.charAt(i) == middle.charAt(index)) {
                    index = i;
                    break;
                }
            }
            letters.set(k, inner.charAt(index));

            if (decrypt) {

            } else {
                char last = rotors.get(iChoice).charAt(rotors.get(iChoice).length() - 1);
                rotors.set(0, last + rotors.get(iChoice).substring(0, rotors.get(iChoice).length() - 1));
                if (k % 27 == 0) { //may need adjusting
                    last = rotors.get(mChoice).charAt(rotors.get(mChoice).length() - 1);
                    rotors.set(1, last + rotors.get(mChoice).substring(0, rotors.get(mChoice).length() - 1));
                } else if (k % (27 * 27) == 0) { //may need adjusting
                    last = rotors.get(oChoice).charAt(rotors.get(oChoice).length() - 1);
                    rotors.set(2, last + rotors.get(oChoice).substring(0, rotors.get(oChoice).length() - 1));
                }
            }
        }
        System.out.println(letters);
    }

    public List<Character> getLetters() {
        return letters;
    }
}
