package CarDemo.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;



public class CarClient {

    CarClient() throws IOException{
        String hostName = "127.0.0.1";  //localhost
       //  String hostName = "172.20.201.122";
        int portNumber = 12345;


    try (
        Socket addressSocket = new Socket(hostName, portNumber);
            
        PrintWriter out = new PrintWriter(addressSocket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(addressSocket.getInputStream()));
    )
    {
        String fromServer;
        String fromUser;
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
             
        //out.println("Vilken bil vill du slå upp");
        
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                out.println(fromUser);
            }
        }
    }
    catch (UnknownHostException e) {
        e.printStackTrace();
            System.exit(1);
    } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
    }   
        
    }
    
    public static void main(String[] args) throws IOException{
        CarClient c = new CarClient();
    }
    
}

