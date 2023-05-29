package it.polimi.ingsw.view.gui;

/**
 * Enumeration of the different types messenger.
 */
public enum ChatMessageType {
    /**
     * when the message is received
     */
    RECEIVER,
    /**
     * when the message is written form the client
     */
    SENDER;

    @Override
    public String toString() {
        return super.toString();
    }
}