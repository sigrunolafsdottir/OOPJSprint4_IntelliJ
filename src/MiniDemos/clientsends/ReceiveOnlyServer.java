package MiniDemos.clientsends;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class ReceiveOnlyServer {
    
    
    public ReceiveOnlyServer(){
        int portNumber = 12345;
        

        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            
           
            while (true) {  
                System.out.println(in.readLine());
                
                
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ReceiveOnlyServer s = new ReceiveOnlyServer();
    }

}
