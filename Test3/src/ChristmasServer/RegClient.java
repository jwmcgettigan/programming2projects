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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class RegClient {
    public static void main(String[] args) {
        BufferedReader in;
        PrintWriter out;
        Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        String serverName = "cop3330.hpc.lab";
        int serverPort = 2016;
        try{
            socket = new Socket(InetAddress.getByName(serverName), serverPort);
            
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to: " + socket.getInetAddress().getHostName());
            
            String serverMessage = "";
            System.out.println("in the process xmas");
            serverMessage = (String) input.readObject();
            System.out.println((String)serverMessage + "\n");
            output.writeObject("color");
            serverMessage = (String) input.readObject();
            System.out.println((String)serverMessage + "\n");
        }catch(UnknownHostException unk){
            System.out.println("Unknown host");
        }catch(IOException io){
            System.out.println("io exception ");
        }catch(ClassNotFoundException onf){
            System.out.println("caught class not found exception while processing XMAS connection");
        }
    }
}
