package it.polimi.ingsw.server.serverMessage;

import org.javatuples.Pair;

/**
 * This class represents the message that server sends to
 * client for communicate the beginning of a turn
 */
public class StartTurnMessage implements ServerMessage {

    private final String message;
    private final Pair<String, Integer> currentPlayer;

    public StartTurnMessage(Pair<String, Integer> currentPlayer) {
        this.message = currentPlayer.getValue0()+ "'s turn started";
        this.currentPlayer = currentPlayer;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    public Pair<String, Integer> getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
