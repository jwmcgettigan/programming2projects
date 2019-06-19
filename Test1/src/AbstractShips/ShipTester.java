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
public class ShipTester {
    public static void main(String[] args) {
        Ship battle = new Battleship(), carrier = new Carrier();
        
        battle.fire();
        carrier.fire();
    }
}
