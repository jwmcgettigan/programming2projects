/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @project Authorship 1.0
 * @author Justin McGettigan
 */
public class Data {

    private Analysis analyze;
    private List<String> authors, texts, sentences, words, tokens;
    private String linesCombined;
    private List<String> lines;

    public Data() {
        analyze = new Analysis();
        lines = new ArrayList<>();
        linesCombined = "";
    }

    /*
    0 - My father’s family name being Pirrip, and my Christian name Philip, my
    1 - infant tongue could make of both names nothing longer or more explicit
    2 - than Pip. So, I called myself Pip, and came to be called Pip.
    
    0 + 1 = 0
    1
    
    0 + 1 = 0
     */
 /*
    1. Parse sentences from text.
    2. Parse words from sentences.
     */
    //become one giant string
    public void linesCombined() {
        System.out.println();
        /*
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, lines.get(i) + " " + lines.get(i + 1));
            lines.remove(i + 1);
            linesCombined += lines.get(i);
        }*/

        for (int i = 0; i < lines.size(); i++) {
            i = 0;
            lines.set(i, lines.get(i) + " " + lines.get(i + 1));
            lines.remove(i + 1);
        }
        linesCombined = lines.get(0);
        //System.out.println(lines.get(0));
        //System.out.println(linesCombined);
    }

    public void tokens() {
        tokens = new ArrayList<>();

        for (String line : lines) {
            tokens.addAll(Arrays.asList(line.split("[\\s]+")));
        }
        
    }

    public void words() {
        double avgWordLength, sumOfWordLengths = 0;

        words = new ArrayList<>(Arrays.asList(linesCombined.split("[^\\w'_-]+")));
        words.remove("");
        System.out.println("Number of words: " + words.size());

        for (String word : words) {
            sumOfWordLengths += word.length();
            //System.out.println("- " + word + " - " + word.length());
        }
        avgWordLength = sumOfWordLengths / words.size(); //(sum of word lengths)/(number of words)
        System.out.printf("Average Word Length: %.2f%n", avgWordLength);
        analyze.typeTokenRatio(words);
        //analyze.hapaxLegomanaRatio(words); //takes way too long to complete; need a more efficient method
    }

    public void sentences() {
        //"(?<=\\.) "
        //"(?<=\\w[\\w\\)\\]](?<!Mrs?|Dr)[\\.\\?\\!]\\s)"
        //still a work in progress...
        sentences = new ArrayList<>(Arrays.asList(linesCombined.split("[\\!\\?\\.]+|\\. +"))); //should I use BreakIterator instead?
        System.out.println("Number of sentences: " + sentences.size());

        for (String sentence : sentences) {
            sentence = sentence.trim();
            //System.out.println("- " + sentence);
        }
        analyze.wordsPerSentence(sentences);
        analyze.sentenceComplexity(sentences);
    }

    public int numWords() {
        int lineWords = 0;
        int totalWords = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int p = 0; p < lines.get(i).length(); p++) {
                //System.out.println(Arrays.toString(lines.get(i).split("[^a-zA-Z0-9'_-]+")));
                lineWords = (lines.get(i).split("[^a-zA-Z0-9'_-]+")).length;
                //System.out.println("linewords:" + lineWords);
            }
            totalWords += lineWords;
            //System.out.println("totalwords:" + totalWords);
        }
        return totalWords;
    }

    public void wordLengths() {
        String[] test;
        List<Integer> wordLengths = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            //Which is better? ----- "[^a-zA-Z0-9'_-]+" or "[\\s!\\?.,]+"----- my god I had to learn a lot about regex for this...
            test = lines.get(i).split("[^\\w'_-]+");
            System.out.printf("%n" + Arrays.toString(test));
            for (String test1 : test) {
                //System.out.printf("" + test1.length());
                wordLengths.add(test1.length());
            }
        }
        System.out.printf("%n" + wordLengths);
        System.out.printf("%nNumber of word lengths: " + wordLengths.size() + "%n");
    }

    public void readText(String text) {
        try (Stream<String> theLines = Files.lines(Paths.get(text), Charset.forName("UTF-8"))) { //This is the only one I've found that works with the txt files provided
            lines = theLines.collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //I want to create "signatures.txt" and put in each author's linguistic signiture
    public void createAndWriteToFile() {
        /*
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("signatures.txt"), Charset.forName("UTF-8"))) {
            for(int i = 0; i < lines.size(); i++){
                writer.write(lines.get(i));
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }*/
    }

    public List<String> getSentences() {
        return sentences;
    }

    public List<String> getWords() {
        return words;
    }
}
