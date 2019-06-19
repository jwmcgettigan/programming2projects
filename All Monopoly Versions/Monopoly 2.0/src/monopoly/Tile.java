/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javafx.scene.image.Image;

/**
 * @version 2.0
 * @author Justin McGettigan
 */
public class Tile {
    
    private double x, y;
    private Image sprite;
    private int ID;
    private int value, type; //types: property(1), penalty(2), special(3)
    private int color; //colors: brown(1), lightblue(2), purple(3), orange(4), red(5), yellow(6), green(7), darkblue(8)
    private int specialType; //specialTypes: exploreDungeon(1), chance(2)
    private String name;
    
    public Tile(Image s, int id){
        sprite = s;
        ID = id;
        //type = 0;
    }
    
    public void setName(String s){
        name = s;
    }
    
    public String getName(){
        return name;
    }
    
    public void setSpecialType(int i){
        specialType = i;
    }
    
    public int getSpecialType(){
        return specialType;
    }
    
    public void setColor(int i){
        color = i;
    }
    
    public int getColor(){
        return color;
    }
    
    public void setType(int i){
        type = i;
    }
    
    public int getType(){
        return type;
    }
    
    public void setValue(int v){
        value = v;
    }
    
    public int getValue(){
        return value;
    }
    
    public int getRent(){
        return (int)(value/10);
    }
    
    public void setCoord(double X, double Y){
        x = X;
        y = Y;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public int getID(){
        return ID;
    }
    
    public Image sprite(){
        return sprite;
    }
}
