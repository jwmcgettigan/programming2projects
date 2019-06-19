/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.1
 * @author Justin McGettigan
 */
public class Rotor {

    private List<String> rotors;
    private int choice, startSetting, index, order;
    private char letter;

    public Rotor() {

    }

    public Rotor(int rotorChoice, int rotorStartSetting, int ind, int ordr, char ltr) {
        rotors = new ArrayList<>();
        rotors.add("AUNGHOVBIPWCJQXDKRY ELSZFMT.");
        rotors.add("O J.ETYCHMRWAFKPUZDINSXBGLQV");
        rotors.add("FBDHJLNPRTVXZ.ACEGI KMOQSUWY");
        rotors.add(".HKPDEAC WTVQMYNLXSURZOJFBGI");
        rotors.add("YDNGLCIQVEZRPTAOXWBMJSUH.K F");
        choice = rotorChoice - 1;
        startSetting = rotorStartSetting;
        letter = ltr;
        index = ind;
        order = ordr;
    }

    /*
    Find the index of the matching letter in the inner row.
    Find the index of the matching outer row in the middle row.
    Change the letter to the index for the outer row.
     */
    public void run() {
        if (index >= 0) {
            String rotor = rotors.get(choice);
            if(order == 1)
            for (int i = 0; i < rotor.length(); i++) {
                if (rotor.charAt(i) == letter) {
                    index = i;
                    break;
                }
            }
        }
    }

    public int getIndex() {
        return index;
    }

    public char getLetter() {
        return letter;
    }
}
