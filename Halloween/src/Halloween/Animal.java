/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halloween;

/**
 *
 * @author Justin
 */
public abstract class Animal {
    protected String name;
    int weight;
    
    public Animal() {
        System.out.println("I'm creating an animal");
        name = "Dumbo";
        weight = 0;
    }
    
    public abstract void scream();
    
    public void weightGain(){
        weight+=15;
    }
}
//polymorphism: the use of multiple changes
//make a generic class that can be used in multiple ways