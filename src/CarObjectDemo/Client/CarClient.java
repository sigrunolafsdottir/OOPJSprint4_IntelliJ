package CarObjectDemo.Client;

import CarObjectDemo.Server.Car;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

//CHeating using Servers Car-class, because of the package structure
//THe Car classes would not have the same path if i made a copy and put here

public class CarClient {

    CarClient() throws IOException{
        String hostName = "127.0.0.1";  //localhost
       //  String hostName = "172.20.201.122";
        int portNumber = 12345;


    try (
        Socket addressSocket = new Socket(hostName, portNumber);

        ObjectOutputStream out =
                new ObjectOutputStream(addressSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(addressSocket.getInputStream());)
    {
        Object fromServer;
        String fromUser;
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
             

        while ((fromServer = in.readObject()) != null) {

            if (fromServer instanceof String) {
                System.out.println("Server: " + fromServer);
            }
            else if (fromServer instanceof Car) {
                System.out.println("Server: " + ((Car) fromServer).getData());
            }
            
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                out.writeObject(fromUser);
            }
        }
    }
    catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
    } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    }
    
    public static void main(String[] args) throws IOException{
        CarClient c = new CarClient();
    }
    
}

