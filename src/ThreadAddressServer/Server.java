package ThreadAddressServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Server extends Thread{
    
    Database database;
    private Socket clientSocket;
    
    public Server(Socket clientSocket, Database database) throws IOException {
        this.database = database;
        this.clientSocket = clientSocket;
    }
    
    public void run(){
        try( PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));){
            
            String inputLine;
            String outputLine;
            out.println("Vems adress behöver du få veta?");
           
            
            while ((inputLine = in.readLine()) != null) {
                
                System.out.println("Getting message "+inputLine);
                outputLine = database.getAddress(inputLine.trim());
                if (outputLine == null){
                    out.println("Denna person finns inte i databasen");
                }
                else{
                    out.println(outputLine);
                }
            }
        }
        catch(Exception e){
                e.printStackTrace();
        }
    }

}
