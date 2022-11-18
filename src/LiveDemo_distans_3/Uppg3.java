package LiveDemo_distans_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class Uppg3 extends JFrame implements ActionListener {

    JPanel bottom = new JPanel();
    JTextArea text = new JTextArea( 20, 50);
    JTextField input = new JTextField();
    InetAddress inetaddress;
    int port = 33333;
    MulticastSocket socket;


    public Uppg3() throws IOException {

        inetaddress = InetAddress.getByName("234.234.234.234");
        InetSocketAddress group = new InetSocketAddress(inetaddress, port);
        NetworkInterface netIf = NetworkInterface.getByName("wlan3");
        socket = new MulticastSocket(port);
        socket.joinGroup(group, netIf);

        bottom.setLayout(new BorderLayout());
        bottom.add(text, BorderLayout.NORTH);
        bottom.add(input, BorderLayout.SOUTH);
        input.addActionListener(this);
        add(bottom);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Receiver rec = new Receiver(socket, text);
        //rec.start();

        while(true){
            byte[] data = new byte[256];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String mess = new String(packet.getData(),0, packet.getLength());
            String mess = new String(data,0, data.length);
            text.append(mess);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mess = input.getText().trim()+ "\n";
        byte[] data = mess.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, inetaddress, port);
        try {
            socket.send(packet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        input.setText("");

    }

    public static void main(String[] args) throws IOException {
        Uppg3 u = new Uppg3();
    }


}
