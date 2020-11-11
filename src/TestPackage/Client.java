package TestPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args){
        InetAddress inetAddress = InetAddress.getLoopbackAddress();

        try(Socket socketToServer = new Socket(inetAddress, 55555);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader inFromServer = new BufferedReader(
                    new InputStreamReader(socketToServer.getInputStream()));
            PrintWriter out = new PrintWriter(socketToServer.getOutputStream(), true);){

            String userWrites;
            String serverWrites;
            while((userWrites = in.readLine()) != null){
                out.println(userWrites);

                serverWrites = inFromServer.readLine();
                System.out.println("Received message from server: "+serverWrites);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
