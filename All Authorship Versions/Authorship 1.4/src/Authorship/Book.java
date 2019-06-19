/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @project Authorship 1.4
 * @author Justin McGettigan
 */
public class Book {

    private List<String> text, tokens, words, sentences;

    public Book() {

    }

    public Book(List<String> txt) {
        text = txt;
        tokens = new ArrayList<>();
        words = new ArrayList<>();
        sentences = new ArrayList<>();
    }
    
    /**
     * This method calls all other methods in the Book class.
     */
    public void run() {
        tokens();
        words();
        sentences();
    }
    
    /**
     * This method fills an array list with tokens.
     */
    public void tokens() {
        for (String line : text) {
            tokens.addAll(Arrays.asList(line.split("[\\s]+"))); //alternative: "[\\s]+"
        }
    }

    /**
     * This method fills an array list with words.
     */
    public void words() {
        for (String token : tokens) {
            if (!(token.isEmpty() || token.matches("[\\W]+"))) {
                words.add(token.toLowerCase());
            }
        }
    }

    /**
     * This method fills an array list with sentences.
     */
    public void sentences() {
        String sentence = "";
        for (int i = 0; i < tokens.size(); i++) {
            sentence += tokens.get(i) + " ";
            if (tokens.get(i).contains("!") || tokens.get(i).contains("?") || tokens.get(i).contains(".") || i == tokens.size() - 1) { //need to terminate with end of file
                sentence = sentence.replaceAll("[\\!\\?\\.]", "");
                sentences.add(sentence.trim());
                sentence = "";
            }
        }
        sentences.removeIf(String::isEmpty);
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> getSentences() {
        return sentences;
    }
}
