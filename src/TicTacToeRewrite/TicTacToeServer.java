package TicTacToeRewrite;

import java.net.ServerSocket;

/**
 * A server for a network multi-player tic tac toe game.
 * Modified by Sigrun from a solution fround on the Internet.
 * Modification is using only one (implicit) thread to handle the game server side.
 * The strings that are sent in TTTP (tic tac toe protocol) are:
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
                ServerSidePlayer playerX = new ServerSidePlayer(listener.accept(), 'X');
                ServerSidePlayer playerO = new ServerSidePlayer(listener.accept(), 'O');
                ServerSideGameThreadLess game = new ServerSideGameThreadLess(playerX, playerO);
                game.doGame();
            }
        } finally {
            listener.close();
        }
    }
}

