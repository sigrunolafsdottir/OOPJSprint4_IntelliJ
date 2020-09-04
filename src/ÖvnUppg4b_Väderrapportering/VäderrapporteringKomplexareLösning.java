package ÖvnUppg4b_Väderrapportering;

   import java.net.*;
   import java.io.*;
   import java.text.*;
   import java.util.*;
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;

   public class VäderrapporteringKomplexareLösning extends JFrame implements ActionListener {
      JTextField ort = new JTextField(10);
      JTextField tem = new JTextField();
      InetAddress     iadr;
      MulticastSocket socket;
      int port;
   
      public VäderrapporteringKomplexareLösning(String gruppAdress, int portNr) 
      throws UnknownHostException, IOException {
         iadr = InetAddress.getByName(gruppAdress);
         port = portNr;
      
         // ordna fönstrets layout
         setTitle("Rapportera temperatur");
         Container c = getContentPane();
         c.setLayout(new GridLayout(2, 2));
         c.add(new JLabel("Ort ", JLabel.RIGHT));        c.add(ort);
         c.add(new JLabel("Temperatur ", JLabel.RIGHT)); c.add(tem);
         ort.addActionListener(this);
         tem.addActionListener(this);
         pack();
         setVisible(true);
         setDefaultCloseOperation(EXIT_ON_CLOSE);

         socket = new MulticastSocket(port);
         socket.joinGroup(iadr);
         Mottagare m = new Mottagare(socket);
         Point p = getLocationOnScreen();
         p.translate(getSize().width, 0);  // placera mottagaren till höger om detta fönster
         m.setLocation(p);
      }
   
      private void rapportera(String plats, double temp) {
         // avläs plats och temperatur från fönstret
         String medd = String.format("%-10s%6.1f", plats, temp);
         byte[] data = medd.getBytes();  // gör om texten till bytes
         // skapa ett datagram med meddelandet     
         DatagramPacket packet = new DatagramPacket(data, data.length, iadr, port);
         try {socket.send(packet);} 
         catch(IOException ie) {}
      }
   
      public void actionPerformed(ActionEvent e) {
         // användaren har tryckt på Enter-tangenten
         if (e.getSource()==ort || e.getSource()==tem) {
            Scanner sc = new Scanner(tem.getText());
            rapportera(ort.getText(), sc.nextDouble());
         }
      }
   
      public static void main(String[] arg) 
      throws UnknownHostException, SocketException, IOException {
         // hämta internetadress och portnummer från arg
         new VäderrapporteringKomplexareLösning("234.235.236.237", 12540);
      }
   }

   class Mottagare extends JFrame implements Runnable {
      final int ortLängd=10;
      final int temLängd=6;
      Thread aktivitet = new Thread(this);
      MulticastSocket so;  
      JTextArea ta = new JTextArea("", 0, 0);
      JScrollPane sp = new JScrollPane(ta);
      java.util.List<String> rader = new ArrayList<String>();
      DateFormat f = DateFormat.getTimeInstance(DateFormat.SHORT);
   
      Mottagare(MulticastSocket sock) {
         so = sock;
         setTitle("Uppmätta temperaturer");
         ta.setEditable(false);
         ta.setFont(new Font("Monospaced", Font.PLAIN, 14));
         ta.setBackground(Color.white);
         getContentPane().add(sp);
         setSize(250,200); 
         setVisible(true);
         aktivitet.start();
      }
   
      public void run() {
         byte[] data = new byte[1024];
         while (true) 
            try {
               // skapa ett datagram för att ta emot meddelande
               DatagramPacket packet = new DatagramPacket(data, data.length);
               so.receive(packet);  // vänta på ett meddelande
               // kontrollera att meddelandet har rätt längd
               if (packet.getLength() == ortLängd+temLängd)
                  uppdatera(packet.getData()); 
            }
            catch (IOException e) {}
      }
   
      private void uppdatera(byte[] data) {
         String medd = new String(data, 0, ortLängd+temLängd);
         String ort  = medd.substring(0,ortLängd);
         boolean funnen=false;
      
         // leta efter den aktuella orten
         for (int i=0; i<rader.size() && !funnen; i++) {
            String rad = rader.get(i);
            if (rad.substring(0,ortLängd).equals(ort)) {
               // uppdatera raden för denna ort
               rader.set(i, medd + " " + f.format(new Date()));
               funnen = true;   
            }
         }
         if (!funnen)  // ny ort, lägg till sist
            rader.add(medd + " " + f.format(new Date()));
     
         // Uppdatera fönstret
         String nyText = "";
         for (int i=0; i<rader.size(); i++) { 
            nyText += (String)rader.get(i) + '\n';  // radslutstecken efter varje rad
         }
         ta.setText(nyText);
      }
   }