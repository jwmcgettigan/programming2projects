/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnigmaMachine;

/**
 * @version 1.3
 * @author Justin McGettigan
 */
public class Machine {
    
    private Transposer plugboard;
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
        int[] rotorChoices = {1, 2, 3}; //inner wheel, middle wheel, outer wheel
        int placeholder = 5;
        int[] rotorStartSettings = {placeholder-1, placeholder-1, placeholder-1}; //inner start, middle start, outer start
        String plugboardSettings = "DP BM NZ CK GV HQ AF UY SW JO"; //plugboard
        String reflectorSettings = "IL AP EU HO QT WZ KV GM BP NR DX CS"; //reflector
        
        //encode or decode
        boolean encode = true;
        if(encode){
            encrypt(plugboardSettings, rotorChoices, rotorStartSettings, reflectorSettings);
        }else
            decrypt(plugboardSettings, rotorChoices, rotorStartSettings, reflectorSettings);
    }
    
    public void encrypt(String plugboardSettings, int[] rotorChoices, int[] rotorStartSettings, String reflectorSettings){
        String message = "This is a test. 123.";
        //keyboard = new Keyboard(message);
        System.out.println("Typing... " + message);
        //keyboard.type();
       // plugboard = new Transposer(plugboardSettings, keyboard.getLetters());
        System.out.println("Running through plugboard...");
        plugboard.substitute();
        //rotors = new Rotors(rotorChoices, rotorStartSettings, plugboard.getLetters(), false);
        System.out.println("Adjusting rotor settings...");
        rotors.adjustToSettings();
        System.out.println("Running through rotors fowards...");
        rotors.forward();
        reflector = new Reflector(reflectorSettings, rotors.getLetters());
        System.out.println("Running through reflector...");
        reflector.substitute();
        System.out.println("Running through rotors backwards...");
        rotors.backward();
        System.out.println("Running through plugboard...");
        plugboard.substitute();
        System.out.println("Displaying through lampboard...");
        //lampboard = new Lampboard(plugboard.getLetters(), false);
        lampboard.display();
    }
    
    public void decrypt(String plugboardSettings, int[] rotorChoices, int[] rotorStartSettings, String reflectorSettings){
        String message = "EQFVMFVMJMEIVEBMCGIMELCMEQTIIB";
        //keyboard = new Keyboard(message);
        System.out.println("Typing... " + message);
        //keyboard.type();
        //plugboard = new Transposer(plugboardSettings, keyboard.getLetters());
        System.out.println("Running through plugboard...");
        plugboard.substitute();
        //rotors = new Rotors(rotorChoices, rotorStartSettings, plugboard.getLetters(), true);
        System.out.println("Running through rotors backwards...");
        rotors.backward();
        reflector = new Reflector(reflectorSettings, rotors.getLetters());
        System.out.println("Running through reflector...");
        reflector.substitute();
        System.out.println("Running through rotors fowards...");
        rotors.forward();
        System.out.println("Running through plugboard...");
        plugboard.substitute();
        System.out.println("Displaying through lampboard...");
       // lampboard = new Lampboard(plugboard.getLetters(), true);
        lampboard.display();
    }
}
