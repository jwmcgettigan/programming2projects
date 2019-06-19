/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @version 1.3
 * @author Justin McGettigan
 */
public class EnigmaMachine {

    private Transposer plugboard;
    private Rotor rotor;
    private Transposer reflector;
    private Lampboard lampboard;
    private String[] chosenRotorSettings;
    private List<String> plugboardSettings, reflectorSettings;
    private int[] rotorChoices, rotorStartSettings;
    private String inner, middle, outer;
    private List<List<Character>> rotors;

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
    public EnigmaMachine() {
        plugboardSettings = new ArrayList<>();
        reflectorSettings = new ArrayList<>();
    }

    public void settings() {
        //int placeholder = 5;
        //int[] choices = {5 - 1, 2 - 1, 4 - 1};//inner wheel, middle wheel, outer wheel DAY 5
        int[] choices = {5 - 1, 2 - 1, 3 - 1};//inner wheel, middle wheel, outer wheel DAY 16
        //int[] startSettings = {23 - 1, 2 - 1, 25 - 1};//inner start, middle start, outer start DAY 5
        int[] startSettings = {8 - 1, 16 - 1, 13 - 1};//inner start, middle start, outer start DAY 16
        String plugboardSetting = "MV CL GK OQ BI FU HS PX NW EY"; //plugboard DAY 5
               plugboardSetting = "HM JO DI NR BY XZ GS PU FQ CT"; //DAY 16
        String reflectorSetting = "IL AP EU HO QT WZ KV GM YP NR DX CJ S. B "; //reflector DAY 5
               reflectorSetting = "AI BT MV HU FW EL DG KN YZ OQ CP SX J. R "; //DAY 16
        rotorChoices = choices;
        rotorStartSettings = startSettings; //inner start, middle start, outer start
        for(int i = 0; i < plugboardSetting.length(); i+=3){
            plugboardSettings.add(plugboardSetting.substring(i, i+2));
        }
        for(int i = 0; i < reflectorSetting.length(); i+=3){
            reflectorSettings.add(reflectorSetting.substring(i, i+2));
        }
        System.out.println(plugboardSettings);
        System.out.println(reflectorSettings);
        
        String[] rotorStrings = {"AUNGHOVBIPWCJQXDKRY ELSZFMT.",
            "O J.ETYCHMRWAFKPUZDINSXBGLQV",
            "FBDHJLNPRTVXZ.ACEGI KMOQSUWY",
            ".HKPDEAC WTVQMYNLXSURZOJFBGI",
            "YDNGLCIQVEZRPTAOXWBMJSUH.K F"};

        rotors = new ArrayList<>();
        List<Character> rotor;
        for (String rotorString : rotorStrings) {
            rotor = new ArrayList<>();
            for (char c : rotorString.toCharArray()) {
                rotor.add(c);
            }
            rotors.add(rotor);
        }
        for (int i = 0; i < 3; i++) {
            Collections.rotate(rotors.get(choices[i]), -startSettings[i]);
        }/*
        for(List<Character> rotr : rotors){
            System.out.println("Rotor: " + rotr);
        }*/
        //System.out.println("Rotation" + 0 + ": " + rotors.get(choices[0]));
    }

    public void run() {
        List<Character> codedMessage = new ArrayList<>();
        String codedMessageString = "";
        boolean capital;
        settings();
        //String message = "This is a test. 123.";
        String message = "JbwmYgyKpFlyrxN.TMQYLYMOXIYH";
        //String message = "AtizDbbRbEdxgeFRSQQQF.QCTUQX";
                       //"ThisAis a test. 123."
        message = message.replaceAll("0", "ZERO");
        message = message.replaceAll("1", "ONE");
        message = message.replaceAll("2", "TWO");
        message = message.replaceAll("3", "THREE");
        message = message.replaceAll("4", "FOUR");
        message = message.replaceAll("5", "FIVE");
        message = message.replaceAll("6", "SIX");
        message = message.replaceAll("7", "SEVEN");
        message = message.replaceAll("8", "EIGHT");
        message = message.replaceAll("9", "NINE");
        int index = 0;
        for (char letter : message.toCharArray()) {
            System.out.println(index);
            System.out.println("Rotation" + index + ": " + rotors.get(rotorChoices[0]));
            System.out.println("Rotation" + index + ": " + rotors.get(rotorChoices[1]));
            System.out.println("Rotation" + index + ": " + rotors.get(rotorChoices[2]));
            capital = Character.isUpperCase(letter) || letter == '.' || letter == ' ';
            letter = Character.toUpperCase(letter);
            System.out.println("Running " + letter + " through plugboard...");
            plugboard = new Transposer(plugboardSettings, letter);
            plugboard.substitute();
            System.out.println("Running " + plugboard.getLetter() + " through rotors...");
            rotor = new Rotor(rotors, rotorChoices, plugboard.getLetter());
            rotor.innerToOuter();
            System.out.println("Running " + rotor.getLetter() + " through reflector...");
            reflector = new Transposer(reflectorSettings, rotor.getLetter());
            reflector.substitute();
            System.out.println("Running " + reflector.getLetter() + " through rotors...");
            rotor = new Rotor(rotors, rotorChoices, reflector.getLetter());
            rotor.outerToInner();
            System.out.println("Running " + rotor.getLetter() + " through plugboard...");
            plugboard = new Transposer(plugboardSettings, rotor.getLetter());
            plugboard.substitute();
            new Rotor(rotors, rotorChoices, plugboard.getLetter()).rotateMe(index++);
            if (capital) {
                codedMessage.add(plugboard.getLetter());
            } else {
                codedMessage.add(Character.toLowerCase(plugboard.getLetter()));
            }
            System.out.println("Finishd " + plugboard.getLetter());
            //System.out.println();
        }
        for (char c : codedMessage) {
            codedMessageString += c;
        }
        boolean decrypting = true;
        if (decrypting) {
            codedMessageString = codedMessageString.replaceAll("ZERO", "0");
            codedMessageString = codedMessageString.replaceAll("ONE", "1");
            codedMessageString = codedMessageString.replaceAll("TWO", "2");
            codedMessageString = codedMessageString.replaceAll("THREE", "3");
            codedMessageString = codedMessageString.replaceAll("FOUR", "4");
            codedMessageString = codedMessageString.replaceAll("FIVE", "5");
            codedMessageString = codedMessageString.replaceAll("SIX", "6");
            codedMessageString = codedMessageString.replaceAll("SEVEN", "7");
            codedMessageString = codedMessageString.replaceAll("EIGHT", "8");
            codedMessageString = codedMessageString.replaceAll("NINE", "9");
        }
        System.out.println("FinalString: " + codedMessageString);
    }
}
