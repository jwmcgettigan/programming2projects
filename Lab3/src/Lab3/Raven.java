/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @project Lab 3
 * @author Justin McGettigan
 */
public class Raven {
    private String bookTitle;
    private List<String> lines;

    public Raven(String fileName) {
        bookTitle = fileName;
        lines = new ArrayList<>();
    }

    public void readFileUsingStreams() {
        BufferedReader filereader = null;
        try (Stream<String> theLines = Files.lines(Paths.get(bookTitle))) {
            lines = theLines.collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(Raven.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createAndWriteToFile() {
        try {
            Files.write(Paths.get("NewRaven.txt"), lines, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(Raven.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printBook() {
        System.out.printf("Number of Vowels: " + numVowels() + "%n");
        System.out.printf("Number of Capital Letters: " + numCAPS() + "%n");
        lines.forEach(System.out::println);
    }

    public int numVowels() {
        int numVowels = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int p = 0; p < lines.get(i).length(); p++) {
                String letter = Character.toString(lines.get(i).charAt(p));
                if (letter.matches("[aeiouAEIOU]")){
                    numVowels++;
                }
                /*
                if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U') {
                    numVowels++;
                }*/
            }
        }
        return numVowels;
    }

    public int numCAPS() {
        int numCAPS = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int p = 0; p < lines.get(i).length(); p++) {
                char letter = lines.get(i).charAt(p);
                if (Character.isUpperCase(letter)) {
                    numCAPS++;
                }
            }
        }
        return numCAPS;
    }

}
