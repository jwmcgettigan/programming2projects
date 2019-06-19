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
public class Carrier extends Ship {
    private int numAircraft;
    
    public Carrier(){
        super();
    }
    
    @Override
    public void speedup(){
        //do nothing
    }
    
    @Override
    public void fire(){
        //super.fire();
        System.out.println("Bombard!");
    }
}
