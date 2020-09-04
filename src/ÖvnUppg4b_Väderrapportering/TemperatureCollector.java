package ÖvnUppg4b_Väderrapportering;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class TemperatureCollector  {
    
    public static void main(String[] args) throws SocketException, IOException{
        String ip = "234.235.236.237";
        InetAddress iadr = InetAddress.getByName(ip);
        int port = 12540;
        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(iadr);
        byte[] data = new byte[256];
        while(true){
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);
        }
    }
}
