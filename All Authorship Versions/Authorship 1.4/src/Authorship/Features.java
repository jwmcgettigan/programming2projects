/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @project Authorship 1.4
 * @author Justin McGettigan
 */
public class Features {
    
    private List<String> words, sentences;
    private List<Double> features;
    
    public Features(){
        
    }
    
    public Features(List<String> wrds, List<String> sntces){
        words = wrds;
        sentences = sntces;
    }
    
    /**
     * This method call all other methods in the Features class
     * assigns their values to the features list.
     */
    public void run() {
        features = new ArrayList<>(Arrays.asList(avgWordLength(), typeTokenRatio(), hapaxLegomanaRatio(), avgNumOfWordsPerSentence(), sentenceComplexity()));
    }
    
    /**
     * This method calculates the average word length.
     * @return The average word length.
     */
    public double avgWordLength() {
        double sumOfWordLengths = 0;
        for (String word : words) {
            sumOfWordLengths += word.length();
        }
        return sumOfWordLengths / words.size(); //(sum of word lengths)/(number of words)
    }

    /**
     * This method calculates the Type Token Ratio.
     * @return The Type Token Ratio.
     */
    public double typeTokenRatio() {
        Set<String> occurs = new HashSet<>(words);
        return (double) occurs.size() / words.size(); //(number of different words)/(number of words)
    }

    /*
    Create 2 hashsets.
    add word to SET if it doesn't contain it
    add word to COMMON if SET doesn't add a word
    SET = all WORDS that occur
    COMMON = all words that occur more than once
    SET - COMMON = all words that occur once
     */
    /**
     * This method calculates the Hapax Legomana Ratio.
     * @return The Hapax Legomana Ratio.
     */
    public double hapaxLegomanaRatio() {
        Set<String> set = new HashSet<>(), common = new HashSet<>();
        for (String word : words) {
            if (!set.add(word)) {
                common.add(word);
            }
        }
        return (double) (set.size() - common.size()) / words.size(); //(number of words that occur once)/(number of words)
    }

    /**
     * This method calculates the average number of words per sentence.
     * @return The average number of words per sentence.
     */
    public double avgNumOfWordsPerSentence() {
        double sumOfNumOfWordsPerSentence = 0;

        for (String sentence : sentences) {
            sumOfNumOfWordsPerSentence += sentence.split("[\\s]+").length; //need to improve this regex
        }
        return sumOfNumOfWordsPerSentence / sentences.size(); //(sum of the number of words per sentence)/(number of sentences)
    }

    /**
     * This method calculates the sentence complexity.
     * @return The sentence complexity.
     */
    public double sentenceComplexity() {
        double sumOfNumOfPhrasesPerSentence = 0;
        for (String sentence : sentences) {
            sumOfNumOfPhrasesPerSentence += sentence.split("[\\:\\;\\,]").length;
        }
        return sumOfNumOfPhrasesPerSentence / sentences.size(); //(sum of the number of phrases per sentence)/(number of sentences)
    }
    
    /*
    Punctuation Frequency: 
    num of punctutation marks over num of words per sentence (and averaged out per sentence)
     */
    
    /**
     * This method calculates the punctuation frequency.
     * @return The punctuation frequency.
     */
    public double punctFrequency() {
        double sum = 0, numPunctMarks, numOfWords;
        Pattern p = Pattern.compile("\\p{Punct}+");
        Matcher m;
        for (String sentence : sentences) {
            numPunctMarks = 0;
            numOfWords = 0;
            for(String token : Arrays.asList(sentence.split("[\\s]+"))){
                if(!token.matches("\\p{Punct}+")){
                    numOfWords++;
                }
                m = p.matcher(token);
                if(m.find()){
                    numPunctMarks++;
                }
            }
            sum += (double) numPunctMarks / numOfWords;
        }
        return (double) sum / sentences.size();
    }
    
    /*
    Frequency of Common Words:
    sum of occurrences of each common word over total num of words
    
    for(String word : words){
        
    }
     */
    
    public List<Double> getFeatures() {
        return features;
    }
}
