package it.polimi.ingsw.client.clientMessage;

public class ChatClientMessage implements ClientMessage {

    private String sender;
    private String receiver;
    private String messageText;

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
    public void accept(ClientMessageHandler clientMessageHandler) {
        clientMessageHandler.handle(this);
    }

}
