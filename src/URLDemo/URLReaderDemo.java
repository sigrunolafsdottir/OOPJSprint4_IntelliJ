package URLDemo;

import java.net.*;
import java.io.*;

public class URLReaderDemo {
    public static void main(String[] args) throws Exception {

        URL boken = new URL("https://skansholm.com/java_dir/#Exempel");
        BufferedReader in = new BufferedReader(
            new InputStreamReader(boken.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
