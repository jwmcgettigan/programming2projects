/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * @project Authorship 1.0
 * @author Justin McGettigan
 */
public class Analysis {
    
    
    public Analysis(){
        
    }
    
    public void typeTokenRatio(List<String> words){
        List<String> tempWords = new ArrayList<>();
        double typeTokenRatio;
        
        for (String word : words) {
            if(!tempWords.contains(word.toLowerCase())){
                tempWords.add(word.toLowerCase());
            }
        }
        typeTokenRatio = (double)tempWords.size() / words.size(); //(number of different words)/(number of words)
        System.out.printf("Type-Token Ratio: %.2f%n", typeTokenRatio);
    }
    
    public void hapaxLegomanaRatio(List<String> words){
        List<String> tempWords = new LinkedList<>(words);
        double hapaxLegomanaRatio;
        
        for (String word : words) {
            if(tempWords.contains(word.toLowerCase())){
                tempWords.removeAll(Arrays.asList(word));
            }
            if(!tempWords.contains(word.toLowerCase())){
                tempWords.add(word.toLowerCase());
            }
            System.out.println(tempWords.size());
        }
        /*
        for (int i = 0; i < words.size(); i++){
            if(!(Collections.frequency(tempWords, words.get(i))==1)){
                tempWords.remove(words.get(i));
                System.out.println(tempWords.size());
            }
        }
        for (String word : words) {
            if(!(Collections.frequency(tempWords, word)==1)){
                tempWords.remove(word);
            }
        }*/
        hapaxLegomanaRatio = (double)tempWords.size() / words.size(); //(number of words that occur once)/(number of words)
        System.out.printf("Hapaz Legomana Ratio: %.2f%n", hapaxLegomanaRatio);
        
    }
    
    public void wordsPerSentence(List<String> sentences){
        double sumOfNumOfWordsPerSentence = 0, avgNumOfWordsPerSentence;
        
        for (String sentence : sentences) {
            sumOfNumOfWordsPerSentence += sentence.split("[^\\w'_-]+").length;
        }
        avgNumOfWordsPerSentence = sumOfNumOfWordsPerSentence / sentences.size(); //(sum of the number of words per sentence)/(number of sentences)
        System.out.printf("Average Number of Words Per Sentence: %.2f%n", avgNumOfWordsPerSentence);
    }
    
    public void sentenceComplexity(List<String> sentences){
        double sentenceComplexity; //average number of phrases per sentence
        double sumOfNumOfPhrasesPerSentence = 0;
        for (String sentence : sentences) {
            sumOfNumOfPhrasesPerSentence += sentence.split("[\\:\\;\\,]").length;
        }
        sentenceComplexity = sumOfNumOfPhrasesPerSentence / sentences.size(); //(sum of the number of phrases per sentence)/(number of sentences)
        System.out.printf("Sentence Complexity: %.2f%n", sentenceComplexity);
    }
    
    public void punctuationFrequency(){
        
    }
    
    public void commonWordsFrequency(){
        
    }
}
