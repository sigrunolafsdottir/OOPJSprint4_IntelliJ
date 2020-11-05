package ÖvnUppg3_Klasschatt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

//This solution is not really single thread, it only looks that way.
//Works because actionPerformed is executed in the Event Dispatch Thread
//the rest of the code is not.


public class ChatSingleThread extends JFrame implements ActionListener {

    String namn;
    InetAddress iadr;
    int port;
    MulticastSocket so;
    JTextArea txt = new JTextArea();
    JScrollPane sp = new JScrollPane(txt);
    JTextField skriv = new JTextField();
    JButton sluta = new JButton("Koppla ner");
    Timer timer = new Timer(10000, this);
    InetSocketAddress group;
    NetworkInterface netIf = NetworkInterface.getByName("wlan1");

    public ChatSingleThread(String användarnamn, 
            String gruppadr, int portNr) throws IOException{
        namn = användarnamn;
        iadr = InetAddress.getByName(gruppadr);
        port = portNr;
        group = new InetSocketAddress(iadr, port);
        
        so = new MulticastSocket(port);

        so.joinGroup(group, netIf);
        sändMedd("UPPKOPPLAD");
        
        setTitle("Chat "+namn);
        txt.setEditable(false);
        add(sluta, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(skriv, BorderLayout.SOUTH);
        sluta.addActionListener(this);
        skriv.addActionListener(this);
        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        timer.start();
        
        byte[] data = new byte[1024];
        while(true){
            try{
//                System.out.println("received, "
//                        + "is EventDispatchThread: "
//        + javax.swing.SwingUtilities.isEventDispatchThread());
                DatagramPacket packet = new DatagramPacket(data, 
                        data.length);
                so.receive(packet);
                String medd = new String(data, 0, 
                        packet.getLength());
                    txt.append(medd +"\n");
            }
            catch (IOException e){
                break;
            }
        }
    }
    
    private void sändMedd(String s){
        
        byte[] data = (namn + ": " +s).getBytes();
        DatagramPacket packet= new DatagramPacket(data, 
                data.length, iadr, port);
        try{
                so.send(packet);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent e){
        System.out.println("ActionPerformed, is EventDispatchThread:"
                + javax.swing.SwingUtilities.isEventDispatchThread());
        if(e.getSource() == skriv){
            sändMedd(skriv.getText());
            skriv.setText("");
        }
        else if(e.getSource() == sluta){
            sändMedd("NEDKOPPLAD");
            try {
                so.leaveGroup(group, netIf);
            }
            catch (IOException ie){
                so.close();
                dispose();
                System.exit(0);
            }
        }
        else {
            sändMedd("Hej allesammans");
        }
    }
    
    public static void main (String[] args) throws IOException{
        String namn = "Sigrun";
        ChatSingleThread c = new ChatSingleThread(namn, 
        //        "234.235.236.237", 12540);
                "230.1.1.1", 12345);
    }
}
