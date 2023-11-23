package TicTacToeSimplerThreadHandeling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


   /**
* The class for the helper threads in this multithreaded server
* application. A Player is identified by a character mark
* which is either 'X' or 'O'. For communication with the
* client the player has a socket with its input and output
* streams. Since only text is being communicated we use a
* reader and a writer.
*/
    class ServerSidePlayer  {
        char mark;
        ServerSidePlayer opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;

    /**
* Constructs a handler thread for a given socket and mark
* initializes the stream fields, displays the first two
* welcoming messages.
*/
        public ServerSidePlayer(Socket socket, char mark) {
            this.socket = socket;
            this.mark = mark;
            try {
                input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }

        /*
        Sends data to client
         */

        public void send(String mess){
            output.println(mess);
        }

/*
Receives data from client
 */

       public String receive()  {
           try {
               return input.readLine();
           } catch (IOException e) {
               System.out.println("Player "+mark+" could not receive data " + e);
               throw new RuntimeException(e);
           }
       }


        /**
* Accepts notification of who the opponent is.
*/
        public void setOpponent(ServerSidePlayer opponent) {
            this.opponent = opponent;
        }

       /**
        * Returns the opponent.
        */
       public ServerSidePlayer getOpponent() {
           return opponent;
       }

    }