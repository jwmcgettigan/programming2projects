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
public class Hungry {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Pitcher yogi = new Pitcher();
        CreamSoda cs = new CreamSoda();
        Koolaid yayaya = new Koolaid();
        Tequilla salsa = new Tequilla();
        Cowboys andIndians = new Cowboys();
        
        GenericPitcher<Tequilla> mrpotato = new GenericPitcher<>();
        
        yogi.fillCreamSoda(cs);
        yogi.fillKoolaid(yayaya);
        
        mrpotato.fill(salsa);
        GenericPitcher<Cowboys> mrpotato2 = new GenericPitcher<>();
        mrpotato2.fill(andIndians);
        
        GenericPitcher test = new GenericPitcher<>();
    }
    //generic classes are usually for containers
    
    /*
    why does java have templating? To enforce type inferencing
    To prevent type incompatibility errors at runtime
    */
}
