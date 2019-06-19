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
public class Machine {
    
    private Keyboard keyboard;
    private Plugboard plugboard;
    //private Rotor rotor;
    private Reflector reflector;
    private Lampboard lampboard;
    private Rotor[] rotors;
    
    public Machine(){
        rotors = new Rotor[3];
    }
    
    public void run(){
        settings();
    }
    
    public void input(){
        //keyboard = new Keyboard("This is a test.");
    }
    
    public void output(){
        //lampboard = new Lampboard();
    }
    
    public void settings(){
        int[] rotorChoices = {1, 2, 3}; //inner wheel, middle wheel, outer wheel
        int[] rotorStartSettings = {1, 1, 1}; //inner start, middle start, outer start
        String plugboardSettings = "DP BM NZ CK GV HQ AF UY SW JO"; //plugboard
        String reflectorSettings = "IL AP EU HO QT WZ KV GM BP NR DX CS"; //reflector
        
        //encode or decode
        boolean encode = true;
        if(encode){
            encrypt(plugboardSettings, rotorChoices, rotorStartSettings, reflectorSettings);
        }else
            decrypt();
    }
    
    public void encrypt(String plugboardSettings, int[] rotorChoices, int[] rotorStartSettings, String reflectorSettings){
        List<Character> letters = new ArrayList<>();
        keyboard = new Keyboard("This is a test. 12345.");
        keyboard.type();
        plugboard = new Plugboard(plugboardSettings, keyboard.getLetters());
        plugboard.substitute();
        for(char c : plugboard.getLetters()){
            for(int i = 0; i < rotors.length; i++){
                if(i!=0){
                    rotors[i] = new Rotor(rotorChoices[i], rotorStartSettings[i], rotors[i-1].getIndex(), (i+1), rotors[i].getLetter());
                }else{
                    rotors[i] = new Rotor(rotorChoices[i], rotorStartSettings[i], (i+1), -1, c);
                }
                rotors[i].run();
            }
            letters.add(rotors[2].getLetter());
        }
        //rotors.run();
        //plugboard = new Plugboard("This is a test.");
        
    }
    
    public void decrypt(){
        
    }
}
