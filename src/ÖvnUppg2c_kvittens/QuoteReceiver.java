package ÖvnUppg2c_kvittens;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class QuoteReceiver  {
    
    public static void main(String[] args) throws SocketException, IOException{
        InetAddress toAdr = InetAddress.getLocalHost();
        int recPort = 55555;
        int sendPort = 55556;
        DatagramSocket recSocket = new DatagramSocket(recPort);
        DatagramSocket sendSocket = new DatagramSocket();
        byte[] data = new byte[256];
        String kvittens = "Message Received";
        while(true){
            DatagramPacket packet = new DatagramPacket(data, data.length);
            recSocket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);

            packet =  new DatagramPacket(kvittens.getBytes(), kvittens.getBytes().length,toAdr, sendPort );
            sendSocket.send(packet);
            System.out.println("kvittens skickad");
        }
    }
}
