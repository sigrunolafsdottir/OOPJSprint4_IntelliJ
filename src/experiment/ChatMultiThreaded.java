package experiment;

import javax.swing.*;
//import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class ChatMultiThreaded extends JFrame implements ActionListener {

    String namn;
    InetAddress iadr;
    int port;
    MulticastSocket so= new MulticastSocket(12540);;
    JTextArea txt = new JTextArea();
    JTextArea memberArea = new JTextArea("I chatten just nu:", 10, 12);

    List<String> memberList = new ArrayList<>();
    JScrollPane sp = new JScrollPane(txt);
    JTextField skriv = new JTextField();
    JButton sluta = new JButton("Koppla ner");
    InetSocketAddress group;
    NetworkInterface netIf = NetworkInterface.getByName("wlan3");
    
    public ChatMultiThreaded(String användarnamn, 
            String gruppadr, int portNr) throws IOException{
        namn = användarnamn;
        iadr = InetAddress.getByName(gruppadr);
        port = portNr;
        group = new InetSocketAddress(iadr, port);
        
        //so = new MulticastSocket(port);
        so.joinGroup(group, netIf);
        new MottagareMultiThreaded(so, txt, memberArea, memberList);
        
        sändMedd("UPPKOPPLAD");
        //memberList.add(namn);
        //updateMemberArea();

        setTitle("Chat "+namn);
        txt.setEditable(false);
        add(sluta, BorderLayout.NORTH);
        add(memberArea, BorderLayout.EAST);
        add(sp, BorderLayout.CENTER);
        add(skriv, BorderLayout.SOUTH);
        sluta.addActionListener(this);
        skriv.addActionListener(this);
        setSize(500, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void updateMemberArea(){
        String memberText = "I chatten just nu: \n";
        for (String n: memberList){
            memberText += n + "\n";
        }
        memberArea.setText(memberText);
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
                so.close();
                dispose();
                System.exit(0);
            }
            catch (IOException ie){
                so.close();
                dispose();
                System.exit(0);
            }
                
        }
    }
    
    public static void main (String[] args) throws IOException{
        String namn = JOptionPane.showInputDialog(null, "Ange ditt chatt-alias");
        //Chat c = new Chat(namn, "234.235.236.237", 12540);
        ChatMultiThreaded c = new ChatMultiThreaded(namn, 
                "234.235.236.237", 12540);
    }
}
