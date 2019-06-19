/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hello;

import java.util.Scanner;

/**
 *
 * @author Justin McGettigan
 * date Aug 23. 2017
 */
public class Hello {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.printf("What is your name? %n");
        String name = input.next();
        System.out.printf("What is your age? %n");
        int age = input.nextInt();
        
        System.out.printf("Hello, " + name + "%n");
        System.out.printf(name + " is %d%n", age);
    }
}
