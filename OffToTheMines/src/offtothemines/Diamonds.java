/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offtothemines;

/**
 *
 * @author Justin
 */
public class Diamonds {
    private int diamonds = 1000;
    
    public Diamonds(){
        
    }
    
    public boolean gone(){
        return diamonds == 0;
    }
    
    public void mineDiamonds(int mined){
        diamonds -= mined;
    }
    
    public int getDiamonds(){
        return diamonds;
    }
}
