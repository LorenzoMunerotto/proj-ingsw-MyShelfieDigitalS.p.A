package it.polimi.ingsw.view.events;

import it.polimi.ingsw.client.clientMessage.ClientMessage;
import it.polimi.ingsw.client.clientMessage.ClientMessageHandler;

/**
 * This class represents the message that the client sends when he chooses his username.
 */

public class UsernameChoice implements ClientMessage, ViewEvent {


    /**
     * The username chosen by the client.
     */
    private final String username;

    /**
     * The constructor of the class.
     *
     * @param username the username chosen by the client
     */
    public UsernameChoice(String username) {
        this.username = username;
    }

    /**
     * Get the username chosen by the client.
     *
     * @return the username chosen by the client
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the message.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return null;
    }



    @Override
    public void accept(ViewChangeEventHandler viewChangeEventHandler) {
        viewChangeEventHandler.handle(this);
    }

    @Override
    public void accept(ClientMessageHandler clientMessageHandler) {
        clientMessageHandler.handle(this);
    }
}
