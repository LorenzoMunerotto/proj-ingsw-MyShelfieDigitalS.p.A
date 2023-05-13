package it.polimi.ingsw.chat;

import it.polimi.ingsw.server.serverMessage.ServerMessage;
import it.polimi.ingsw.server.serverMessage.ServerMessageHandler;

import java.io.Serializable;

public class ChatMessage implements Serializable, ServerMessage {

    private final String sender;
    private final String content;

    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
