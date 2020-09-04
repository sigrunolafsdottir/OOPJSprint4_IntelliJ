package ÖvnUppg11_KnockKnockThreads;

import ThreadDemo.TestPrg;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ServerSocketFactory;


public class ServerListener extends Thread {

        private ServerSocket serverSocket;

        ServerListener() throws IOException {
            serverSocket = ServerSocketFactory.getDefault()
                    .createServerSocket(44444);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final Socket socketToClient = serverSocket.accept();
                    ClientHandler clientHandler = 
                            new ClientHandler(socketToClient);
                    clientHandler.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        public static void main(String[] args) throws IOException {
            ServerListener sl = new ServerListener();
            sl.start();
        }
    }
