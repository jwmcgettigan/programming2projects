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
 * @version 1.6
 * @author Justin McGettigan
 */
public class EnigmaMachine {

    private Rotors rotors;
    private Transposer plugboard;
    private Transposer reflector;
    private List<String> plugboardSettings, reflectorSettings;
    private int[] rotorChoices, rotorStartSettings;
    private List<List<Character>> rotorSettings;
    private String before, after;
    private boolean encrypting, isDebugging;

    public EnigmaMachine(int[] choices, int[] startSettings, String plugboardSetting, String reflectorSetting, String msg, boolean isEncrypting, boolean debugging) {
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
        before = msg;
        after = "";
        encrypting = isEncrypting;
        isDebugging = debugging;
    }

    /**
     * 
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
     * 
     */
    public void run() {
        List<Character> changedLetters = new ArrayList<>();
        boolean capital = true;
        rotorSettings();
        decode();
        int index = 0;
        for (char letter : before.toCharArray()) {
            capital = false;
            if (isDebugging) {
                System.out.println(index);
                System.out.println("Rotation" + index + ": " + rotorSettings.get(rotorChoices[0]));
                System.out.println("Rotation" + index + ": " + rotorSettings.get(rotorChoices[1]));
                System.out.println("Rotation" + index + ": " + rotorSettings.get(rotorChoices[2]));
            }
            if (String.valueOf(letter).matches("[\\w]") || letter == '.' || letter == ' ') {
                //---------------------------------------------------------------------------
                if (encrypting) {
                    capital = Character.isUpperCase(letter) || letter == '.' || letter == ' ';
                } else {
                    capital = Character.isUpperCase(letter);
                }
                letter = Character.toUpperCase(letter);
                plugboard = new Transposer(plugboardSettings, isDebugging);
                reflector = new Transposer(reflectorSettings, isDebugging);
                rotors = new Rotors(rotorSettings, rotorChoices, isDebugging);
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
            after += changedLetter;
        }
        decode();
    }

    /**
     * 
     */
    public void decode() {
        if (encrypting) {
            before = before.replaceAll("0", "ZERO");
            before = before.replaceAll("1", "ONE");
            before = before.replaceAll("2", "TWO");
            before = before.replaceAll("3", "THREE");
            before = before.replaceAll("4", "FOUR");
            before = before.replaceAll("5", "FIVE");
            before = before.replaceAll("6", "SIX");
            before = before.replaceAll("7", "SEVEN");
            before = before.replaceAll("8", "EIGHT");
            before = before.replaceAll("9", "NINE");
        } else {/*
            after = after.replaceAll(" "+"(?i)ZERO", "0");
            after = after.replaceAll("(?i)ONE", "1");
            after = after.replaceAll("(?i)TWO", "2");
            after = after.replaceAll("(?i)THREE", "3");
            after = after.replaceAll("(?i)FOUR", "4");
            after = after.replaceAll("(?i)FIVE", "5");
            after = after.replaceAll("(?i)SIX", "6");
            after = after.replaceAll("(?i)SEVEN", "7");
            after = after.replaceAll("(?i)EIGHT", "8");
            after = after.replaceAll("(?i)NINE", "9");*/
        }
    }

    /**
     * 
     * @return 
     */
    public String getDecoded() {
        return after;
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
