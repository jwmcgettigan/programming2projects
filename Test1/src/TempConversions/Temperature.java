/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TempConversions;

/**
 *
 * @author Justin McGettigan
 */
public class Temperature {
    private double temp_C;
    
    public Temperature(){
        
    }
    
    public Temperature(double tempC){
        temp_C = tempC;
    }
    
    public double calc_Fahren(){
        return (temp_C * (9.0/5.0)) + 32;
    }
    
    public double calc_Kelvin(){
        return temp_C + 273.15;
    }
    
    public double getTemp_C(){
        return temp_C;
    }
    
    public void setTemp_C(double tempC){
        temp_C = tempC;
    }
}
