package ChatDemo2Parts;

import CarDemo.Client.CarClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    
    Client() throws IOException{
        String hostName = "127.0.0.1";  //localhost
       //  String hostName = "172.20.201.122";
        int portNumber = 12345;


    try (
        Socket addressSocket = new Socket(hostName, portNumber);
            
        PrintWriter out = new PrintWriter(addressSocket.getOutputStream(),
                true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(addressSocket.getInputStream()));
    )
    {
        String inputLine;
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
             
        //out.println("Vilken bil vill du slå upp");
        
        Receiver rec = new Receiver(in, "Server");
            rec.start();
        
        while ((inputLine = stdIn.readLine()) != null) {          
                out.println(inputLine);
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
    
    public static void main(String[] args) throws IOException{
        Client c = new Client();
    }

}
