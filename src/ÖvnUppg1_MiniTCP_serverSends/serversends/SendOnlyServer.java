package ÖvnUppg1_MiniTCP_serverSends.serversends;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class SendOnlyServer {
    
    String output = "Hej svejs i lingonskogen";
    
    public SendOnlyServer(){
        int portNumber = 12345;

        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            while (true) {          
               out.println(output);
               Thread.sleep(1000);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SendOnlyServer s = new SendOnlyServer();
    }

}
