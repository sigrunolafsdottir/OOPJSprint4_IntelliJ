package ÖvnUppg3_Klasschatt;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import javax.swing.JTextArea;


public class MottagareMultiThreaded implements Runnable{

    Thread aktivitet = new Thread(this);
    MulticastSocket so;
    JTextArea txt;
    
    MottagareMultiThreaded (MulticastSocket sock, JTextArea txtAr){
        so = sock;
        txt = txtAr;
        aktivitet.start();
    }
    
    public void run(){
        byte[] data = new byte[1024];
        while(true){
            try{
                DatagramPacket packet = new DatagramPacket(data, data.length);
                so.receive(packet);
                String medd = new String(data, 0, packet.getLength());
                synchronized(this) {
                    txt.append(medd +"\n");
                }
            }
            catch (IOException e){
                break;
            }
        }
    }
}
