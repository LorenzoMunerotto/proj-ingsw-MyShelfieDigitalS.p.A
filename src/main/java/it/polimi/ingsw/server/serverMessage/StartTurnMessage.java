package it.polimi.ingsw.server.serverMessage;

import org.javatuples.Pair;

public class StartTurnMessage implements ServerMessage {

    private final String message;
    private final Pair<String, Integer> currentPlayer;

    public StartTurnMessage(Pair<String, Integer> currentPlayer) {
        this.message = currentPlayer.getValue0()+ "'s turn started";
        this.currentPlayer = currentPlayer;
    }

    public String getMessage() {
        return message;
    }

    public Pair<String, Integer> getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
