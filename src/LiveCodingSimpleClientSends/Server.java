package LiveCodingSimpleClientSends;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {



    public static void main(String[] args){
        int port = 44444;

        try(ServerSocket listener = new ServerSocket(port);
            Socket socket = listener.accept();
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))){

            String mess = "aaaaa";
            System.out.println("About to wait for client messages");


            while((mess = in.readLine()) != null){
                System.out.println("Received from client: "+mess);
                out.println("Message received "+mess);
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }


    }

}
