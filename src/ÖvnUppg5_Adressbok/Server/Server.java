package ÖvnUppg5_Adressbok.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    
    DAO d = new DAO();
    
    public Server(){
        int portNumber = 12345;

        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            String outputLine;
            
            out.println("Vems adress behöver du få veta?");
            
            while ((inputLine = in.readLine()) != null) {
                
                System.out.println("Getting message "+inputLine);
                outputLine = d.getAddress(inputLine.trim());
                if (outputLine == null){
                    out.println("Denna person finns inte i databasen");
                }
                else{
                    out.println(outputLine);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server s = new Server();
    }
}
