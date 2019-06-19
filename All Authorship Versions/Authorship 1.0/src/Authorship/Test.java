/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

/**
 * @project Authorship
 * @author Justin McGettigan
 */
public class Test {
    public static void main(String[] args){
        Interface UI = new Interface();
        
        UI.prompt();
        UI.printSignature();
    }
}
