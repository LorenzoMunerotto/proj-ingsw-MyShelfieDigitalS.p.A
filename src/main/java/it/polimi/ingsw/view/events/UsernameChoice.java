package it.polimi.ingsw.view.events;

import it.polimi.ingsw.client.clientMessage.ClientMessage;

public class UsernameChoice implements ClientMessage, ViewEvent {

    private final String username;

    public UsernameChoice(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void accept(ViewChangeEventHandler viewChangeEventHandler) {
        viewChangeEventHandler.handle(this);
    }
}
