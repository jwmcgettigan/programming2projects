/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class RegClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        System.out.println("test");
        try {
            socket = new Socket("127.0.0.1", 9898);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String serverResponse = in.readLine();
            System.out.println(serverResponse);
            serverResponse = in.readLine();
            System.out.println("The server said " + serverResponse);
            in.readLine();
            
            out.println("money");
            serverResponse = in.readLine();
            System.out.println("The stupid server changed my input to: " + serverResponse);
        } catch (Exception ex) {}
        
    }
}
