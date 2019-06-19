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
public class MyArrays {
    private Character[] mychars;
    private Integer[] myints;
    private Double[] mydoubles;
    
    public MyArrays(){
        
    }
    
    public MyArrays(Character[] mychars, Integer[] myints, Double[] myfloats){
        this.mychars = mychars;
        this.myints = myints;
        this.mydoubles = myfloats;
    }
    
    public <T> void printArray(T[] inchars){
        for(T c : inchars){
            System.out.println(c);
        }
    }
    
    /*
    public <T> T printArray(T[] inchars){
        for(T c : inchars){
            System.out.println(c);
        }
    }*/
}
