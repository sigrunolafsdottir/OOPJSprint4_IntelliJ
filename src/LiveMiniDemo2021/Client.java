package LiveMiniDemo2021;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public Client(){

        int port = 33333;
        String ip = "127.0.0.1";
        String temp;
        Object tempFromServer;

        try (Socket sock = new Socket(ip, port);
             PrintWriter writeToServer = new PrintWriter(sock.getOutputStream(), true);
             BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in));
             ObjectInputStream sockRead = new ObjectInputStream(sock.getInputStream());
        ){

            while((temp = bufRead.readLine()) != null){
                writeToServer.println(temp);
                tempFromServer = sockRead.readObject();
                if(tempFromServer instanceof Response){
                    System.out.println(((Response)tempFromServer).getRes());
                }
               else{
                    System.out.println("Okänt objekt påträffades");
                }
            }



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args){
        new Client();
    }

}
