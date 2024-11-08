package experiment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;

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

    JTextArea memberArea = new JTextArea("I chatten just nu:", 10, 12);

    java.util.Set<String> memberList = new HashSet<>();
    JButton sluta = new JButton("Koppla ner");
   // Timer timer = new Timer(10000, this);
    InetSocketAddress group;
    NetworkInterface netIf = NetworkInterface.getByName("wlan3");

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
        add(memberArea, BorderLayout.EAST);
        add(sp, BorderLayout.CENTER);
        add(skriv, BorderLayout.SOUTH);
        sluta.addActionListener(this);
        skriv.addActionListener(this);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                shutDown();
            }
        });
        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
      //  timer.start();
        
        byte[] data = new byte[1024];
        while(true){
            try{
             //   System.out.println("receive-loop: is EventDispatchThread:"
             //           + javax.swing.SwingUtilities.isEventDispatchThread());
                DatagramPacket packet = new DatagramPacket(data, 
                        data.length);
                so.receive(packet);
                String medd = new String(data, 0, 
                        packet.getLength());

                System.out.println(medd);
                if (medd.endsWith("UPPKOPPLAD".trim())){
                    String newChatter = medd.split(":")[0].trim();
                    memberList.add(newChatter);
                    updateMemberArea();
                    txt.append(medd +"\n");
                    if (!newChatter.equals(namn)){ //en ny medlem har anslutit till chatten efter mig
                        sändMedd("PINGBACK");
                    }
                }
                else if (medd.endsWith("NEDKOPPLAD".trim())){
                    //System.out.println("nerkopplad");
                    memberList.remove(medd.split(":")[0]);
                    updateMemberArea();
                    txt.append(medd +"\n");
                }
                else if (medd.endsWith("PINGBACK".trim())){
                    String newChatter = medd.split(":")[0].trim();
                    memberList.add(newChatter);
                    updateMemberArea();
                }
                else txt.append(medd +"\n");
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

    private void updateMemberArea(){
        String memberText = "I chatten just nu: \n";
        for (String n: memberList){
            memberText += n + "\n";
        }
        memberArea.setText(memberText);
    }
    
    public void actionPerformed(ActionEvent e){
    //    System.out.println("ActionPerformed, is EventDispatchThread:"
     //           + javax.swing.SwingUtilities.isEventDispatchThread());
        if(e.getSource() == skriv){
            sändMedd(skriv.getText());
            skriv.setText("");
        }
        else if(e.getSource() == sluta){
           shutDown();
        }
        else {
            sändMedd("Hej allesammans");
        }
    }

    private void shutDown(){
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
    
    public static void main (String[] args) throws IOException{
        String namn = JOptionPane.showInputDialog(null, "Ange ditt chatt-alias");
        ChatSingleThread c = new ChatSingleThread(namn,
        //        "234.235.236.237", 12540);
                "230.1.1.2", 12346);
    }
}
