/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adder;

/**
 *
 * @author Justin
 */
public class Adder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i = 0;
        int jay = 2;
        double icecream = 2.0;
        double gpa = 3.0;
        Number result;
        Addfuncs myfunk = new Addfuncs();
        result = myfunk.addme(jay, gpa);
        result = myfunk.addme(i, gpa);
        
        System.out.println(result);
    }
    
}
