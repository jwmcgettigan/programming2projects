/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3;

/**
 * @project Lab 3
 * @author Justin McGettigan
 */
public class Raven_Test {
    public static void main(String[] args) {
        Raven raven = new Raven("Raven.txt");
        
        raven.readFileUsingStreams();
        raven.createAndWriteToFile();
        raven.printBook();
    }
}
