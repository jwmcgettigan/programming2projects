/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generics;

/**
 *
 * @author Justin McGettigan
 */
public class GenericPair <T>{
    private T first, second;
    
    public GenericPair(T f, T s){
        first = f;
        second = s;
    }
    
    public void swap(){
        T temp = first;
        first = second;
        second = temp;
    }
    
    public T getFirst(){
        return first;
    }
    
    public T getSecond(){
        return second;
    }
    
    public void setFirst(T f){
        first = f;
    }
    
    public void setSecond(T s){
        second = s;
    }
    
    public String toString(){
        return "(" + first + ", " + second + ")";
    }
}
