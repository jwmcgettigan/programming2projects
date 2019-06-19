/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Books;

import java.net.URISyntaxException;

/**
 *
 * @author Justin
 */
public class BooksTester {
    public static void main(String[] args) throws URISyntaxException{
        //create instance of book
        Book myBook = new Book("Metamorphosis", "Metamorphosis.txt");
        
        myBook.readWithBufferReader();
    }
}
