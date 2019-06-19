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
public class Mammal extends Animal implements Move, Sleep{
    String furType;
    
    public Mammal(){
        System.out.println("I'm creating a mammal");
        furType = "Very hairy";
    }
    
    public void run(){
        System.out.println("Tom Brady is running");
    }
    
    public void scream(){
        System.out.println("Grr");
    }

    @Override
    public void jump() {
    }

    @Override
    public void moveSideways() {
    }

    @Override
    public void snooze() {
    }
}
