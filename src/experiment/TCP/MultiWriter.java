package experiment.TCP;

//Kod modifierad utifrån exemplen på http://cs.lmu.edu/~ray/notes/javanetexamples/

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//We need a centralized place in order to keep track of users
//A clientHandler only knows about her own user, this class is used whenever 
//the clienthandler does something that concernes the other users
//i.e. popagating their messages

public class MultiWriter {
/*
   private static List<ObjectOutputStream> writers = new ArrayList<ObjectOutputStream>();
 public void addWriter(ObjectOutputStream p) {
     writers.add(p);
 }

    public void removeWriter(ObjectOutputStream p) {
        writers.remove(p);
    }

     public void print(Object s) throws IOException {
        for (ObjectOutputStream writer : writers) {
            //writer.println(s);
            writer.writeObject(s);
        }
    }
 */

    private static List<ServerSideUser> writers = new ArrayList<>();

    public void addWriter(ServerSideUser p) throws IOException {
        writers.add(p);
        print(writers.stream().map(w -> w.getNick()).collect(Collectors.toSet()));
    }

    public void removeWriter(ServerSideUser p) throws IOException {
        writers.remove(p);
        print(writers.stream().map(w -> w.getNick()).collect(Collectors.toSet()));
    }

    public void print(Object s) throws IOException {
        for (ServerSideUser writer : writers) {
            writer.getOos().writeObject(s);
        }
    }

}
