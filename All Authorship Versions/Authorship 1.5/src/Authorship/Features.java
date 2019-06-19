/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @project Authorship 1.5
 * @author Justin McGettigan
 */
public class Features {

    private List<String> words, sentences;
    private List<Double> features;
    private boolean extraCredit;

    public Features() {

    }

    public Features(List<String> wrds, List<String> sntces, boolean xtraCred) {
        words = wrds;
        sentences = sntces;
        extraCredit = xtraCred;
    }

    /**
     * This method call all other methods in the Features class assigns their
     * values to the features list.
     */
    public void run() {
        features = new ArrayList<>(Arrays.asList(avgWordLength(), typeTokenRatio(), hapaxLegomanaRatio(), avgNumOfWordsPerSentence(), sentenceComplexity()));
        if(extraCredit){
            features = new ArrayList<>(Arrays.asList(avgWordLength(), typeTokenRatio(), hapaxLegomanaRatio(), avgNumOfWordsPerSentence(), sentenceComplexity(), punctFrequency(), commonWordFrequency()));
        }
    }

    /**
     * This method calculates the average word length.
     *
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
     *
     * @return The Type Token Ratio.
     */
    public double typeTokenRatio() {
        //Set<String> occurs = new HashSet<>(words);
        return (double) (new HashSet<>(words)).size() / words.size(); //(number of different words)/(number of words)
    }

    /**
     * This method calculates the Hapax Legomana Ratio.
     *
     * @return The Hapax Legomana Ratio.
     */
    public double hapaxLegomanaRatio() {
        Set<String> unique = new HashSet<>(), common = new HashSet<>(); //create two hashsets
        for (String word : words) {
            if (!unique.add(word)) { //add word to UNIQUE if UNIQUE doesn't contain it
                common.add(word); //add word to COMMON if UNIQUE doesn't add a word and if COMMON doesn't contain it
            }
            //once.addAll()
            //once.removeAll()
        }
        return (double) (unique.size() - common.size()) / words.size(); //(number of words that occur once)/(number of words)
    }

    /**
     * This method calculates the average number of words per sentence.
     *
     * @return The average number of words per sentence.
     */
    public double avgNumOfWordsPerSentence() {
        double sumOfNumOfWordsPerSentence = 0;

        for (String sentence : sentences) {
            sumOfNumOfWordsPerSentence += sentence.split("[\\s]+").length;
        }
        return sumOfNumOfWordsPerSentence / sentences.size(); //(sum of the number of words per sentence)/(number of sentences)
    }

    /**
     * This method calculates the sentence complexity.
     *
     * @return The sentence complexity.
     */
    public double sentenceComplexity() {
        double sumOfNumOfPhrasesPerSentence = 0;
        for (String sentence : sentences) {
            sumOfNumOfPhrasesPerSentence += sentence.split("[\\:\\;\\,]").length;
        }
        return sumOfNumOfPhrasesPerSentence / sentences.size(); //(sum of the number of phrases per sentence)/(number of sentences)
    }

    /**
     * This method calculates the punctuation frequency.
     *
     * @return The punctuation frequency.
     */
    public double punctFrequency() { //adds about 0.5s to Oliver Twist
        double sum = 0;
        for (String sentence : sentences) {
            sum += sentence.split("[\\w\\s]+").length / sentence.split("[\\s]+").length; //(Number of punctuation marks) / (Number of words)
        }
        return (double) sum / sentences.size();
    }

    /**
     * This method calculates the frequency of the 100 most common words;
     * I multiplied the result by 100 because it was so small a number.
     * @return The frequency of the 100 most common words.
     */
    public double commonWordFrequency() { //adds about 0.5s to Oliver Twist
        List<String> commonWords = new ArrayList<>(Arrays.asList("the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he", "was", "for", "on", "are", "as", "with", "his", "they", "i", "at", "be", "this", "have", "from", "or", "one", "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", "your", "can", "said", "there", "use", "an", "each", "which", "she", "do", "how", "their", "if", "will", "up", "other", "about", "out", "many", "then", "them", "these", "so", "some", "her", "would", "make", "like", "him", "into", "time", "has", "look", "two", "more", "write", "go", "see", "number", "no", "way", "could", "people", "my", "than", "first", "water", "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", "did", "get", "come", "made", "may", "part"));
        List<String> tempWords = new ArrayList<>(new HashSet<>(words));
        int numOfCommonWords = 0;
        String word;
        
        for (String tempWord : tempWords) {
            word = tempWord.replaceAll("[\\W]+", "");
            for (String commonWord : commonWords) {
                if (word.equals(commonWord)) {
                    numOfCommonWords++;
                    break;
                }
            }
        }
        return  100.0 * ((double)numOfCommonWords / words.size()); //(Sum of occurrences of each common word) / (Total number of words)
    }

    public List<Double> getFeatures() {
        return features;
    }
}
