/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DwarfMine;

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elgar72
 */
public class Dwarf implements Runnable {
    
    private String name;
    private MineSim mine;
    private int numMined;
    private final SecureRandom generator;
    
    
    public Dwarf(MineSim m, String n) {
        mine = m;
        name = n;
        numMined = 0;
        generator = new SecureRandom();
    }
    
    
    public void run() {
        while (mine.isLeftOver()) {
            try {
                System.out.println(name + " is going to the mine");
                Thread.sleep(generator.nextInt(1)+5);
                System.out.println(name + " has arrived at the mine");
                numMined += mine.diamonds_collection(name);
                System.out.println(name + " is going to sleep");
                Thread.sleep(generator.nextInt(1)+10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dwarf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (mine.isRunning()) {
            mine.kill();
        }
    }
    
    public int report() {
        System.out.printf("%s collected %d mines %n",name, numMined);
        return(numMined);
    }
}
