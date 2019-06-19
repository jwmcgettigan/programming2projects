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
public class Lampboard {

    private List<Character> letters;
    private boolean decrypt;
    private String message;

    /*
    convert word numbers back into integers
     */
    public Lampboard(List<Character> ltrs, boolean dcrpt) {
        letters = ltrs;
        decrypt = dcrpt;
        message = "";
    }

    public void display() {
        for (char letter : letters) {
            message += letter;
        }
        if (decrypt) {
            message = message.replaceAll("ZERO ", "0");
            message = message.replaceAll("ONE ", "1");
            message = message.replaceAll("TWO ", "2");
            message = message.replaceAll("THREE ", "3");
            message = message.replaceAll("FOUR ", "4");
            message = message.replaceAll("FIVE ", "5");
            message = message.replaceAll("SIX ", "6");
            message = message.replaceAll("SEVEN ", "7");
            message = message.replaceAll("EIGHT ", "8");
            message = message.replaceAll("NINE ", "9");
        }
        System.out.println(message);
    }
}
