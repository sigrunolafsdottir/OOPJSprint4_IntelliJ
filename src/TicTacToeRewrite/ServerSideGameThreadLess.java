package TicTacToeRewrite;


/**
* A two-player game.
*/
class ServerSideGameThreadLess {

    ServerSidePlayer playerX;
    ServerSidePlayer playerO;
    ServerSidePlayer currentPlayer;



    public ServerSideGameThreadLess(ServerSidePlayer playerX, ServerSidePlayer playerO){
        this.playerX=playerX;
        this.playerO=playerO;
        this.currentPlayer = playerX;
        this.playerX.setOpponent(playerO);
        this.playerO.setOpponent(playerX);
    }

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

    public void doGame(){

        playerX.send("WELCOME " + playerX.mark);
        playerX.send("MESSAGE Waiting for opponent to connect");

        playerO.send("WELCOME " + playerO.mark);
        playerO.send("MESSAGE All players connected");

        playerX.send("MESSAGE Your move");

        String command;
        currentPlayer = playerX;

        while (true) {

            command = currentPlayer.receive();
            //System.out.println("server, received "+command +" "+currentPlayer.mark);

            if (command.startsWith("MOVE")) {
                int location = Integer.parseInt(command.substring(5));

                if (board[location] == null) {  //clicked in empty square = legal move
                    board[location] = currentPlayer;

                    currentPlayer.send("VALID_MOVE");
                    currentPlayer.send(hasWinner() ? "VICTORY"
                            : boardFilledUp() ? "TIE"
                            : "");

                    currentPlayer = currentPlayer.getOpponent();  //BYTER SPELARE
                    //otherPlayerMoved(location, currentPlayer);    //uppdaterar motståndarens plan

                    currentPlayer.send("OPPONENT_MOVED " + location);
                    currentPlayer.send(hasWinner() ? "DEFEAT"
                            : boardFilledUp() ? "TIE"
                            : "");

                    } else {
                        currentPlayer.send("MESSAGE ?");
                    }
                    //TODO, rensa om det ligger saker i currentPlayers socket som lagts dit för tidigt
            } else if (command.startsWith("QUIT")) {
                    return;
            }
        }
    }


    public void otherPlayerMoved(int location, ServerSidePlayer player) {
        //System.out.println("Other player moved "+player.mark);
        player.send("OPPONENT_MOVED " + location);

        if (hasWinner()){
            player.send("DEFEAT");
        }
        else{
            if (boardFilledUp()){
                player.send("TIE");
            }
            else{
                player.send("");
            }
        }
    }

}

