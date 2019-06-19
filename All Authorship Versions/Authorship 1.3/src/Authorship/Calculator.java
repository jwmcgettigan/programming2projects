/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @project Authorship 1.3
 * @author Justin McGettigan
 */
public class Calculator {

    /*
    Frequency of Common Words:
    sum of occurrences of each common word over total num of words
    
    for(String word : words){
        
    }
     */

    private Parser parser;
    private List<String> words, sentences;
    private List<Double> features;

    public Calculator() {
        parser = new Parser();
    }

    /**
     *
     * @return
     */
    public double avgWordLength() {
        long start = System.nanoTime();
        double sumOfWordLengths = 0;
        for (String word : words) {
            sumOfWordLengths += word.length();
        }
        System.out.printf("1/5(" + new DecimalFormat("#.####").format((System.nanoTime() - start) / 1000000000.0) + "s), ");
        return sumOfWordLengths / words.size(); //(sum of word lengths)/(number of words)
    }

    /**
     *
     * @return
     */
    public double typeTokenRatio() {
        long start = System.nanoTime();
        Set<String> occurs = new HashSet<>(words);
        System.out.printf("2/5(" + new DecimalFormat("#.####").format((System.nanoTime() - start) / 1000000000.0) + "s), ");
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
     *
     * @return
     */
    public double hapaxLegomanaRatio() {
        long start = System.nanoTime();
        Set<String> set = new HashSet<>(), common = new HashSet<>();
        for (String word : words) {
            if (!set.add(word)) {
                common.add(word);
            }
        }
        System.out.printf("3/5(" + new DecimalFormat("#.####").format((System.nanoTime() - start) / 1000000000.0) + "s), ");
        return (double) (set.size() - common.size()) / words.size(); //(number of words that occur once)/(number of words)
    }

    /**
     *
     * @return
     */
    public double avgNumOfWordsPerSentence() {
        long start = System.nanoTime();
        double sumOfNumOfWordsPerSentence = 0;

        for (String sentence : sentences) {
            sumOfNumOfWordsPerSentence += sentence.split("[^\\w'_-]+").length; //need to improve this regex
        }
        System.out.printf("4/5(" + new DecimalFormat("#.####").format((System.nanoTime() - start) / 1000000000.0) + "s), ");
        return sumOfNumOfWordsPerSentence / sentences.size(); //(sum of the number of words per sentence)/(number of sentences)
    }

    /**
     *
     * @return
     */
    public double sentenceComplexity() {
        long start = System.nanoTime();
        double sumOfNumOfPhrasesPerSentence = 0;
        for (String sentence : sentences) {
            sumOfNumOfPhrasesPerSentence += sentence.split("[\\:\\;\\,]").length;
        }
        System.out.printf("5/5(" + new DecimalFormat("#.####").format((System.nanoTime() - start) / 1000000000.0) + "s)%n");
        return sumOfNumOfPhrasesPerSentence / sentences.size(); //(sum of the number of phrases per sentence)/(number of sentences)
    }

    /*
    Punctuation Frequency: 
    num of punctutation marks over num of words per sentence (and averaged out per sentence)
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

    /**
     *
     */
    public void run() {
        parser.run();
        words = parser.getWords();
        sentences = parser.getSentences();
        //System.out.println("PunctFrequency: " + punctFrequency());
        long start = System.nanoTime();
        features = new ArrayList<>(Arrays.asList(avgWordLength(), typeTokenRatio(), hapaxLegomanaRatio(), avgNumOfWordsPerSentence(), sentenceComplexity()));
        System.out.printf("DONE(" + new DecimalFormat("#.####").format((System.nanoTime() - start) / 1000000000.0) + ")%n");
    }

    public List<Double> getFeatures() {
        return features;
    }

    public Parser getParser() {
        return parser;
    }
}
