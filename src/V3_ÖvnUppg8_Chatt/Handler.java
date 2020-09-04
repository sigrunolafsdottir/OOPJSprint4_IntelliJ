package V3_ÖvnUppg8_Chatt;

//Kod modifierad utifrån exemplen på http://cs.lmu.edu/~ray/notes/javanetexamples/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Handler extends Thread{
    
    private Socket socket;
    private MultiWriter multiWriter;
    private PrintWriter out;
    private BufferedReader in;
    int id;
    
    public Handler (Socket socket, MultiWriter multiWriter){
        this.socket = socket;
        this.multiWriter = multiWriter;
    }
    
    public void run(){
        
         try{
            out = new PrintWriter(socket.getOutputStream(), true);  //true for autoflush
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Vi lägger in vår printWriter i multiWriters lista 
            multiWriter.addWriter(out);
            
            while(true){
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                for (PrintWriter writer : multiWriter.getWriters()) {
                    writer.println("MESSAGE " + input);
                }
            }
            
        }
         catch (Exception e){
             e.printStackTrace();
         }
         finally {
            // Klienten har stängts ner. Vi städar ur multiWritern
            if (out != null) {
                multiWriter.removeWriter(out);
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    


}
