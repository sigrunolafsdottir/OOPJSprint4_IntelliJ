package LiveDemo_distans_3;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class Receiver extends Thread{

    MulticastSocket ms;
    JTextArea text;

    public Receiver(MulticastSocket ms, JTextArea text){
        this.ms = ms;
        this.text=text;
    }


    @Override
    public void run(){
        while(true){
            byte[] data = new byte[256];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                ms.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String mess = new String(packet.getData(),0, packet.getLength());
            String mess = new String(data,0, data.length);
            text.append(mess);
        }
    }

}
