/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overloads;

/**
 *
 * @author Justin
 */
public class Rainbows {
    public String name;
    
    public Rainbows(String nm){
        name = nm;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
