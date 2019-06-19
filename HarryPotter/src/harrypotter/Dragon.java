/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harrypotter;

/**
 *
 * @author Justin
 */
public class Dragon implements Runnable{
    
    private String name;
    
    public Dragon(){
        name = "The people's court.";
    }
    
    public Dragon(String n){
        name = n;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public void run() {
        System.out.println(name + " roars mightly!");
    }
}
