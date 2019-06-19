/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolarSystem;

/**
 *
 * @author Justin McGettigan
 */
public class Planet {
    private double radius;
    private String color;
    private String name;
    
    public Planet(){
        
    }
    
    public Planet(double r, String c, String n){
        radius = r;
        color = c;
        name = n;
    }

    public double getRadius() {
        return radius;
    }
    
    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
    
    public void setRadius(double r) {
        radius = r;
    }
    
    public void setColor(String c) {
        color = c;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public double getArea() {
        return radius*radius*4*Math.PI;
    }
}
