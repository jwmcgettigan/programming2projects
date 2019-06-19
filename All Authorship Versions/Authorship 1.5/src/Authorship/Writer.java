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
 * @project Authorship 1.5
 * @author Justin McGettigan
 */
public class Writer {
    
    private List<List<Double>> booksFeatures;
    private List<Double> authorFeatures, weights;
    private String author;
    private int numBooks;
    private boolean extraCredit;
    
    public Writer(){
        
    }
    
    public Writer(String athr, List<List<Double>> bksFtrs, int numBks, boolean xtraCred){
        author = athr;
        booksFeatures = bksFtrs;
        extraCredit = xtraCred;
        numBooks = numBks;
        authorFeatures = new ArrayList<>();
        if(extraCredit){
            weights = new ArrayList<>(Arrays.asList(5.0, 33.0, 50.0, 0.4, 10.0, 50.0, 1.0));
        }else{
            weights = new ArrayList<>(Arrays.asList(5.0, 33.0, 50.0, 0.4, 10.0));
        }
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
        String file, newSignature;
        if(extraCredit){
            file = "signaturesEC.txt";
            newSignature = String.format("%-19s | %d | %.3f | %.3f | %.3f | %5.3f | %.3f | %.3f | %.3f |", author, numBooks, authorFeatures.get(0), authorFeatures.get(1), authorFeatures.get(2), authorFeatures.get(3), authorFeatures.get(4), authorFeatures.get(5), authorFeatures.get(6));
        } else {
            file = "signatures.txt";
            newSignature = String.format("%-19s | %d | %.3f | %.3f | %.3f | %5.3f | %.3f |", author, numBooks, authorFeatures.get(0), authorFeatures.get(1), authorFeatures.get(2), authorFeatures.get(3), authorFeatures.get(4));
        }
        try (Stream<String> theLines = Files.lines(Paths.get(file), Charset.forName("UTF-8"))) {
            signatures = theLines.collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!author.equals("MYSTERY")) {
            export(signatures, file, newSignature);
        } else {
            match(signatures);
        }
    }

    /**
     * This method writes the author's signature to the signatures file,
     * adding to or replacing existing signatures in the process.
     * @param signatures This parameter is a list of lines that make up the signatures file.
     * @param file This parameter is the file that will be exported to.
     * @param newSignature This parameter is the string that will be written to the file.
     */
    public void export(List<String> signatures, String file, String newSignature) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file), Charset.forName("UTF-8"))) {
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
            stats.add(Arrays.asList(signature.split("[\\|]")));
        }
        double min = 0.0;
        int index = 0;
        if (author.equals("MYSTERY")) {
            for (int p = 0; p < stats.size(); p++){
                double sumOfFeatures = 0.0;
                for (int i = 2, k = 0; i < stats.get(p).size(); i++, k++) {
                    sumOfFeatures += Math.abs(authorFeatures.get(k) - Double.valueOf(stats.get(p).get(i).trim())) * weights.get(k);
                }
                if (p == 0 || min > sumOfFeatures) {
                    min = sumOfFeatures;
                    index = p;
                }
            }
            String match = stats.get(index).get(0).trim();
            System.out.printf("%nThe author is most likely " + match + ".%n");
        }
    }
    
    public List<Double> getAuthorFeatures(){
        return authorFeatures;
    }
}
