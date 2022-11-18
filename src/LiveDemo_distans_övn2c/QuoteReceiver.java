package LiveDemo_distans_övn2c;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class QuoteReceiver  {
    
    public static void main(String[] args) throws SocketException, IOException{
        int minPort = 45555;
        int sendPort = 45556;
        InetAddress toAdr = InetAddress.getLocalHost();
        try (DatagramSocket socket = new DatagramSocket(minPort);) {
            byte[] data = new byte[256];
            while (true) {
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println(message);

                //TODO skicka kvittens
                String kvittens = "kvittens på " + message;
                packet = new DatagramPacket(kvittens.getBytes(), kvittens.getBytes().length, toAdr, sendPort);
                socket.send(packet);
                System.out.println("kvittens sent");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
