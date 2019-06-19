/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2;

import java.util.ArrayList;

/**
 *
 * @author Justin McGettigan
 * @date 9/6/17
 */
public class Problems {

    public Problems() {

    }

    public void minToFront(ArrayList<Integer> numList) {
        int min = numList.get(0);
        for(int num : numList){
            if(min > num){
                min = num;
            }
        }
        numList.remove(numList.indexOf(min));
        numList.add(0, min);
    }

    public void switchPairs(ArrayList<String> stringList) {
        for (int i = 0; i < stringList.size() - 1; i += 2) {
            stringList.add(i, stringList.get(i+1));
            stringList.remove(i+2);
        }
    }
    /*
    [to, be, or, not, to, be, hamlet]
    i=0
    add(i, i+1)
    [be, to, be, or, not, to, be, hamlet]
    remove(i+2)
    [be, to, or, not, to, be, hamlet]
    i=2
    add(i, i+1)
    [be, to, not, or, not, to, be, hamlet]
    remove(i+2)
    [be, to, not, or, to, be, hamlet]
    i=4
    add(i, i+1)
    [be, to, not, or, be, to, be, hamlet]
    remove(i+2)
    [be, to, not, or, be, to, hamlet]
    */
}
