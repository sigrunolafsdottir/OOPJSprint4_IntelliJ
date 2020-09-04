package ThreadAddressServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ServerSocketFactory;
import ÖvnUppg11_KnockKnockThreads.ClientHandler;


public class ServerListener extends Thread{
    
    Database database = new Database();
    private ServerSocket serverSocket;
    
     ServerListener() throws IOException {
            serverSocket = new ServerSocket(12345);
    }

        @Override
        public void run() {
            while (true) {
                try {
                    final Socket socketToClient = serverSocket.accept();
                    Server clientHandler = new Server(socketToClient,
                            database);
                    clientHandler.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public static void main(String[] args) throws IOException {
        ServerListener s = new ServerListener();
        s.start();
    }
}
