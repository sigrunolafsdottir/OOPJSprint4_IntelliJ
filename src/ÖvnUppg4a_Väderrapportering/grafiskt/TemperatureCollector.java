package ÖvnUppg4a_Väderrapportering.grafiskt;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TemperatureCollector extends JFrame {

    JPanel panel = new JPanel();
    JTextArea text = new JTextArea(30, 20);

    public TemperatureCollector() throws SocketException, IOException {
        this.add(panel);
        panel.add(text);
        this.pack();
        this.setLocation(500, 300);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int minPort = 55555;
        DatagramSocket socket = new DatagramSocket(minPort);
        byte[] data = new byte[256];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        while (true) {
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            text.setText(text.getText() + " " + message + "\n");

        }
    }

    public static void main(String[] args) throws SocketException, IOException {
        TemperatureCollector tc = new TemperatureCollector();
    }


}