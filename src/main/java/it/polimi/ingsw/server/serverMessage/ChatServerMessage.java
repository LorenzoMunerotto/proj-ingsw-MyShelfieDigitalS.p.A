package it.polimi.ingsw.server.serverMessage;

/**
 * Message used to send a chat message to the client.
 */
public class ChatServerMessage implements ServerMessage {

    /**
     * The sender of the message.
     */
    private final String sender;
    /**
     * The content of the message.
     */
    private final String messageText;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return null;
    }

    public ChatServerMessage(String sender, String receiver, String messageText) {
        this.sender = sender;
        this.messageText = messageText;
    }

    public String getSender() {
        return sender;
    }

    public String getMessageText() {
        return messageText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(ServerMessageHandler serverMessageHandler) {
        serverMessageHandler.handle(this);
    }
}
