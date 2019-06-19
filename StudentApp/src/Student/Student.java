/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;
/**
*
* @author Justin McGettigan
*
*/
public class Student {
    private String name;
    private String hometown;
    private int age;

    //Constructor
    public Student(String stud_name, String home, int years){
        name = stud_name;
        hometown = home;
        age = years;

    }
    
    public String getName(){
        return name;
    }
    public String getHometown(){
        return hometown;
    }
    public int getAge(){
        return age;
    }
    public void setName(String studname){
        name = studname;
    }
    public void setHometown(String studhometown){
        hometown = studhometown;
    }
    public void setAge(int studage){
        age =studage;
    }
}