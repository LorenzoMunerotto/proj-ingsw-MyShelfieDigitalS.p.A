package it.polimi.ingsw.server.serverMessage;

import it.polimi.ingsw.client.clientMessage.ClientMessage;
import it.polimi.ingsw.client.clientMessage.ClientMessageHandler;

public class ChatServerMessage implements ServerMessage {

    private String sender;
    private String receiver;
    private String messageText;

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

    public String getReceiver() {
        return receiver;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
    }
}
