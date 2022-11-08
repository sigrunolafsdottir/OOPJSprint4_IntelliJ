package LiveDmo_övn2c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class QuoteSender {
    final static String quote1 = "Dreams and deception is a powerful combination";
    final static String quote2 = "Whatever your're thinking, think bigger.";
    final static String quote3 = "Maybe swearing will help?";

    
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException, InterruptedException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<String> quoteList = new ArrayList<>();
        quoteList.add(quote1);
        quoteList.add(quote2);
        quoteList.add(quote3);
        int listCounter = 0;
        
        InetAddress toAdr = InetAddress.getLocalHost();
        int sendPort = 55555;
        int recPort = 55556;
        DatagramSocket socket = new DatagramSocket(recPort);
        while(true){
            byte[] data = quoteList.get(listCounter).getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, toAdr, sendPort);
            socket.send(packet);
            listCounter = (listCounter + 1) % 3;


            packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);

            Thread.sleep(3000);
        } 
    }
}
