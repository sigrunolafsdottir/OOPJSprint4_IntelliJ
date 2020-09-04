package ÖvnUppg4b_Väderrapportering;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class TemperatureSensor {
    
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException, InterruptedException{
        
        String temperature = "";
        String dataToSend = "";
        String ip = "234.235.236.237";
        InetAddress iadr = InetAddress.getByName(ip);
        int port = 12540;
        Scanner sc = new Scanner(System.in);
        String city = JOptionPane.showInputDialog(null, "Ange stad");
        if (city == null || city.length() == 0){  //tar hand om Cancel-tryck
            System.exit(0); 
        }
        
        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(iadr);
        
        while(sc.hasNext()){
            temperature = sc.next();
            dataToSend = city+","+temperature;
            byte[] data = dataToSend.getBytes();
            DatagramPacket packet= new DatagramPacket(data, data.length, iadr, port);
            socket.send(packet);
        } 
    }
}
