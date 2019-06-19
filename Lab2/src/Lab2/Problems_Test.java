/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Justin McGettigan
 * @data 9/6/17
 * 
 */
public class Problems_Test {
    public static void main(String[] args) {
        Problems prob = new Problems();
        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(3,8,92,4,2,17,9));
        ArrayList<String> stringList1 = new ArrayList<>(Arrays.asList("four","score","and","seven","years","ago"));
        ArrayList<String> stringList2 = new ArrayList<>(Arrays.asList("to","be","or","not","to","be","hamlet"));
        
        System.out.println(stringList1);
        prob.switchPairs(stringList1);
        System.out.println(stringList1);
        
        System.out.println(numList);
        prob.minToFront(numList);
        System.out.println(numList);
        
        System.out.println(stringList2);
        prob.switchPairs(stringList2);
        System.out.println(stringList2);
    }
}
