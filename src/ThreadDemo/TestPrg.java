package ThreadDemo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ServerSocketFactory;


public class TestPrg {

    public static void main(String... args) throws IOException {
        ServerListener server = new ServerListener();
        server.start();

        Socket socketToServer = new Socket("localhost", 15000);
        ObjectOutputStream outStream = new ObjectOutputStream(socketToServer.getOutputStream());

        for (int i=1; i<10; i++) {
            try {
                Thread.sleep((long) (Math.random()*3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sending object to server ...");
            outStream.writeObject("test message #"+i);
        }
        System.exit(0);

    }

    static class ServerListener extends Thread {

        private ServerSocket serverSocket;

        ServerListener() throws IOException {
            serverSocket = ServerSocketFactory.getDefault().createServerSocket(15000);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final Socket socketToClient = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(socketToClient);
                    clientHandler.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ClientHandler extends Thread{
        private Socket socket;
        ObjectInputStream inputStream;

        ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            inputStream = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Object o = inputStream.readObject();
                    System.out.println("Read object: "+o);
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}