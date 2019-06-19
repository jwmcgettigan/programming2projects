/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolarSystem;

import java.util.Scanner;

/**
 *
 * @author Justin McGettigan
 */
public class PlanetTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.printf("What is your username?%n");
        String username = input.next();
        
        System.out.printf("%nWelcome " + username + "! Please answer a couple questions and your planet will be registered in no time.%n");
        
        System.out.printf("What is the radius of your planet (km)?%n");
        double radius = input.nextDouble();
        
        System.out.printf("What is the color of your planet?%n");
        String color = input.next();
        
        System.out.printf("What is the name of your planet?%n");
        String name = input.next();
        
        Planet planet = new Planet(radius, color, name);
        
        System.out.printf("%nYour Planetary Statistics %n");
        System.out.printf("=========================== %n");
        System.out.printf("Name: " + name + "%n");
        System.out.printf("Radius: " + radius + " km%n");
        System.out.printf("Color: " + color + "%n");
        System.out.printf("Area: " + planet.getArea() + " km%n");
    }
}
