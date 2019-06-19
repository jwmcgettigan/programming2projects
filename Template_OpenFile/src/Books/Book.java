/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Books;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Justin McGettigan
 */
public class Book {
    private String bookTitle;
    private URL url;
    private URI uri;
    
    public Book(String t, String p) throws URISyntaxException{
        bookTitle = t;
        url = getClass().getResource("../resources/files/" + p);
        uri = new URI(url.toString());
        System.out.println(url.getPath());
    }
    
    public void readWithScanner(){
        try{
            Scanner sc = new Scanner(new File(url.getPath()));
            
            String temp;
            
            //loop through file and read it line by line
            while(sc.hasNext()){
                temp = sc.nextLine();
                System.out.println(temp);
            }
            
            //closes file which is done by java even if code is not there
            sc.close();
        }
        catch(IOException | NullPointerException e){
            System.out.println("File Not Found!");
        }
    }
    
    public void readWithBufferReader(){
        try {
            FileReader fileReader = new FileReader(uri.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            String temp = bufferedReader.readLine();
            
            while(temp != null){
                System.out.println(temp);
                temp = bufferedReader.readLine();
            }
            
            bufferedReader.close();
        } catch(IOException | NullPointerException e){
            System.out.println("File Not Found!");
        }
    }
}
