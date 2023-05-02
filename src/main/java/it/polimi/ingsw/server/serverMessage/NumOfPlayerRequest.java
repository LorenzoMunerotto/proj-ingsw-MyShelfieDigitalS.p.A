package it.polimi.ingsw.server.serverMessage;

public class NumOfPlayerRequest implements ServerMessage {

    private final String message;

    public NumOfPlayerRequest() {
        message = "Please select the number of players for this match (2-3-4)";
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
