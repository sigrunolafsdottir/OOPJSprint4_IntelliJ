
package ÖvnUppg6_Adressbok.Klient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import ÖvnUppg5_Adressbok.Server.Kompis; //



public class ObjectClient {

    ObjectClient() throws Exception{
        String hostName = "127.0.0.1";  //localhost
        int portNumber = 12345;
  
        try(
        Socket addressSocket = new Socket(hostName, portNumber);
        ObjectOutputStream oos = new ObjectOutputStream(
                addressSocket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(
                addressSocket.getInputStream());){
           
            Kompis fromServer;
            String fromUser;
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));


            while ((fromServer = (Kompis)ois.readObject()) != null) {

                System.out.println("Server: " + fromServer.getAddress());

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    oos.writeObject(fromUser);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) throws Exception{
        ObjectClient c = new ObjectClient();
    }
    
}
