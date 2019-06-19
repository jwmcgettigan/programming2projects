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
public class Vehicle {
    private String color;
    private int speed;
    
    public Vehicle(){
        
    }
    
    public Vehicle(String hue, int how_fast){
        color = hue;
        speed = how_fast;
    }
    
    public void soundAlarm(){
        System.out.println("Vehicle Alarm Initiated");
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public void setSpeed(int spd){
        speed = spd;
    }
    
    public String getColor(){
        return color;
    }
    
    public void setColor(String hue){
        color = hue;
    }
}
