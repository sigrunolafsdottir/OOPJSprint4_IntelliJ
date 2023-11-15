package CarObjectDemo.Server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class CarServer {
    
    Database d = new Database();
    
    public CarServer(){
        int portNumber = 12345;

        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            ObjectOutputStream out =
                new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ) {
            Object input;
            Object output;
            
            out.writeObject("Vilken bil vill du slå upp?");
            
            while ((input = in.readObject()) != null) {
                if (input instanceof String s) {
                    output = d.getCarData(s.trim());
                    if (output == null) {
                        out.writeObject("Denna bil finns inte i databasen: " + input);
                    } else {
                        out.writeObject(output);
                    }
                }
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
