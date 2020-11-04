package ÖvnUppg2c_TrådadeCitat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class QuoteReceiverThreaded implements Runnable {

    String s;

    public QuoteReceiverThreaded(String s){
        this.s = s;
    }
    
    public void run(){
        int minPort = 55555;
        try (DatagramSocket socket = new DatagramSocket(minPort);) {
            byte[] data = new byte[256];
            while (true) {
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println(message);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
