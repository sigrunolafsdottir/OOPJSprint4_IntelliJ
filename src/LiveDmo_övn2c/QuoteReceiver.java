package LiveDmo_övn2c;

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
        DatagramSocket socket = new DatagramSocket(recPort);
        byte[] data = new byte[256];
        while(true){
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);

            String kvittens = "meddelande mottaget";
            byte[] kvittensBA = kvittens.getBytes();
            packet = new DatagramPacket(kvittensBA, kvittensBA.length, toAdr, sendPort);
            socket.send(packet);
            System.out.println("kvittens sent");
        }
    }
}
