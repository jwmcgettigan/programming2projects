/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shape;

/**
 *
 * @author Justin
 */
public abstract class Shape implements Clemson{
    private String name, shape;
    
    public Shape(){
        
    }
    
    public Shape(String nm, String sh){
        name = nm;
        shape = sh;
    }
    
    public void sayHello(){
        System.out.println("Come Esta?");
    }
    
    
    public abstract double getSize();
    
    @Override
    public void scream() {
        System.out.println("Ahhhhhhhh!");
    }
}
