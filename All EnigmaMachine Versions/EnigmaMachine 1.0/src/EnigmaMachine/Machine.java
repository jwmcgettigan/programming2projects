/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

/**
 * @version 1.0
 * @author Justin McGettigan
 */
public class Machine {
    
    private Keyboard keyboard;
    private Plugboard plugboard;
    private Rotors rotors;
    private Reflector reflector;
    private Lampboard lampboard;
    
    public Machine(){
        
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
        int[] rotorChoices = new int[3];
        //inner wheel
        rotorChoices[0] = 1;
        //middle wheel
        rotorChoices[1] = 2;
        //outer wheel
        rotorChoices[2] = 3;
        
        int[] rotorStartSettings = new int[3];
        //inner start
        rotorStartSettings[0] = 1;
        //middle start
        rotorStartSettings[1] = 1;
        //outer start
        rotorStartSettings[2] = 1;
        
        //plugboard
        String plugboardSettings = "DP BM NZ CK GV HQ AF UY SW JO";
        
        //reflector
        String reflectorSettings = "IL AP EU HO QT WZ KV GM BP NR DX CS";
        
        //encode or decode
        boolean encode = true;
        if(encode){
            encrypt(plugboardSettings, rotorChoices, rotorStartSettings, reflectorSettings);
        }else
            decrypt();
    }
    
    public void encrypt(String plugboardSettings, int[] rotorChoices, int[] rotorStartSettings, String reflectorSettings){
        keyboard = new Keyboard("This is a test. 12345.");
        keyboard.type();
        plugboard = new Plugboard(plugboardSettings, keyboard.getLetters());
        plugboard.substitute();
        rotors = new Rotors(rotorChoices, rotorStartSettings, plugboard.getLetters());
        rotors.run();
        //plugboard = new Plugboard("This is a test.");
        
    }
    
    public void decrypt(){
        
    }
}
