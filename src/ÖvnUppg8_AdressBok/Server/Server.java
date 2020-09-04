package ÖvnUppg8_AdressBok.Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//This class is now only concerned with sending and receiving objects

public class Server {
    
    
    public Server()throws Exception{
        int portNumber = 12345;
        try(
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket
                    .getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket
                    .getInputStream());)
        {                      
            String inputLine;           
            Protokoll protocol = new Protokoll();
            oos.writeObject(protocol.processInput(null));
                    
            //servar frågeloopen
           while ((inputLine = (String)ois.readObject()) != null) {              
               oos.writeObject(protocol.processInput(inputLine));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws Exception{
        Server s = new Server();
    }
}
