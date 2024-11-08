package experiment.TCP;

//Kod modifierad utifrån exemplen på http://cs.lmu.edu/~ray/notes/javanetexamples/

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Client  extends JFrame implements ActionListener {
    
    JTextArea txt = new JTextArea();
    JScrollPane sp = new JScrollPane(txt);
    JTextField skriv = new JTextField();
    ObjectOutputStream oos;
    ObjectInputStream ois;
  //  private BufferedReader in;
  //  private PrintWriter out;
    String name = "";
    JTextArea memberArea = new JTextArea("I chatten just nu:", 10, 12);

    java.util.Set<String> memberList = new HashSet<>();
    
    public Client () throws IOException {
        name = JOptionPane.showInputDialog(null, "Ange ditt chatt-alias");
        setTitle("TCP-Chat "+name);
        txt.setEditable(false);
        add(sp, BorderLayout.CENTER);
        add(memberArea, BorderLayout.EAST);
        add(skriv, BorderLayout.SOUTH);
        skriv.addActionListener(this);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    shutDown();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        String hostName = "127.0.0.1";  //localhost
        int portNumber = 12345;
 
        try {
            Socket socket = new Socket(hostName, portNumber);

             oos = new ObjectOutputStream(
                     socket.getOutputStream());
             ois = new ObjectInputStream(
                     socket.getInputStream());

           // Socket socket = new Socket(hostName, portNumber);
           // out = new PrintWriter(socket.getOutputStream(), true);
           // in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            Object fromServer;

            oos.writeObject("NAMN : " + name );

            while ((fromServer = ois.readObject()) != null) {
                if (fromServer instanceof String s){
                    txt.append(s+ "\n");
                }
                else{   //listan med uppdaterade medlemmar
                    memberList = (Set<String>)fromServer;
                    updateMemberArea();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //här hamnar vi när vi skrivit i textfältet och tryckt på enter
    public void actionPerformed(ActionEvent e) {
        try{
            //out.println(name +" : " + skriv.getText());
            oos.writeObject(name +" : " + skriv.getText());
            skriv.setText("");
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    private void updateMemberArea(){
        String memberText = "I chatten just nu: \n";
        for (String n: memberList){
            memberText += n + "\n";
        }
        memberArea.setText(memberText);
    }

    private void shutDown() throws IOException {
        oos.writeObject(name +" : SHUTDOWN" );
    }
    
    public static  void main(String[] args) throws IOException {
        Client c = new Client();
    }

}
