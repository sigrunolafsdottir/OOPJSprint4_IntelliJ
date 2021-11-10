package ConsoleMultiuserChatDemo.Server;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Kod modifierad utifrån exemplen på http://cs.lmu.edu/~ray/notes/javanetexamples/


public class ServerListener {
    
    private static MultiWriter multiWriter = new MultiWriter();

    public static void main(String[] args) throws IOException {
         while (true) {
            try (ServerSocket serverSocket = new ServerSocket(12345);){
                final Socket socketToClient = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socketToClient, multiWriter);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}