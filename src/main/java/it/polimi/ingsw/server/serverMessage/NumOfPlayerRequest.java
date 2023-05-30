package it.polimi.ingsw.server.serverMessage;

/**
 * This class represents the message that server sends to the first
 * client connected for set the num of players
 */
public class NumOfPlayerRequest implements ServerMessage {

    private final String message;

    public NumOfPlayerRequest() {
        message = "Please select the number of players for this match (2-3-4)";
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
