package LoveDemo_TCP_pp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port = 44444;

    public Server(){

        try(ServerSocket servS = new ServerSocket(port);
            Socket sock = servS.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()))
        ){
            String temp = "";
            while((temp = in.readLine()) != null){
                System.out.println(temp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Server s = new Server();
    }

}
