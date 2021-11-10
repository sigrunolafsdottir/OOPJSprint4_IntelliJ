package ConsoleMultiuserChatDemo.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientHandler  extends Thread{

    Socket clientSocket;
    MultiWriter mw;

    public ClientHandler(Socket clientSocket, MultiWriter mw){
        this.clientSocket = clientSocket;
        this.mw = mw;
    }
    
    public void run(){

        try ( 
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            mw.addWriter(out);
            out.println("Hej och välkommen");
            
            //Sändandet sker här
            while ((inputLine = in.readLine()) != null) {
                for (PrintWriter writer : mw.getWriters()) {
                    writer.println(inputLine);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
