package MiniDemos.clientsends;


import MiniDemos.serversends.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class SendOnlyClient {
    
    String output = "Kill your darlings";
    
    SendOnlyClient() throws IOException{
        String hostName = "127.0.0.1";  //localhost
       //  String hostName = "172.20.201.122";
        int portNumber = 12345;


    try (
        Socket addressSocket = new Socket(hostName, portNumber);
        PrintWriter out =
            new PrintWriter(
                    addressSocket.getOutputStream(), true);
    )
    {
        while (true) {
            out.println(output);
            Thread.sleep(1000);
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
    catch (Exception e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
    } 
        
    }
    
    public static void main(String[] args) throws IOException{
        SendOnlyClient c = new SendOnlyClient();
    }

}
