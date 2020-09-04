package URLConnectionDemo;

import java.net.*;
import java.io.*;


public class SocketDemo {

    public static void main(String[] args) throws Exception {
            
            Socket s = new Socket(InetAddress.getByName("skansholm.com"), 80);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            
            pw.println("GET /java_dirfdghdfhf/ HTTP/1.1");
            
            //bör ge 404
            //pw.println("GET /java_dirdfsfsfs/ HTTP/1.1");
            pw.println("Host: skansholm.com");
            
            pw.println("");
            pw.flush();
            
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            String t;
            while((t = br.readLine()) != null) System.out.println(t);
            br.close();
    }
}
