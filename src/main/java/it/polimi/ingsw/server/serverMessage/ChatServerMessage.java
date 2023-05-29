package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.client.clientMessage.ClientMessage;
import it.polimi.ingsw.client.clientMessage.ClientMessageHandler;

public class ChatServerMessage implements ServerMessage {

    private final String sender;
    private final String receiver;
    private final String messageText;

    @Override
    public String getMessage() {
        return null;
    }

    public ChatServerMessage(String sender, String receiver, String messageText) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageText = messageText;
    }
    public String getSender() {
        return sender;
    }

    public String getMessageText() {
        return messageText;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
