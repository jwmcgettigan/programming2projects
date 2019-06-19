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
 * @project Authorship 1.2
 * @author Justin McGettigan
 */
public class Writer {
    
    private Scanner input;
    Calculator calculator;
    private int numBooks;
    private String author;
    private List<Double> weights, features;
    private List<String> lines;

    public Writer() {
        input = new Scanner(System.in);
        calculator = new Calculator();
        lines = new ArrayList<>();
        weights = new ArrayList<>(Arrays.asList(11.0, 33.0, 50.0, 0.4, 4.0));
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
                calculator.getParser().tokens(readText(text));
                numBooks++;

            } else {
                resume = false;
            }
        }
    }
    
    public List<String> readText(String text) {
        try (Stream<String> theLines = Files.lines(Paths.get(text), Charset.forName("UTF-8"))) {
            if (numBooks > 0) {
                lines.addAll(theLines.collect(Collectors.toList()));
            } else {
                lines = theLines.collect(Collectors.toList());
            }
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lines;
    }
    
    public void readSignatures(){
        List<String> tempLines = new ArrayList<>();
        try (Stream<String> theLines = Files.lines(Paths.get("signatures.txt"), Charset.forName("UTF-8"))) {
            tempLines = theLines.collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
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

    public void run() {
        prompt();
        calculator.run();
        features = calculator.getFeatures();
        readSignatures();
        
        System.out.printf("%n%-19s %s%n", " Author ", "| Books |  a1  |  a2  |  a3  |  a4  |  a5  |");
        System.out.printf("----------------------------------------------------------------------%n");
        System.out.printf(" %-18s |   %d   | %.2f | %.2f | %.2f | %.2f | %.2f |%n", author, numBooks, features.get(0), features.get(1), features.get(2), features.get(3), features.get(4));
    }
}
