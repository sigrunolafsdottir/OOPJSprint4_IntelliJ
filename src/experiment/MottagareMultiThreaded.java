package experiment;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.List;


public class MottagareMultiThreaded implements Runnable{

    Thread aktivitet = new Thread(this);
    MulticastSocket so;
    JTextArea txt;

    JTextArea memberArea;
    List<String> members;
    
    MottagareMultiThreaded (MulticastSocket sock, JTextArea txtAr, JTextArea memberArea, List<String> members){
        so = sock;
        txt = txtAr;
        this.members = members;
        this.memberArea = memberArea;
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
                    System.out.println(medd);
                    if (medd.endsWith("UPPKOPPLAD".trim())){
                        members.add(medd.split(":")[0]);
                        updateMemberArea();
                    }
                    else if (medd.endsWith("NEDKOPPLAD".trim())){
                        members.remove(medd.split(":")[0]);
                        updateMemberArea();
                    }
                }
            }
            catch (IOException e){
                break;
            }
        }
    }

    private void updateMemberArea(){
        String memberText = "I chatten just nu: \n";
        for (String n: members){
            memberText += n + "\n";
        }
        memberArea.setText(memberText);
    }
}
