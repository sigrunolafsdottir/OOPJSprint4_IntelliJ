package ÖvnUppg10_Server;

import ÖvnUppg8_AdressBok.Server.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//This class is now only concerned with sending and receiving objects

public class Server extends Thread {
    
    Socket clientSocket;
    
    public Server(Socket clientSocket)throws Exception{
        this.clientSocket = clientSocket;
    }
    
    public void run(){
        try(
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());)
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


}
