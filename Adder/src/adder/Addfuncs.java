/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adder;

/**
 *
 * @author Justin
 */
public class Addfuncs {
    
    
    public Addfuncs(){
        
    }
    
    //let's restrain the templated programming
    public <T extends Number> double addme(T firstnumber, T secondnumber){
        //T res = firstnumber + secondnumber;
        return firstnumber.doubleValue() + secondnumber.doubleValue();
    }
}
