package QuoteMulticastLive;

import java.io.IOException;
import java.net.*;

public class QuoteReceiver  {
    
    public static void main(String[] args) throws SocketException, IOException{

        int minPort = 12540;
        InetAddress fromAdr = InetAddress.getByName("234.235.236.237");
        InetSocketAddress socketAdr = new InetSocketAddress(fromAdr, minPort);
        NetworkInterface netIf = NetworkInterface.getByName("wlan1");
        MulticastSocket socket = new MulticastSocket(12540);
        socket.joinGroup(socketAdr, null);

        byte[] data = new byte[256];
        while(true){
            DatagramPacket packet = new DatagramPacket(data, data.length);
            System.out.println("receiving");
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);
        }
    }
}
