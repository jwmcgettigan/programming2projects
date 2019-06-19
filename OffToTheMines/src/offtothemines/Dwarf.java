/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offtothemines;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Justin
 */
public class Dwarf implements Runnable {

    private String name;
    private int load;
    private Diamonds diamonds;
    private ExecutorService dwarfService;
    private int totalMined;
    private final SecureRandom generator = new SecureRandom();

    public Dwarf(String n, Diamonds d, ExecutorService dService) {
        name = n;
        diamonds = d;
        totalMined = 0;
        dwarfService = dService;
    }

    @Override
    public synchronized void run() {
        while (!diamonds.gone()) {
            leaveHome();
            travel();
            mine();
            returnHome();
        }
        kill();
    }

    public void travel() {
        int travelTime = generator.nextInt(6) + 5;
        try {
            Thread.sleep(travelTime);
        } catch (InterruptedException ex) {
        }
        System.out.println(name + " took " + travelTime + " to arrive at the mine.");
    }

    public void leaveHome() {
        System.out.println(name + " leaves the hamlet.");
    }

    public void mine() {
        if (!diamonds.gone()) {
            if (diamonds.getDiamonds() > 50) {
                load = generator.nextInt(41) + 10;
            } else if (diamonds.getDiamonds() > 10) {
                load = generator.nextInt(diamonds.getDiamonds() - 9) + 10;
            } else {
                load = generator.nextInt(diamonds.getDiamonds()) + 1;
            }
            diamonds.mineDiamonds(load);
            totalMined += load;
        } else {
            System.out.println(name + " can't find any more diamonds.");
        }
    }

    public void returnHome() {
        System.out.printf(name + " returns home");
        if (!diamonds.gone()) {
            System.out.printf(" with " + load + " diamonds.\n");
            System.out.println("There are " + diamonds.getDiamonds() + " diamonds are left.");
        } else {
            System.out.printf(".\n");
        }
    }

    public int report() {
        System.out.printf("%s collected %d. %n", name, totalMined);
        return totalMined;
    }

    public void kill() {
        dwarfService.shutdown();
        System.out.println("Mine is closing...");

        try {
            boolean tasksEnded = dwarfService.awaitTermination(1, TimeUnit.SECONDS);
            if (tasksEnded) {
                System.out.println("Mine closed");
            } else {
                System.out.println("Still talking to dwarfs...");
            }
        } catch (InterruptedException ex) {

        }

    }
}
