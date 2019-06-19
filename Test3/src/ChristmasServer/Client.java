/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChristmasServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Justin
 */
public class Client {
    
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private Boolean christmas = false;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String localHost = "127.0.0.1";
    private int localPort = 9898;
    private String serverName = "cop3330.hpc.lab";
    private int serverPort = 2016;
    
    //private Button localButton, xmasButton;
    
    //private TextArea message;
    
    //private TextField data;
    
    public void xmasConnect(){
        christmas = true;
        connectToServer();
        getStreams();
        processXmas();
        talkToServer();
    }
    
    public void processXmas(){
        String serverMessage = "";
        System.out.println("in the process xmas");
        
        try{
            serverMessage = (String) input.readObject();
            System.out.println((String)serverMessage + "\n");
        }catch(IOException io){
            System.out.println("caught an IO exception while processing XMAS connection");
        }catch(ClassNotFoundException onf){
            System.out.println("caught class not found exception while processing XMAS connection");
        }
    }
    
    
    public void talkToServer(){
        try {
            output.writeObject("color");
        } catch (IOException ex) {
            
        }
    }
    
    public void connectToServer(){
        System.out.println("Attempting connection\n");
        try{
            if(!christmas){
                socket = new Socket(localHost, localPort);
            } else{
                socket = new Socket(InetAddress.getByName(serverName), serverPort);
            }
        }catch(UnknownHostException unk){
            System.out.println("Unknown host");
        }catch(IOException io){
            System.out.println("io exception ");
        }
        System.out.println("Connected young jedi");
    }
    
    public void getStreams(){
        try{
            if(!christmas){
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                System.out.append("\nGot local I/O streams\n");
            } else {
                output = new ObjectOutputStream(socket.getOutputStream());
                output.flush();
                
                input = new ObjectInputStream(socket.getInputStream());
                
                System.out.println("Connected to: " + socket.getInetAddress().getHostName());
            }
        }catch(IOException io){
            System.out.println("IO exception when establishing streams");
        }
    }
    
}
