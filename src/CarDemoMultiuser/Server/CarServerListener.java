package CarDemoMultiuser.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class CarServerListener {

    public static void main(String[] args) throws IOException {

        try(ServerSocket serverSocket = new ServerSocket(12345);) {

            while (true) {
                Socket socketToClient = serverSocket.accept();

                MultiUserCarServer clientHandler = new MultiUserCarServer(socketToClient);
                clientHandler.start();

            }
        }
    }
}
