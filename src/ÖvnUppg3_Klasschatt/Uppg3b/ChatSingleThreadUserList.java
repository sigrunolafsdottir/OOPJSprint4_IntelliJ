package ÖvnUppg3_Klasschatt.Uppg3b;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

//This solution is not really single thread, it only looks that way.
//Works because actionPerformed is executed in the Event Dispatch Thread
//the rest of the code is not.


public class ChatSingleThreadUserList extends JFrame implements ActionListener {

    String name;
    InetAddress iadr;
    int port;
    MulticastSocket so;
    JTextArea txt = new JTextArea(20, 30);
    JTextArea userList = new JTextArea( 20, 10);
    JScrollPane sp = new JScrollPane(txt);
    JTextField skriv = new JTextField();
    InetSocketAddress group;
    NetworkInterface netIf = NetworkInterface.getByName("wlan3");

    //A data structure that only allows one instance of each string
    //thus removing all duplicate names
    Set<String> userSet = new HashSet<>();

    WindowListener userQuitsListener = new WindowAdapter() {
        public void windowClosing(WindowEvent evt) {
            sendMess("BYEBYE");
        }
    };


    public ChatSingleThreadUserList(String gruppadr, int portNr) throws IOException{

        name = JOptionPane.showInputDialog("Ditt namn ");
        iadr = InetAddress.getByName(gruppadr);
        port = portNr;
        group = new InetSocketAddress(iadr, port);

        setTitle("Chat "+name);
        txt.setEditable(false);
        add(sp, BorderLayout.WEST);
        add(userList, BorderLayout.EAST);
        add(skriv, BorderLayout.SOUTH);
        skriv.addActionListener(this);
        addWindowListener(userQuitsListener);
        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        so = new MulticastSocket(port);
        so.joinGroup(group, netIf);
        sendMess("UPPKOPPLAD");
        sendMess("NEWUSER");

        byte[] data = new byte[1024];
        while(true){
            try{
                DatagramPacket packet = new DatagramPacket(data, data.length);
                so.receive(packet);
                String mess = new String(data, 0, packet.getLength());

                if (mess.trim().endsWith("NEWUSER")){
                    String newName = mess.substring(0, mess.indexOf(':'));
                    userSet.add(newName);
                    updateUserList();
                    sendMess("OLDUSER");
                }
                else if (mess.trim().endsWith("OLDUSER")){
                    String oldName = mess.substring(0, mess.indexOf(':'));
                    userSet.add(oldName);
                    updateUserList();
                }
                else if (mess.trim().endsWith("BYEBYE")){
                    String oldName = mess.substring(0, mess.indexOf(':'));
                    userSet.remove(oldName);
                    updateUserList();
                }
                else {
                    txt.append(mess + "\n");
                }
            }
            catch (IOException e){
                break;
            }
        }
    }

    private void updateUserList(){
        userList.setText("");
        for (String s : userSet){
            userList.append(s + "\n");
        }
    }
    
    private void sendMess(String s){

        byte[] data = (name + ": " +s).getBytes();
        DatagramPacket packet= new DatagramPacket(data, data.length, iadr, port);
        try{
            so.send(packet);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == skriv){
            sendMess(skriv.getText());
            skriv.setText("");
        }
        else {
            sendMess("Hej allesammans");
        }
    }
    
    public static void main (String[] args) throws IOException{
        ChatSingleThreadUserList c = new ChatSingleThreadUserList(
                        "230.1.1.1", 12345);
    }
}
