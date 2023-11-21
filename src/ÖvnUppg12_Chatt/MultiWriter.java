package ÖvnUppg12_Chatt;

//Kod modifierad utifrån exemplen på http://cs.lmu.edu/~ray/notes/javanetexamples/

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//We need a centralized place in order to keep track of users
//A clientHandler only knows about her own user, this class is used whenever 
//the clienthandler does something that concernes the other users
//i.e. popagating their messages

public class MultiWriter {

    private static List<PrintWriter> writers = new ArrayList<PrintWriter>();
    
    public void addWriter(PrintWriter p) {
            writers.add(p);
    }

    public void removeWriter(PrintWriter p) {
            writers.remove(p);
    }

    public void print(String s){
        for (PrintWriter writer : writers) {
            writer.println(s);
        }
    }

}
