package TicTacToeSimple;

import java.net.ServerSocket;

/**
* A server for a network multi-player tic tac toe game. Modified and
* extended from the class presented in Deitel and Deitel "Java How to
* Program" book. I made a bunch of enhancements and rewrote large sections
* of the code. The main change is instead of passing *data* between the
* client and server, I made a TTTP (tic tac toe protocol) which is totally
* plain text, so you can test the game with Telnet (always a good idea.)
* The strings that are sent in TTTP are:
*
 * Client -> Server        Server -> Client
 * ----------------------  ----------
 * MOVE <n> (0 <= n <= 8)  WELCOME <char> (char in {X, O})
 * QUIT                    VALID_MOVE
 *                         OTHER_PLAYER_MOVED <n>
 *                         VICTORY
 *                         DEFEAT
 *                         TIE
 *                         MESSAGE <text>
*
* A second change is that it allows an unlimited number of pairs of
* players to play.
*/
public class TicTacToeServer {

    /**
* Runs the application. Pairs up clients that connect.
*/
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Tic Tac Toe Server is Running");
        try {
            while (true) {
                ServerSideGame game = new ServerSideGame();

                ServerSidePlayer playerX 
                        = new ServerSidePlayer(listener.accept(), 'X', game);
                ServerSidePlayer playerO 
                        = new ServerSidePlayer(listener.accept(), 'O', game);

                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);
                game.currentPlayer = playerX;
                playerX.start();
                playerO.start();

                //Game2 game2 = new Game2(socket1, socket2);

            }
        } finally {
            listener.close();
        }
    }
}

