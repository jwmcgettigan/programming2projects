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
public class Cat extends Mammal{
    
    public Cat(){
        System.out.println("I'm creating a cat.");
    }
    
    public void scream(){
        super.scream();
        System.out.println("Meow!");
    }
}
