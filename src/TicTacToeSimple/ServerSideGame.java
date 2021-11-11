package TicTacToeSimple;


/**
* A two-player game.
*/
class ServerSideGame {

    /**
* A board has nine squares. Each square is either unowned or
* it is owned by a player. So we use a simple array of player
* references. If null, the corresponding square is unowned,
* otherwise the array cell stores a reference to the player that
* owns it.
*/
    private ServerSidePlayer[] board = {
        null, null, null,
        null, null, null,
        null, null, null};

    /**
* The current player.
*/
    ServerSidePlayer currentPlayer;

    /**
* Returns whether the current state of the board is such that one
* of the players is a winner.
*/
    public boolean hasWinner() {
        return
            (board[0] != null && board[0] == board[1] && board[0] == board[2])
            ||(board[3] != null && board[3] == board[4] && board[3] == board[5])
            ||(board[6] != null && board[6] == board[7] && board[6] == board[8])
            ||(board[0] != null && board[0] == board[3] && board[0] == board[6])
            ||(board[1] != null && board[1] == board[4] && board[1] == board[7])
            ||(board[2] != null && board[2] == board[5] && board[2] == board[8])
            ||(board[0] != null && board[0] == board[4] && board[0] == board[8])
            ||(board[2] != null && board[2] == board[4] && board[2] == board[6]);
    }

    /**
* Returns whether there are no more empty squares.
*/
    public boolean boardFilledUp() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
* Called by the player threads when a player tries to make a
* move. This method checks to see if the move is legal: that
* is, the player requesting the move must be the current player
* and the square in which she is trying to move must not already
* be occupied. If the move is legal the game state is updated
* (the square is set and the next player becomes current) and
* the other player is notified of the move so it can update its
* client.
*/
    public synchronized boolean legalMove(int location, ServerSidePlayer player) {
        if (player == currentPlayer && board[location] == null) {
            board[location] = currentPlayer;
            currentPlayer = currentPlayer.getOpponent();
            currentPlayer.otherPlayerMoved(location);
            return true;
        }
        return false;
    }

}

