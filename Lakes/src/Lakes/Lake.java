/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lakes;

/**
 *
 * @author Justin McGettigan
 * date Aug 23. 2017
 */
public class Lake {
    private String name;
    private int temp;
    
    public Lake(){
        name = "billy";
        temp = -72;
    }

    public Lake(String nm, int tmp) {
        name = nm;
        temp = tmp;
    }

    public String getName() {
        return name;
    }

    public int getTemp() {
        return temp;
    }
    
    public void setTemp(int mytemp){
        temp = mytemp;
    }
    
    public void setName(String myName){
        name = myName;
    }
    
    public boolean isCold(){ 
        if (temp<1) {
            return true;
        }
        return false;
    }
}
