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
public class Overloads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Character[] testchars = {'O','M','G'};
        Integer[] testints = {1,9,8,8};
        Double[] testfloats = {0.0, 9.2, 15.11, 43.0, 67.23};
        
        Character[] anothertest = {'a','e','i','o','u'};
        Integer[] anotherint = {1, 2, 3};
        Rainbows[] myColors = new Rainbows[3];
        
        myColors[0] = new Rainbows("red");
        myColors[1] = new Rainbows("Blue");
        myColors[2] = new Rainbows("Purple");
        
        MyArrays mondayisfun = new MyArrays(testchars, testints, testfloats);
        
        mondayisfun.printArray(myColors);
        mondayisfun.printArray(anothertest);
        mondayisfun.printArray(anotherint);
    }
    /*Bear
    
    -Bad!-----------------
    public void growl()
    public void growl()
    ------------------
    
    -Bad!-----------------
    public void growl(int i)
    public int growl(int j)
    ------------------
    
    -Okay!-----------------
    public void growl(int i)
    public void growl(double cheeseburger)
    ------------------
    
    -Okay!-----------------
    public void growl(int i, double j)
    public void growl(double i, int j)
    ------------------
    */
}
