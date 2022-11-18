package ÖvnUppg13_LetterCount;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class ÖvnUppg13_LetterCount_Map {

    public ÖvnUppg13_LetterCount_Map() {

        String temp;
        Map<Integer, Integer> mapper = new HashMap<>();
        int total = 0;

        try{
            URL url = new URL("https://github.com/dwyl/english-words/blob/master/words.txt?raw=true");
        
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {
                while ((temp = in.readLine()) != null ){

                    int size = temp.length();
                    //det finns en rad med denna storlek
                    //det finns inte någon rad med denna storlek
                    // första gången vi hittar ett ord av denna storlek
                    mapper.merge(size, 1, Integer::sum);
                    total++;
                }

                for (Map.Entry<Integer,Integer> entry : mapper.entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
                
            }
            System.out.println("Totala mängden ord: "+total);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
       }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        ÖvnUppg13_LetterCount_Map ö = new ÖvnUppg13_LetterCount_Map();
    }
}
