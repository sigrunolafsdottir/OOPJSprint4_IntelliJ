package ÖvnUppg4b_Väderrapportering;


import java.io.IOException;
import java.net.*;

public class TemperatureCollector  {
    
    public static void main(String[] args) throws SocketException, IOException{
        String ip = "234.235.236.237";
        InetAddress iadr = InetAddress.getByName(ip);
        int port = 12540;
        InetSocketAddress group = new InetSocketAddress(iadr, port);
        NetworkInterface netIf = NetworkInterface.getByName("wlan2");
        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(group, netIf);

        byte[] data = new byte[256];
        while(true){
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);
        }
    }
}
