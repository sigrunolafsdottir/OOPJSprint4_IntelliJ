package ÖvnUppg10_Server;

import CarDemoMultiuser.Server.MultiUserCarServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerHandler {

    public static void main(String[] args) throws Exception {
        try(ServerSocket serverSocket = new ServerSocket(12345);) {
            while (true) {
                Socket socketToClient = serverSocket.accept();
                Server clientHandler = new Server(socketToClient);
                clientHandler.start();
            }
        }
    }
}
