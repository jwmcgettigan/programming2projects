/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harrypotter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Justin
 */
public class HarryPotter {

    public static void main(String[] args) {
        String dragon;
        ExecutorService dragonService = Executors.newCachedThreadPool();
        for(int i = 0; i < 1000; i++){
            dragon = "Dean" + i;
            Dragon dean = new Dragon(dragon);
            dragonService.execute(dean);
        }
        dragonService.shutdown();
    }
}
