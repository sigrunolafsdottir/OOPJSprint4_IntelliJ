package CarDemoProtocol.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class CarServer {
    
    public CarServer(){
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
            
            CarProtokoll protocol = new CarProtokoll();
            out.println(protocol.processInput(null));
            
            while ((inputLine = in.readLine()) != null) {          
               out.println(protocol.processInput(inputLine));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        CarServer s = new CarServer();
    }
}
