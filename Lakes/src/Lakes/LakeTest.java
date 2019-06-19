/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lakes;

/**
 *
 * @author Justin McGettigan 
 * date Aug 23. 2017
 */
public class LakeTest {

    public static void main(String[] args) {
        Lake myLake = new Lake();
        Lake mySecondLake = new Lake("Eerie", -3);

        System.out.printf("Lake statistics %n");
        System.out.printf(mySecondLake.getName() + "%n");
        if (mySecondLake.isCold()) {
            System.out.printf("It's damn cold %n");
        } else {
            System.out.printf("swimming temperature %n");
        }

    }
}
