package ChatDemo2Parts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    
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
            
            Receiver rec = new Receiver(in, "Klient");
            rec.start();
            
             BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            
            out.println("hej och välkommen");
            
            //Sändandet sker här
            while ((inputLine = stdIn.readLine()) != null) {          
                out.println(inputLine);
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
