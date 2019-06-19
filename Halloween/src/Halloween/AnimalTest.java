/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halloween;

import java.util.ArrayList;

/**
 *
 * @author Justin
 */
public class AnimalTest {
//test: you cannot inpliment/instantiate an abstract class
    public static void main(String[] args) {
        ArrayList bob = new ArrayList<>();
        Animal dean = new Mammal();
        dean.scream();
        
        Animal cat = new Cat();
        
        Animal bug = new Insect();
        
        ((Mammal)dean).sayHello("Charlie");
        Animal myfriends[] = new Animal[3];
        myfriends[0] = dean;
        myfriends[1] = cat;
        myfriends[2] = bug;
    }
}
