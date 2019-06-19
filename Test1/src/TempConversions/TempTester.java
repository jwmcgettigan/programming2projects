/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TempConversions;

/**
 *
 * @author Justin McGettigan
 */
public class TempTester {
    public static void main(String[] args) {
        Temperature temp = new Temperature(23.5);
        System.out.println("Celcius: " + temp.getTemp_C());
        System.out.println("Fahrenheit: " + temp.calc_Fahren());
        System.out.println("Kelvin: " + temp.calc_Kelvin());
    }
}
