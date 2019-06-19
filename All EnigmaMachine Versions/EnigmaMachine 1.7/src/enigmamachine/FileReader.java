/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmamachine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version 1.7
 * @author Justin McGettigan
 */
public class FileReader {
    private List<String> lines;
    private File file;
    
    public FileReader(){
        
    }
    
    public FileReader(File f){
        lines = new ArrayList<>();
        file = f;
    }
    
    /**
     * This method reads the imported file.
     * @return A String ArrayList of the imported file's contents.
     */
    public List<String> readFile() {
        try (Stream<String> theText = Files.lines(Paths.get(file.getPath()), Charset.forName("UTF-8"))) {
            lines = theText.collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Something has gone HORRIBLY wrong while reading a file!");
        }
        return lines;
    }
    
    /**
     * This method creates a file and writes
     * the output text to that file.
     * @param lines This parameter is a String ArrayList that will be written to the file line by line.
     */
    public void writeFile(String[] lines){
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getPath()), Charset.forName("UTF-8"))) {
            for(String line : lines){
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException x) {
            System.out.println("Something has gone HORRIBLY wrong while writing a file!");
        }
    }
}
