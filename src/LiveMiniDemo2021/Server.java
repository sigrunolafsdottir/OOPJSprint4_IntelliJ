package LiveMiniDemo2021;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port = 33333;

    public Server() {

        String temp;

        try (
                ServerSocket serverSock = new ServerSocket(port);
                Socket sock = serverSock.accept();
                ObjectOutputStream writeToClient = new ObjectOutputStream(sock.getOutputStream());
                BufferedReader bufRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        ) {

            while((temp = bufRead.readLine()) != null){
                System.out.println(temp);
                Response res = new Response();
                res.setRes("Sträng mottagen");
                writeToClient.writeObject(res);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Server();
    }
}
