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
public class Test {
    public static void main(String[] args){
        Shape circle = new Circle();
        Shape square = new Square();
        
        circle.scream();
        circle.sayHello();
        System.out.println(circle.getSize());
        
        square.sayHello();
        System.out.println(square.getSize());
    }
}
