package TestPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){

        try(ServerSocket lyssnarSock = new ServerSocket(55555);
            Socket socketToClient = lyssnarSock.accept();
            PrintWriter out = new PrintWriter(socketToClient.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socketToClient.getInputStream()));){

            String mess;
            while((mess = in.readLine()) != null){
                System.out.println("Received message "+mess);
                out.println("message received");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
