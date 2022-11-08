package ÖvnUppg4a_Väderrapportering.grafiskt;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TemperatureSensor extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JTextField text = new JTextField(10);
    JButton button = new JButton("rapportera");
    String city;
    String temperature = "";
    String dataToSend = "";
    InetAddress toAdr = InetAddress.getLocalHost();
    int toPort = 55555;
    DatagramSocket socket = new DatagramSocket();

    public TemperatureSensor() throws UnknownHostException, SocketException, IOException, InterruptedException {

        city = JOptionPane.showInputDialog(null, "Ange stad");
        if (city == null || city.length() == 0) {  //tar hand om Cancel-tryck
            System.exit(0);
        }
        this.setTitle(city);
        this.add(panel);
        panel.add(text);
        panel.add(button);
        button.addActionListener(this);
        text.addActionListener(this);
        this.pack();
        this.setLocation(800, 300);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        temperature = text.getText();
        dataToSend = city + ", " + temperature;
        byte[] data = dataToSend.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, toAdr, toPort);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText("");
    }

    public static void main(String[] args) throws UnknownHostException, SocketException, IOException, InterruptedException {
        TemperatureSensor ts = new TemperatureSensor();
    }


}