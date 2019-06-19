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
 * @version 1.5
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
    private boolean encrypting, decrypting;

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
    public EnigmaMachine(int[] choices, int[] startSettings, String plugboardSetting, String reflectorSetting, String msg, boolean isEncrypting) {
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
        decrypting = !encrypting;
    }

    public void rotorSettings() {
        String[] rotorStrings
                = {"AUNGHOVBIPWCJQXDKRY ELSZFMT.",
                    "O J.ETYCHMRWAFKPUZDINSXBGLQV",
                    "FBDHJLNPRTVXZ.ACEGI KMOQSUWY",
                    ".HKPDEAC WTVQMYNLXSURZOJFBGI",
                    "YDNGLCIQVEZRPTAOXWBMJSUH.K F"};

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

    public void run() {
        List<Character> changedLetters = new ArrayList<>();
        boolean capital;
        rotorSettings();
        if(encrypting){
            encrypt();
        }
        int index = 0;
        for (char letter : before.toCharArray()) {/*
            System.out.println(index);
            System.out.println("Rotation" + index + ": " + rotorSettings.get(rotorChoices[0]));
            System.out.println("Rotation" + index + ": " + rotorSettings.get(rotorChoices[1]));
            System.out.println("Rotation" + index + ": " + rotorSettings.get(rotorChoices[2]));
            //===========================================================================*/
            capital = Character.isUpperCase(letter) || letter == '.' || letter == ' ';         //TAKE INTO ACCOUNT DECRYPTING
            letter = Character.toUpperCase(letter);
            //---------------------------------------------------------------------------
            //System.out.println("Running " + letter + " through plugboard...");
            plugboard = new Transposer(plugboardSettings, letter);
            plugboard.substitute();
            //---------------------------------------------------------------------------
            //System.out.println("Running " + plugboard.getLetter() + " through rotors...");
            rotors = new Rotors(rotorSettings, rotorChoices, plugboard.getLetter());
            rotors.innerToOuter();
            //---------------------------------------------------------------------------
            //System.out.println("Running " + rotors.getLetter() + " through reflector...");
            reflector = new Transposer(reflectorSettings, rotors.getLetter());
            reflector.substitute();
            //---------------------------------------------------------------------------
            //System.out.println("Running " + reflector.getLetter() + " through rotors...");
            rotors = new Rotors(rotorSettings, rotorChoices, reflector.getLetter());
            rotors.outerToInner();
            //---------------------------------------------------------------------------
            //System.out.println("Running " + rotors.getLetter() + " through plugboard...");
            plugboard = new Transposer(plugboardSettings, rotors.getLetter());
            plugboard.substitute();
            //---------------------------------------------------------------------------
            new Rotors(rotorSettings, rotorChoices, plugboard.getLetter()).rotate(index++);
            //---------------------------------------------------------------------------
            if (capital) {
                changedLetters.add(plugboard.getLetter());
            } else {
                changedLetters.add(Character.toLowerCase(plugboard.getLetter()));
            }
            //===========================================================================
            //System.out.println("Finishd " + plugboard.getLetter());
        }
        for (char changedLetter : changedLetters) {
            after += changedLetter;
        }
        if (decrypting) {
            decrypt();
            System.out.println("Decryption: " + after);
        } else{
            System.out.println("Encryption: " + after);
        }
    }

    public void encrypt() {
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
    }

    public void decrypt() {
        after = after.replaceAll("ZERO", "0");
        after = after.replaceAll("ONE", "1");
        after = after.replaceAll("TWO", "2");
        after = after.replaceAll("THREE", "3");
        after = after.replaceAll("FOUR", "4");
        after = after.replaceAll("FIVE", "5");
        after = after.replaceAll("SIX", "6");
        after = after.replaceAll("SEVEN", "7");
        after = after.replaceAll("EIGHT", "8");
        after = after.replaceAll("NINE", "9");
    }
}
