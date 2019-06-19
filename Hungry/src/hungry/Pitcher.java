/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungry;

/**
 *
 * @author Justin
 */
public class Pitcher {
    
    private CreamSoda yodaddy;
    private Koolaid kicks;
    
    
    public Pitcher(){
        
    }
    
    public void fillCreamSoda(CreamSoda cs){
        yodaddy = cs;
    }
    
    public void fillKoolaid(Koolaid k){
        kicks = k;
    }
}
