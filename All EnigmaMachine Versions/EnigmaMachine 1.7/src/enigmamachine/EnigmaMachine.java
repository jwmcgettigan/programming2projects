/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmamachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @version 1.7
 * @author Justin McGettigan
 */
public class EnigmaMachine {

    private Rotors rotors;
    private Transposer plugboard;
    private Transposer reflector;
    private List<String> plugboardSettings, reflectorSettings;
    private int[] rotorChoices, rotorStartSettings;
    private List<List<Character>> rotorSettings;
    private String input, output;
    private boolean encrypting;

    public EnigmaMachine(){
        
    }
    
    public EnigmaMachine(int[] choices, int[] startSettings, String plugboardSetting, String reflectorSetting, String in, boolean isEncrypting) {
        rotorChoices = choices;
        rotorStartSettings = startSettings; //inner start, middle start, outer start
        plugboardSettings = new ArrayList<>();
        for (int i = 0; i < plugboardSetting.length(); i += 3) {
            plugboardSettings.add(plugboardSetting.substring(i, i + 2));
        }
        reflectorSettings = new ArrayList<>();
        for (int i = 0; i < reflectorSetting.length(); i += 3) {
            reflectorSettings.add(reflectorSetting.substring(i, i + 2));
        }
        input = in;
        output = "";
        encrypting = isEncrypting;
    }

    /**
     * This method turns the string form of each rotor setting into lists of
     * characters and then rotates the rotors to their starting positions.
     */
    public void rotorSettings() {
        String[] rotorStrings = {"AUNGHOVBIPWCJQXDKRY ELSZFMT.", "O J.ETYCHMRWAFKPUZDINSXBGLQV", "FBDHJLNPRTVXZ.ACEGI KMOQSUWY", ".HKPDEAC WTVQMYNLXSURZOJFBGI", "YDNGLCIQVEZRPTAOXWBMJSUH.K F"};

        rotorSettings = new ArrayList<>();
        List<Character> rotor;
        for (String rotorString : rotorStrings) {
            rotor = new ArrayList<>();
            for (char c : rotorString.toCharArray()) {
                rotor.add(c);
            }
            rotorSettings.add(rotor);
        }
        for (int i = 0; i < 3; i++) {
            Collections.rotate(rotorSettings.get(rotorChoices[i]), -rotorStartSettings[i]);
        }
    }

    /**
     * This method is the actual enigma machine translating the input text
     * character by character.
     */
    public void run() {
        List<Character> changedLetters = new ArrayList<>();
        boolean capital = true;
        rotorSettings();
        convertIntegers();
        int index = 0;
        for (char letter : input.toCharArray()) {
            capital = false;
            if (String.valueOf(letter).matches("[a-zA-Z]") || letter == '.' || letter == ' ') {
                if (encrypting) {
                    capital = Character.isUpperCase(letter) || letter == '.' || letter == ' ';
                } else {
                    capital = Character.isUpperCase(letter);
                }
                letter = Character.toUpperCase(letter);
                plugboard = new Transposer(plugboardSettings);
                reflector = new Transposer(reflectorSettings);
                rotors = new Rotors(rotorSettings, rotorChoices);
                letter = plugboard.substitute(rotors.outerToInner(reflector.substitute(rotors.innerToOuter(plugboard.substitute(letter)))));
                rotors.rotate(++index);
            }
            if (capital) {
                changedLetters.add(letter);
            } else {
                changedLetters.add(Character.toLowerCase(letter));
            }
        }
        for (char changedLetter : changedLetters) {
            output += changedLetter;
        }
    }

    /**
     * This method converts integers into words.
     */
    public void convertIntegers() {
        input = input.replaceAll("0", "ZERO");
        input = input.replaceAll("1", "ONE");
        input = input.replaceAll("2", "TWO");
        input = input.replaceAll("3", "THREE");
        input = input.replaceAll("4", "FOUR");
        input = input.replaceAll("5", "FIVE");
        input = input.replaceAll("6", "SIX");
        input = input.replaceAll("7", "SEVEN");
        input = input.replaceAll("8", "EIGHT");
        input = input.replaceAll("9", "NINE");
    }

    public String getOutput() {
        return output;
    }
}

/*
    Read from file.
    Turn into arraylist of characters.
    Have each character go through enigmaMachine
        -plugboard
        -innerToOuter
        -reflector
        -outerToInner
        -plugboard
        -rotate appropriate rotors
    Turn encrypted arraylist of characters into a single string.
 */
