/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractShips;

/**
 *
 * @author Justin McGettigan
 */
public abstract class Ship {
    protected String name;
    protected int speed;
    
    public Ship(){
        
    }
    
    public abstract void speedup();
    
    public void fire(){
        System.out.println("Fire!");
    }
}
