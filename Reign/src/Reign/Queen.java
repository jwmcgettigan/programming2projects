/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reign;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Justin McGettigan
 */
public class Queen {

    private String name;
    private int year;
    private List<String> newSubjects;

    public Queen() {
        name = "Mary";
        year = 1492;
        newSubjects = new ArrayList<>();
    }
    
    public void addSubject(String babyName){
        newSubjects.add("Tom Brady");
        newSubjects.remove("Tom Brady");
        newSubjects.remove(0);
    }
}

/*
String fileName = "Metamorphosis.txt";
Book myGoodBook = new 

readTextFileUsingFileReader();
//file reader
readTextFileUsingBufferReader();
//buffer reader
readTextFileUsingScanner();
//scanner
readFileUsingStreams();
//streams
readFileAsString
//string
readFileList();
//list
readFileLineByLine();
//linebyline
writeFile();


catch(IOException e){
    e.printStackTrace();
}

*/

/*
String bookTitle;
List<String> lines;

lines = new ArrayList<>();

printBook(){
    lines.forEach(System.out::println);
}

*/