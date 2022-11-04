package CarDemoMultiuser.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class CarServerListener {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        while (true) {
            try {
                Socket socketToClient = serverSocket.accept();
                MultiUserCarServer clientHandler = 
                        new MultiUserCarServer(socketToClient);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
