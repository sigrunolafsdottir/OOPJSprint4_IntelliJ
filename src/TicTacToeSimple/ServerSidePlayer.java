package TicTacToeSimple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


   /**
     * The class for the helper threads in this multithreaded server
     * application.  A Player is identified by a character mark
     * which is either 'X' or 'O'.  For communication with the
     * client the player has a socket with its input and output
     * streams.  Since only text is being communicated we use a
     * reader and a writer.
     */
    class ServerSidePlayer extends Thread {
        char mark;
        ServerSidePlayer opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        ServerSideGame game;

    /**
         * Constructs a handler thread for a given socket and mark
         * initializes the stream fields, displays the first two
         * welcoming messages.
         */
        public ServerSidePlayer(Socket socket, char mark, ServerSideGame game) {
            this.socket = socket;
            this.mark = mark;
            this.game = game;
            try {
                input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME " + mark);
                output.println("MESSAGE Waiting for opponent to connect");
            } catch (IOException e) {
                System.out.println("Player died: " + e);
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

        /**
         * Handles the otherPlayerMoved message.
         */
        public void otherPlayerMoved(int location) {
            output.println("OPPONENT_MOVED " + location);
        
            if (game.hasWinner()){
                output.println("DEFEAT");
            }
            else{
                if (game.boardFilledUp()){
                    output.println("TIE");
                }
                else{
                    output.println("");
                }
            }
        }

        /**
         * The run method of this thread.
         */
        public void run() {
            try {
            // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");

            // Tell the first player that it is her turn.
                if (mark == 'X') {
                    output.println("MESSAGE Your move");
                }

                // Repeatedly get commands from the client and process them.
                while (true) {
                    String command = input.readLine();
                    if (command.startsWith("MOVE")) {
                        int location = Integer.parseInt(command.substring(5));
                        if (game.legalMove(location, this)) {
                            output.println("VALID_MOVE");
                            output.println(game.hasWinner() ? "VICTORY"
                                : game.boardFilledUp() ? "TIE"
                                : "");
                        } else {
                            output.println("MESSAGE ?");
                        }
                    } else if (command.startsWith("QUIT")) {
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
        }
    }