package ThreadAddressClient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Client extends Thread{

    protected String hostName = "127.0.0.1";  //localhost
    protected int portNumber = 12345;
    protected String name;
    
    public Client(String name){
        this.name = name;
    }
    
    
    @Override
    public void run(){

    try (
        Socket addressSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(addressSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(addressSocket.getInputStream()));
    )
    {
        String fromServer;
        String fromUser;
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
           
        while ((fromServer = in.readLine()) != null) {
            System.out.println(name +" Server: " + fromServer);
            
            fromUser = JOptionPane.showInputDialog(null, name+" Vems adress vill du veta?" );
            
            if (fromUser != null) {
                System.out.println(name +" Client: " + fromUser);
                out.println(fromUser);
            }
        }
    }
    catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            e.printStackTrace();
            interrupt();
    } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            e.printStackTrace();
            interrupt();
    }   
        
    }

    
    public static void main(String[] args){
        Client c1 = new Client("Två");
        //Client c2 = new Client("Två");
        c1.start();
       // c2.start();
    }
    
}
