package ÖvnUppg1_MiniTCP_serverSends.serversends;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class ReceiveOnlyClient {
    
    ReceiveOnlyClient() throws IOException{
        String hostName = "127.0.0.1";  //localhost
        int portNumber = 12345;

        try (
            Socket addressSocket = new Socket(hostName, portNumber);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(addressSocket.getInputStream()));
        )
        {
            String fromServer;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        ReceiveOnlyClient c = new ReceiveOnlyClient();
    }

}
