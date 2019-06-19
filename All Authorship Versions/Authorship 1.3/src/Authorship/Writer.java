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
public class Writer {

    private Scanner input;
    Calculator calculator;
    List<List<String>> text;
    List<List<Double>> booksFeatures;
    List<Double> authorFeatures;
    List<String> books;
    private int numBooks, messups;
    private String author;
    private List<Double> weights, features;

    public Writer() {
        input = new Scanner(System.in);
        calculator = new Calculator();
        text = new ArrayList<>();
        books = new ArrayList<>();
        booksFeatures = new ArrayList<>();
        authorFeatures = new ArrayList<>();
        numBooks = 0;
        weights = new ArrayList<>(Arrays.asList(11.0, 33.0, 50.0, 0.4, 4.0));
        //10, 75, 100, 10, 75
    }

    public void prompt() {
        String book;
        Boolean resume = true;
        System.out.println("Enter MYSTERY if unknown.");
        System.out.printf("Author? ");
        author = input.nextLine();
        System.out.printf("%nDo not include the file extension; enter STOP if done.%n");
        while (resume) {
            System.out.printf("Book? ");
            book = input.nextLine();
            if (book.matches("[\\s\\w]+")) {
                if (!book.equals("STOP")) {
                    book += ".txt";
                    if (author.equals("MYSTERY")) {
                        resume = false;
                    }
                    if (readBook(book)) {
                        calculator.getParser().tokens(text.get(numBooks++));
                        calculator.run();
                        booksFeatures.add(calculator.getFeatures());
                    }
                } else {
                    resume = false;
                }
            } else {
                messups();
                messups++;
            }
        }
    }

    public void messups() {
        if (messups == 0) {
            System.out.println("I'll pretend I didn't see that.");
        } else if (messups == 1) {
            System.out.println("I can only keep pretending for so long.");
        } else if (messups == 2) {
            System.out.println("You are a disappointment.");
        } else if (messups == 3) {
            System.out.println("You're doing this on purpose.");
        } else if (messups == 4) {
            System.out.println("I'm going to ignore you now.");
        } else {
            System.out.println("...");
        }
    }

    public boolean readBook(String book) {
        try (Stream<String> theText = Files.lines(Paths.get(book), Charset.forName("UTF-8"))) {
            books.add(book);
            text.add(theText.collect(Collectors.toList()));
            return true;
        } catch (IOException ex) {
            messups();
            messups++;
            return false;
        }
    }

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

    public void features() {
        double num;
        for (int i = 0; i < booksFeatures.get(0).size(); i++) {
            num = 0;
            for (List<Double> bookFeature : booksFeatures) {
                num += bookFeature.get(i);
            }
            authorFeatures.add(num / booksFeatures.size());
        }
    }

    public void run() {
        prompt();
        features();
        readSignatures();
        print();
    }

    public void print() {
        System.out.println("---------------------------------------");
        System.out.printf("| %24s %12s%n", author, "|");
        System.out.println("---------------------------------------");
        for (int i = 0; i < numBooks; i++) {
            System.out.printf("| %24s %12s%n", books.get(i), "|");
            System.out.printf("| %25s %9.6f |%n", "Average Word Length: ", booksFeatures.get(i).get(0));
            System.out.printf("| %25s %9.6f |%n", "Type Token Ratio: ", booksFeatures.get(i).get(1));
            System.out.printf("| %25s %9.6f |%n", "Hapax Legomana Ratio: ", booksFeatures.get(i).get(2));
            System.out.printf("| %25s %9.6f |%n", "Avg # of Words/Sentence: ", booksFeatures.get(i).get(3));
            System.out.printf("| %25s %9.6f |%n", "Sentence Complexity: ", booksFeatures.get(i).get(4));
            System.out.println("---------------------------------------");
        }
        System.out.printf("| %23s %13s%n", "OVERALL", "|");
        System.out.printf("| %25s %9.6f |%n", "Average Word Length: ", authorFeatures.get(0));
        System.out.printf("| %25s %9.6f |%n", "Type Token Ratio: ", authorFeatures.get(1));
        System.out.printf("| %25s %9.6f |%n", "Hapax Legomana Ratio: ", authorFeatures.get(2));
        System.out.printf("| %25s %9.6f |%n", "Avg # of Words/Sentence: ", authorFeatures.get(3));
        System.out.printf("| %25s %9.6f |%n", "Sentence Complexity: ", authorFeatures.get(4));
        System.out.println("---------------------------------------");
    }
}
