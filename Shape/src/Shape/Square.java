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
public class Square extends Shape {
    private int length;
    public Square(){
        
    }
    
    @Override
    public double getSize(){
        return (length*length);
    }
    
    @Override
    public void sayHello(){
        System.out.println("Gutentag");
    }
}
