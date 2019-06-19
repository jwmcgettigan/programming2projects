/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generics;

/**
 *
 * @author Justin McGettigan
 */
public class PairTest {
    public static void main(String[] args) {
        GenericPair<String> ps = new GenericPair("happy", "sad");
        GenericPair<Double> pd = new GenericPair(2.5, 5.7);
        System.out.println(ps);
        System.out.println(pd);
        ps.setFirst("very");
        pd.setSecond(-3.4);
        System.out.println(ps);
        System.out.println(pd);
        GenericPair<String> ps2 = new GenericPair("one", "two");
        System.out.println(ps2);
        ps2.swap();
        System.out.println(ps2);
    }
}
