package CarDemo.Server;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class CarServer {
    
    Database d = new Database();
    
    public CarServer(){
        int portNumber = 12345;

        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            String outputLine;
            
            out.println("Vilken bil vill du slå upp?");
            
            while ((inputLine = in.readLine()) != null) {
                outputLine = d.getCarData(inputLine.trim());
                if (outputLine == null){
                    out.println("Denna bil finns inte i databasen: "+inputLine);
                }
                else{
                    out.println(outputLine);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        CarServer s = new CarServer();
    }
}
