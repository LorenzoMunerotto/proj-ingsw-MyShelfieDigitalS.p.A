package it.polimi.ingsw.client.clientMessage;

public class ChatClientMessage implements ClientMessage {

    /**
     * The sender of the message
     */
    private final String sender;
    /**
     * The receiver of the message
     */
    private final String receiver;
    /**
     * The text of the message
     */
    private final String messageText;

    @Override
    public String getMessage() {
        return null;
    }

    public ChatClientMessage(String sender, String receiver, String messageText) {
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

    @Override
    public void accept(ClientMessageHandler clientMessageHandler) {
        clientMessageHandler.handle(this);
    }

}
