package ÖvnUppg12_Chatt;

//Kod modifierad utifrån exemplen på http://cs.lmu.edu/~ray/notes/javanetexamples/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Handler extends Thread{
    
    private Socket socket;
    private MultiWriter multiWriter;
    
    public Handler (Socket socket, MultiWriter multiWriter){
        this.socket = socket;
        this.multiWriter = multiWriter;
    }
    
    public void run(){
        
         try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);  //true for autoflush
              BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));){

            //Vi lägger in vår printWriter i multiWriters lista 
            multiWriter.addWriter(out);
            
            while(true){
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                for (PrintWriter writer : multiWriter.getWriters()) {
                    writer.println(input);
                }
            }
            
        }
         catch (Exception e){
             e.printStackTrace();
         }

    }
    


}
