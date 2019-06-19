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
 *
 * @author Justin
 */
public class FileReader {
    private List<String> lines;
    private String file;
    
    public FileReader(){
        
    }
    
    public FileReader(String f){
        lines = new ArrayList<>();
        file = f;
    }
    
    /**
     * 
     * @return 
     */
    public List<String> readFile() {
        try (Stream<String> theText = Files.lines(Paths.get(file), Charset.forName("UTF-8"))) {
            lines = theText.collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Something has gone HORRIBLY wrong!");
        }
        return lines;
    }
    
    /**
     * 
     * @param lines
     * @param file 
     */
    public void writeFile(String[] lines, File file){
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getPath()), Charset.forName("UTF-8"))) {
            for(String line : lines){
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
