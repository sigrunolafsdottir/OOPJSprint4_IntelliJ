package URLConnectionDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class GetterDemo {
    
    public static void main(String[] args) throws MalformedURLException, IOException{
        
        URL url = new URL(
                "https://github.com/dwyl/english-words/blob/master/words.txt?raw=true");
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();  
        BufferedReader buffy = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        while ((inputLine = buffy.readLine()) != null){
            System.out.println(inputLine);
        }
        buffy.close();

    }
}
