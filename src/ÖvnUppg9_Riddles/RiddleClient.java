package ÖvnUppg9_Riddles;


import java.io.*;
import java.net.*;

public class RiddleClient {
    public static void main(String[] args) throws IOException {
        
        String hostName = "127.0.0.1"; //localhost
        //String hostName = "172.20.200.194"; //localhost
        int portNumber = 44444;

        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("bye"))
                    break;
                
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    //System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
        System.exit(0);
    }
}