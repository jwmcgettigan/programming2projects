/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rainbows;

import java.util.ArrayList;

/**
 *
 * @author Justin McGettigan
 */
public class Rainbows {

    public static void main(String[] args) {
        ArrayList<String> rainbowColors = new ArrayList<>();

        //add colors
        rainbowColors.add("Red");
        rainbowColors.add("Blue");
        rainbowColors.add("Yellow");
        rainbowColors.add("Orange");
        
        System.out.println("Size = " + rainbowColors.size());
        
        rainbowColors.remove(1);
        rainbowColors.add("Blue");
        
        String temp = rainbowColors.get(1);
        System.out.println("Temp" + temp);
        rainbowColors.set(1, rainbowColors.get(2));
        rainbowColors.set(2, temp);
        
        rainbowColors.clear();
        
        //print method
        printList(rainbowColors);
        
        System.out.println(rainbowColors.toString());
    }

    public static void printList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
