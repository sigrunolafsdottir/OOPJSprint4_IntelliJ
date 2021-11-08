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


public class ChatMultiThreaded extends JFrame implements ActionListener {

    String namn;
    InetAddress iadr;
    int port;
    MulticastSocket so;
    JTextArea txt = new JTextArea();
    JScrollPane sp = new JScrollPane(txt);
    JTextField skriv = new JTextField();
    JButton sluta = new JButton("Koppla ner");
    InetSocketAddress group;
    NetworkInterface netIf = NetworkInterface.getByName("wlan2");
    
    public ChatMultiThreaded(String användarnamn, 
            String gruppadr, int portNr) throws IOException{
        namn = användarnamn;
        iadr = InetAddress.getByName(gruppadr);
        port = portNr;
        group = new InetSocketAddress(iadr, port);
        
        so = new MulticastSocket(port);
        so.joinGroup(group, netIf);
        new MottagareMultiThreaded(so, txt);
        
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
    }
    
    public static void main (String[] args) throws IOException{
        String namn = "Nisse";
        //Chat c = new Chat(namn, "234.235.236.237", 12540);
        ChatMultiThreaded c = new ChatMultiThreaded(namn, 
                "234.235.236.237", 12540);
    }
}
