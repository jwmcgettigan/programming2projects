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
 * @project Authorship 1.3
 * @author Justin McGettigan
 */
public class Parser {

    private List<String> tokens, words, sentences, phrases;

    public Parser() {
        tokens = new ArrayList<>();
        words = new ArrayList<>();
        sentences = new ArrayList<>();
        phrases = new ArrayList<>();
    }

    /**
     *
     * @param text
     */
    public void tokens(List<String> text) {
        for (String line : text) {
            tokens.addAll(Arrays.asList(line.split("[\\s]+"))); //alternative: "[\\s]+"
        }
        tokens.removeIf(String::isEmpty);
    }

    /**
     *
     */
    public void words() {
        for (String token : tokens) {
            if (!token.matches("[\\W]+")) { //alternative: "[\\W]+"
                words.add(token.toLowerCase());
            }
        }
        //System.out.println("Number of Words: " + words.size());
    }

    /**
     *
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
    
    /*
    Will keep existing for now, just in case.
     */
    public void phrases() {
        for (String sentence : sentences) {
            phrases.addAll(Arrays.asList(sentence.split("[\\:\\,\\;]")));
        }
    }
    
    public void run() {
        words();
        sentences();
        phrases();
    }
    
    public List<String> getWords() {
        return words;
    }
    
    public List<String> getSentences() {
        return sentences;
    }
}
