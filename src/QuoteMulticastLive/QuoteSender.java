package QuoteMulticastLive;

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
        
        InetAddress toAdr = InetAddress.getByName("234.235.236.237");
        int toPort = 12540;
        InetSocketAddress socketAdr = new InetSocketAddress(toAdr, toPort);
        NetworkInterface netIf = NetworkInterface.getByName("wlan1");
        MulticastSocket socket = new MulticastSocket(12540);
        socket.joinGroup(socketAdr, null);


        while(true){
            byte[] data = quoteList.get(listCounter).getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, toAdr, toPort);
            socket.send(packet);
            System.out.println("Sending "+quoteList.get(listCounter));
            listCounter = (listCounter + 1) % 3;
            Thread.sleep(3000);
        } 
    }
}
