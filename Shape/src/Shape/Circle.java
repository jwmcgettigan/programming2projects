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
public class Circle extends Shape{
    private int radius;
    public Circle(){
        
    }
    
    @Override
    public double getSize(){
        return (Math.PI*radius*radius);
    }
    
    @Override
    public void sayHello(){
        System.out.println("Gutent");
    }
}
