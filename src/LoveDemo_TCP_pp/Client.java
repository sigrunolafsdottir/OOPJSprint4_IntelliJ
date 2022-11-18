package LoveDemo_TCP_pp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    int port = 44444;
    String mess = "Hej svejs";

    public Client() throws UnknownHostException {
        InetAddress ip = InetAddress.getByName("127.0.0.1");

        try(Socket sock = new Socket(ip, port);
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));){

            String fromServer = "";

            while((fromServer = in.readLine()) != null) {
                out.println(mess);
               // fromServer = in.readLine();
                System.out.println(fromServer);
                Thread.sleep(3000);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws UnknownHostException {
        Client c = new Client();
    }
}
