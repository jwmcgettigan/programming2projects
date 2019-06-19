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
 * @project Authorship 1.2
 * @author Justin McGettigan
 */
public class Calculator {
    
    /*
    Punctuation Frequency: 
    num of punctutation marks over num of words per sentence (and averaged out per sentence): punctuation frequency
    
    double sum = 0, punctFreq;
    for(String sentence : sentences){
        sum += (double)numPunctMarks / numOfWords;
    }
    punctFreq = (double)sum / sentences.size();
    */
    /*
    Frequency of Common Words:
    sum of occurrences of each common word over total num of words
    
    for(String word : words){
        
    }
    */
    
    private Parser parser;
    private List<String> words, sentences;
    private List<Double> features;
    
    public Calculator(){
        parser = new Parser();
    }
    
    public double avgWordLength() {
        double sumOfWordLengths = 0;
        for (String word : words) {
            sumOfWordLengths += word.length();
            //System.out.println("- " + word + " - " + word.length());
        }
        return sumOfWordLengths / words.size(); //(sum of word lengths)/(number of words)
        //System.out.printf("Average Word Length: %.2f%n", avgWordLength);
    }

    public double typeTokenRatio() {
        List<String> tempWords = new ArrayList<>();

        for (String word : words) {
            if (!tempWords.contains(word.toLowerCase())) {
                tempWords.add(word.toLowerCase());
            }
        }
        return (double) tempWords.size() / words.size(); //(number of different words)/(number of words)
        //System.out.printf("Type-Token Ratio: %.2f%n", typeTokenRatio);
    }

    public double hapaxLegomanaRatio() {
        List<String> single = new ArrayList<>(), multiple = new ArrayList<>();

        for (String temp : words) {
            String word = temp.replaceAll("[\\W]+", "").toLowerCase();
            if (single.contains(word)) {
                single.remove(word);
                multiple.add(word);
            } else if (!multiple.contains(word)) {
                single.add(word);
            }
        }
        return (double) single.size() / words.size(); //(number of words that occur once)/(number of words)
        //System.out.printf("Hapaz Legomana Ratio: %.2f%n", hapaxLegomanaRatio);
    }

    public double avgNumOfWordsPerSentence() {
        double sumOfNumOfWordsPerSentence = 0;

        for (String sentence : sentences) {
            sumOfNumOfWordsPerSentence += sentence.split("[^\\w'_-]+").length;
        }
        return sumOfNumOfWordsPerSentence / sentences.size(); //(sum of the number of words per sentence)/(number of sentences)
        //System.out.printf("Average Number of Words Per Sentence: %.2f%n", avgNumOfWordsPerSentence);
    }

    public double sentenceComplexity() {
        double sumOfNumOfPhrasesPerSentence = 0;

        for (String sentence : sentences) {
            sumOfNumOfPhrasesPerSentence += sentence.split("[\\:\\;\\,]").length;
        }
        return sumOfNumOfPhrasesPerSentence / sentences.size(); //(sum of the number of phrases per sentence)/(number of sentences)
        //System.out.printf("Sentence Complexity: %.2f%n", sentenceComplexity);
    }
    
    public void run(){
        parser.run();
        words = parser.getWords();
        sentences = parser.getSentences();
        features = new ArrayList<>(Arrays.asList(avgWordLength(), typeTokenRatio(), hapaxLegomanaRatio(), avgNumOfWordsPerSentence(), sentenceComplexity()));
        
    }
    
    public List<Double> getFeatures(){
        return features;
    }
    
    public Parser getParser(){
        return parser;
    }
}
