/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authorship;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @project Authorship 1.4
 * @author Justin McGettigan
 */
public class Library {

    private Scanner input;
    private List<Book> bookObjects;
    private List<Features> features;
    private List<List<Double>> booksFeatures;
    private List<Double> authorFeatures;
    private List<String> bookNames;
    private Writer writer;
    private int numBooks, messups;
    private String author;
    private boolean restart;

    public Library() {
        input = new Scanner(System.in);
        bookObjects = new ArrayList<>();
        features = new ArrayList<>();
        booksFeatures = new ArrayList<>();
        bookNames = new ArrayList<>();
        numBooks = 0;
        messups = 0;
    }

    /**
     * This method 
     */
    public void run() {
        String book;
        boolean resume = true;
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
                    readBook(book);
                    if (author.equals("MYSTERY")) {
                        resume = process();
                    }
                } else {
                    resume = process();
                }
            } else {
                messups();
                messups++;
            }
        }
    }

    /**
     * This method creates all of the class objects, except for Book,
     * and calls the corresponding methods that are necessary for 
     * the program to run successfully.
     */
    public boolean process() {
        for (Book bk : bookObjects) {
            bk.run();
            features.add(new Features(bk.getWords(), bk.getSentences()));
        }
        int i = 0;
        for (Features ftrs : features) {
            long start = System.nanoTime();
            ftrs.run();
            booksFeatures.add(ftrs.getFeatures());
            System.out.printf(bookNames.get(i) + " (" + new DecimalFormat("#.####").format((System.nanoTime() - start) / 1000000000.0) + "s)%n");
            i++;
        }
        writer = new Writer(author, booksFeatures, numBooks);
        writer.run();
        authorFeatures = writer.getAuthorFeatures();
        print();
        return false;
    }

    /**
     * This method reads the named file and adds the file's contents to
     * the bookObjects list as well as adds the book's name to the
     * bookNames list; if the file is not found, the user will be informed
     * through the messups() method.
     * @param book This parameter is the file that needs to be read.
     */
    public void readBook(String book) {
        try (Stream<String> theText = Files.lines(Paths.get("./library/" + book), Charset.forName("UTF-8"))) {
            bookObjects.add(new Book(theText.collect(Collectors.toList())));
            bookNames.add(book);
            numBooks++;
        } catch (IOException ex) {
            messups();
            messups++;
        }
    }

    /**
     * This method is used to inform the user that
     * they their input is not valid.
     */
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

    /**
     * This method prints the features of the read books
     * and their author.
     */
    public void print() {
        System.out.println("---------------------------------------");
        System.out.printf("| %24s %12s%n", author, "|");
        System.out.println("---------------------------------------");
        for (int i = 0; i < numBooks; i++) {
            System.out.printf("| %24s %12s%n", bookNames.get(i), "|");
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
