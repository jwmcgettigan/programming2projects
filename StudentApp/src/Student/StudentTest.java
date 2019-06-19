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
public class StudentTest {
    public static void main(String[] args){
        Student stud1 = new Student("Bob","Miami", 18);
        Student stud2 = new Student ("Cindy","Las Vegas", 19);

        System.out.println("Name\tage\thometown");

        System.out.println(stud2.getName()+"\t"+stud2.getAge()
        +"\t"+stud2.getHometown());

        System.out.println(stud1.getName()+"\t"+stud1.getAge()
        +"\t"+stud1.getHometown());
    }
}