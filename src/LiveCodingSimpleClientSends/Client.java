package LiveCodingSimpleClientSends;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args){
        int port = 44444;
        InetAddress iAdr = InetAddress.getLoopbackAddress();

        try(Socket socket = new Socket(iAdr, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))){

            String mess;
            String messFromServer;
            while((mess = in.readLine()) != null){
                System.out.println("about to send: " + mess);
                out.println(mess);
                messFromServer = inFromServer.readLine();
                System.out.println(messFromServer);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
