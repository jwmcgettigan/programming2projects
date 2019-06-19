/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offtothemines;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Justin
 */
public class Mines {

    public static void main(String[] args) {
        ExecutorService dwarfService = Executors.newFixedThreadPool(7);
        Diamonds diamonds = new Diamonds();
        Dwarf sleepy = new Dwarf("Sleepy", diamonds, dwarfService);
        Dwarf happy = new Dwarf("Happy", diamonds, dwarfService);
        Dwarf doc = new Dwarf("Doc", diamonds, dwarfService);
        Dwarf sneezy = new Dwarf("Sneezy", diamonds, dwarfService);
        Dwarf dopey = new Dwarf("Dopey", diamonds, dwarfService);
        Dwarf grumpy = new Dwarf("Grumpy", diamonds, dwarfService);
        Dwarf bashful = new Dwarf("Bashful", diamonds, dwarfService);

        dwarfService.execute(grumpy);
        dwarfService.execute(bashful);
        dwarfService.execute(dopey);
        dwarfService.execute(sneezy);
        dwarfService.execute(doc);
        dwarfService.execute(happy);
        dwarfService.execute(sleepy);
        if (diamonds.gone()) {
            
            int finalTotalMined = 0;
            System.out.println("\nFinal Report");
            System.out.println("================");
            finalTotalMined += grumpy.report();
            finalTotalMined += bashful.report();
            finalTotalMined += dopey.report();
            finalTotalMined += sneezy.report();
            finalTotalMined += doc.report();
            finalTotalMined += happy.report();
            finalTotalMined += sleepy.report();
            System.out.println("Final amount mined: " + finalTotalMined);
            System.out.println("================");
        }
    }
}
/*
private int dimes;
    private ExecutorService executorService;
    private Random rand;
    private boolean leftover, running;
    
    public void runThreads(){
        
    }
    
    public MineSim(int d){
        dimes = d;
        rand = new Random();
        leftover = true;
        running = true;
        runThreads();
    }

public syncronized int diamond_collection(String s){
    if(dimes > 0 && leftover){
        if(dimes > 50){

        }
    }
}
 */
 /*
Implements (Runnable) is more efficient than the Thread class and it allows room for an extendable class.

Syncronized allows you to access one resource at a time.
 */
