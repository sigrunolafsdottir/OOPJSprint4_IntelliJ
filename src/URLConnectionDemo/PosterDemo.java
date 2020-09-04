package URLConnectionDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PosterDemo {
    
    public static void main(String[] args) throws MalformedURLException, IOException{

        String xmlStr = "<book><author>Philip K Dick</author><title>Valis</title><id>15</id></book>";       
        URL url = new URL("http://localhost:8080/WebApplicationDemo/rest/BookService/book/add");
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/xml"); 
        
        PrintWriter pw = new PrintWriter(conn.getOutputStream());
        pw.println(xmlStr);
        pw.flush();  // nödvändig
        
        //För att fånga upp responset
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String t;
        while((t = br.readLine()) != null) System.out.println(t);
        br.close();


    }
}
