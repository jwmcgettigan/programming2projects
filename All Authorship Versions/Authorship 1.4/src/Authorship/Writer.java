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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @project Authorship 1.4
 * @author Justin McGettigan
 */
public class Writer {
    
    private List<List<Double>> booksFeatures;
    private List<Double> authorFeatures, weights;
    private String author;
    private int numBooks;
    
    public Writer(){
        
    }
    
    public Writer(String athr, List<List<Double>> bksFtrs, int numBks){
        author = athr;
        booksFeatures = bksFtrs;
        numBooks = numBks;
        authorFeatures = new ArrayList<>();
        weights = new ArrayList<>(Arrays.asList(10.0, 75.0, 100.0, 10.0, 75.0));
        //10, 75, 100, 10, 75
    }
    
    /**
     * This method calls all other methods in the Writer class.
     */
    public void run(){
        features();
        readSignatures();
    }
    
    /**
     * This method finds the average of the features
     * from each book and these averages make up
     * the author's features.
     */
    public void features() {
        double num;
        for (int i = 0; i < weights.size(); i++) {
            num = 0;
            for (List<Double> bookFeature : booksFeatures) {
                num += bookFeature.get(i);
            }
            authorFeatures.add(num / booksFeatures.size());
        }
    }
    
    /**
     * This method reads the signatures file and executes the
     * export method or match method depending on whether
     * the user is adding new information or looking to find
     * out who is most likely the author of an unknown book.
     */
    public void readSignatures() {
        List<String> signatures = new ArrayList<>();
        try (Stream<String> theLines = Files.lines(Paths.get("signatures.txt"), Charset.forName("UTF-8"))) {
            signatures = theLines.collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!author.equals("MYSTERY")) {
            export(signatures);
        } else {
            match(signatures);
        }
    }

    /**
     * This method writes the author's signature to the signatures file,
     * adding to or replacing existing signatures in the process.
     * @param signatures This parameter is a list of lines that make up the signatures file.
     */
    public void export(List<String> signatures) { //I should shorten this...
        String newSignature = String.format("%-19s | %d | %.2f | %.2f | %.2f | %5.2f | %.2f |", author, numBooks, authorFeatures.get(0), authorFeatures.get(1), authorFeatures.get(2), authorFeatures.get(3), authorFeatures.get(4));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("signatures.txt"), Charset.forName("UTF-8"))) {
            boolean known = false;
            for (int i = 0; i < signatures.size(); i++) {
                if (signatures.get(i).contains(author)) {
                    signatures.set(i, newSignature);
                    known = true;
                    break;
                }
            }
            if (!known) {
                signatures.add(newSignature);
            }
            for (String signature : signatures) {
                writer.write(signature);
                writer.newLine();
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
    
    /**
     * This method, using the equation provided by the assignment,
     * determines who is most likely the author of an unknown book.
     * @param signatures This parameter is a list of lines that make up the signatures file.
     */
    public void match(List<String> signatures) {
        List<List<String>> stats = new ArrayList<>();
        for (String signature : signatures) {
            stats.add(new ArrayList<>(Arrays.asList(signature.split("[\\|]"))));
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
                    sumOfFeatures += Math.abs(authorFeatures.get(k) - Double.valueOf(stat.get(i))) * weights.get(k);
                }
                sums.add(sumOfFeatures);
            }
            double min = sums.get(0);
            int index = 0;
            for (double sum : sums) {
                if (min > sum) {
                    min = sum;
                }
            }
            for (int i = 0; i < sums.size(); i++) {
                if (sums.get(i).equals(min)) {
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
    
    public List<Double> getAuthorFeatures(){
        return authorFeatures;
    }
}
