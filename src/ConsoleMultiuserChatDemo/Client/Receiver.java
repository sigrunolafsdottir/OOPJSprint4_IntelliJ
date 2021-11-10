package ConsoleMultiuserChatDemo.Client;

import java.io.BufferedReader;


public class Receiver extends Thread {

    BufferedReader in;
    String prompt;
    
    public Receiver(BufferedReader in, String prompt ){
        this.in = in;
        this.prompt = prompt;
    }
    
    @Override
    public void run() {
        try{
            String inputLine;
            while ((inputLine = in.readLine()) != null) {          
                System.out.println(inputLine);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
}
