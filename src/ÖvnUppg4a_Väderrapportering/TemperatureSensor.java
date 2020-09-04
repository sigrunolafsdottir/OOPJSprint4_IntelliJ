package ÖvnUppg4a_Väderrapportering;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class TemperatureSensor {
    
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException, InterruptedException{
        
        String temperature = "";
        String dataToSend = "";
        Scanner sc = new Scanner(System.in);
        String city = JOptionPane.showInputDialog(null, "Ange stad");
        if (city == null || city.length() == 0){  //tar hand om Cancel-tryck
            System.exit(0); 
        }
        
        InetAddress toAdr = InetAddress.getLocalHost();
        int toPort = 55555;
        DatagramSocket socket = new DatagramSocket();
        while(sc.hasNext()){
            temperature = sc.next();
            dataToSend = city+","+temperature;
            byte[] data = dataToSend.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, toAdr, toPort);
            socket.send(packet);
        } 
    }
}
