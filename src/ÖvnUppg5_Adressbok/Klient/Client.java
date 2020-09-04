package ÖvnUppg5_Adressbok.Klient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    Client(){
        String hostName = "127.0.0.1";  //localhost
        int portNumber = 12345;

    try (
        Socket addressSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(addressSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(addressSocket.getInputStream()));
    )
    {
        String fromServer;
        String fromUser;
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
            }
        }
    }
    catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
    } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
    }   
        
    }
    
    public static void main(String[] args){
        Client c = new Client();
    }
    
}
