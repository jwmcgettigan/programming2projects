/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
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
 * @project Authorship 1.3
 * @author Justin McGettigan
 */
public class Writer1 {
    
    private Scanner input;
    private int numBooks;
    private String author;
    private List<Double> weights, features;
    private List<String> lines, tokens, words, sentences, phrases;

    public Writer1() {
        input = new Scanner(System.in);
        weights = new ArrayList<>(Arrays.asList(11.0, 33.0, 50.0, 0.4, 4.0));
        lines = new ArrayList<>();
        tokens = new ArrayList<>();
        words = new ArrayList<>();
        sentences = new ArrayList<>();
        phrases = new ArrayList<>();
    }

    public void prompt() {
        String text;
        Boolean resume = true;
        System.out.println("Enter MYSTERY if unknown.");
        System.out.printf("Author? ");
        author = input.nextLine();

        System.out.printf("%nDo not include the file extension; enter STOP if done.%n");
        while (resume) {
            System.out.printf("Text? ");
            text = input.nextLine();
            if (!text.equals("STOP")) {
                text += ".txt";
                if (author.equals("MYSTERY")) {
                    resume = false;
                }
                readText(text);
                numBooks++;

            } else {
                resume = false;
            }
        }

    }

    public void readText(String text) {
        try (Stream<String> theLines = Files.lines(Paths.get(text), Charset.forName("UTF-8"))) {
            if (numBooks > 0) {
                lines.addAll(theLines.collect(Collectors.toList()));
            } else {
                lines = theLines.collect(Collectors.toList());
            }
        } catch (IOException ex) {
            Logger.getLogger(Writer1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void parse(){
        for (String line : lines) {
            tokens.addAll(Arrays.asList(line.split("[\\s]+")));
        }
        tokens.remove("");
        //======================================================================
        List<String> tempTokens = tokens;
        for (String token : tempTokens) {
            if (!token.matches("[\\W]+")) { //is this the ideal regex for (if not all non-word characters)?
                words.add(token);
            }
        }
        //======================================================================
        String sentence = "";
        for (int i = 0; i < tokens.size(); i++) {
            sentence += tokens.get(i) + " ";
            if (tokens.get(i).contains("!") || tokens.get(i).contains("?") || tokens.get(i).contains(".") || i == tokens.size() - 1) { //need to terminate with end of file
                sentence = sentence.replaceAll("[\\!\\?\\.]", "");
                sentences.add(sentence.trim());
                sentence = "";
            }
        }
        System.out.println(sentences);
        sentences.remove("");
        //======================================================================
        for (String s : sentences) {
            phrases.addAll(Arrays.asList(s.split("[\\:\\,\\;]")));
        }
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

    public void authorFeatures() {
        
        features = new ArrayList<>(Arrays.asList(avgWordLength(), typeTokenRatio(), hapaxLegomanaRatio(), avgNumOfWordsPerSentence(), sentenceComplexity()));
    }

    public void readSignatures(){
        List<String> tempLines = new ArrayList<>();
        try (Stream<String> theLines = Files.lines(Paths.get("signatures.txt"), Charset.forName("UTF-8"))) {
            tempLines = theLines.collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(Writer1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!author.equals("MYSTERY")){
            export(tempLines);
        } else{
            match(tempLines);
        }
    }
    
    public void export(List<String> tempLines) { //I should shorten this...
        
        String list = String.format("%-19s | %d | %.2f | %.2f | %.2f | %5.2f | %.2f |", author, numBooks, features.get(0), features.get(1), features.get(2), features.get(3), features.get(4));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("signatures.txt"), Charset.forName("UTF-8"))) {
            boolean known = false;
            for (int i = 0; i < tempLines.size(); i++) {
                if (tempLines.get(i).contains("Tommy")) {
                    tempLines.set(i, list);
                    known = true;
                    break;
                }
            }
            if (!known) {
                tempLines.add(list);
            }
            for (String line : tempLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);;
        }
    }

    public void match(List<String> tempLines) {
        List<List<String>> stats = new ArrayList<>();
        for (String line : tempLines) {
            stats.add(new ArrayList<>(Arrays.asList(line.split("[\\|]"))));
        }
        for (List<String> stat : stats) {
            for (int i = 0; i < stat.size(); i++) {
                stat.set(i, stat.get(i).trim());
            }

        }
        List<Double> sums = new ArrayList<>();
        if (author.equals("MYSTERY")) {
            for (List<String> stat : stats) {
                double sumOfFeatures = 0.0;
                for (int i = 2, k = 0; i < stat.size(); i++, k++) {
                    sumOfFeatures += Math.abs(features.get(k) - Double.valueOf(stat.get(i))) * weights.get(k);
                }
                sums.add(sumOfFeatures);
                //System.out.println(stat.get(0) + ": " + sumOfFeatures);
            }
            double min = sums.get(0);
            int index = 0;
            for (double sum : sums) {
                if (min > sum) {
                    min = sum;
                }
            }
            for (int i = 0; i < sums.size(); i++){
                if(sums.get(i).equals(min)){
                    index = i;
                }
            }
            String match = "failure";
            for (int p = 0; p < stats.size(); p++) {
                match = stats.get(index).get(0);
            }
            System.out.printf("%nThe author is most likely " + match + ".%n");
        }
    }

    public void print() {
        prompt();
        parse();

        authorFeatures();
        readSignatures();
        
        System.out.printf("%n%-19s %s%n", " Author ", "| Books |  a1  |  a2  |  a3  |  a4  |  a5  |");
        System.out.printf("----------------------------------------------------------------------%n");
        System.out.printf(" %-18s |   %d   | %.2f | %.2f | %.2f | %.2f | %.2f |%n", author, numBooks, features.get(0), features.get(1), features.get(2), features.get(3), features.get(4));
    }
}
