package experiment.TCP;

//Kod modifierad utifrån exemplen på http://cs.lmu.edu/~ray/notes/javanetexamples/

import java.io.*;
import java.net.Socket;


public class Handler extends Thread{
    
    private Socket socket;
    private MultiWriter multiWriter;
    
    public Handler (Socket socket, MultiWriter multiWriter){
        this.socket = socket;
        this.multiWriter = multiWriter;
    }
    
    public void run(){
        
         try ( ObjectOutputStream out = new ObjectOutputStream(
                 socket.getOutputStream());
               ObjectInputStream in = new ObjectInputStream(
                       socket.getInputStream());){


             System.out.println("first i handler");
            //Vi lägger in vår printWriter i multiWriters lista 
             Object input = in.readObject();
             ServerSideUser ssu = null;
             if (((String) input).startsWith("NAMN")){
                 System.out.println("handshake " + (String) input);
                 ssu = new ServerSideUser(out, ((String) input).split(":")[1].trim());
                 multiWriter.addWriter(ssu);
             }
             System.out.println("efter handshake " + (String) input);
             //multiWriter.addWriter(out);

            
            while((input = in.readObject()) != null){
                if (((String) input).endsWith("SHUTDOWN")){
                    System.out.println("stänger ner " + (String) input);
                    multiWriter.removeWriter(ssu);
                }
                else
                    multiWriter.print(input);
            }
        }
         catch (Exception e){
             e.printStackTrace();
         }

    }
    


}
