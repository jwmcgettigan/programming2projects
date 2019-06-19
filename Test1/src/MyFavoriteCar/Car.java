/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyFavoriteCar;

/**
 *
 * @author Justin McGettigan
 */
public class Car extends Vehicle{
    private int year;
    private String make;
    
    public Car(){
        
    }
    
    public Car(int yr, String hue, String mk, int spd){
        super(hue, spd);
        year = yr;
        make = mk;
    }
    
    public void accelerate(){
        setSpeed(getSpeed()+5);
    }
    
    @Override
    public void soundAlarm(){
        System.out.println("BEEP BEEP");
    }
    
    public void brake(){
        setSpeed(getSpeed()-5);
    }
    
    public int getYear(){
        return year;
    }
    
    public void setYear(int yr){
        year = yr;
    }
    
    public String getMake(){
        return make;
    }
    
    public void setMake(String mk){
        make = mk;
    }
}
