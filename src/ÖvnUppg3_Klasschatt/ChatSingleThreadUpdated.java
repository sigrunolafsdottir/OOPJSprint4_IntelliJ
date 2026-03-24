package ÖvnUppg3_Klasschatt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

//This solution is not really single thread, it only looks that way.
//Works because actionPerformed is executed in the Event Dispatch Thread
//the rest of the code is not.


public class ChatSingleThreadUpdated extends JFrame implements ActionListener {

    String namn;
    InetAddress iadr;
    int port;
    MulticastSocket so;
    JTextArea txt = new JTextArea();
    JScrollPane sp = new JScrollPane(txt);
    JTextField skriv = new JTextField();
    Timer timer = new Timer(10000, this);
    InetSocketAddress group;
    NetworkInterface netIf = NetworkInterface.getByInetAddress(
            InetAddress.getLocalHost()
    );

    public ChatSingleThreadUpdated(String användarnamn,
                                   String gruppadr, int portNr) throws IOException{
        namn = användarnamn;
        iadr = InetAddress.getByName(gruppadr);
        port = portNr;
        group = new InetSocketAddress(iadr, port);

        setTitle("Chat "+namn);
        txt.setEditable(false);
        add(sp, BorderLayout.CENTER);
        add(skriv, BorderLayout.SOUTH);
        skriv.addActionListener(this);
        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        so = new MulticastSocket(port);
        so.joinGroup(group, netIf);
        sändMedd("UPPKOPPLAD");
        timer.start();
        byte[] data = new byte[1024];

        Thread.startVirtualThread(() -> {

            while (true) {
                try {
                    DatagramPacket packet = new DatagramPacket(data,
                            data.length);
                    so.receive(packet);
                    String medd = new String(data, 0, packet.getLength());
                    SwingUtilities.invokeLater(() ->
                            txt.append(medd + "\n")
                    );
                } catch (IOException e) {
                    break;
                }
            }
        });
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
        else {
            sändMedd("Hej allesammans");
        }
    }
    
    public static void main (String[] args) {
        String namn = "Sigrun";

        SwingUtilities.invokeLater(()-> {
            try {
                new ChatSingleThreadUpdated(namn,"230.1.1.1", 12345 );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
