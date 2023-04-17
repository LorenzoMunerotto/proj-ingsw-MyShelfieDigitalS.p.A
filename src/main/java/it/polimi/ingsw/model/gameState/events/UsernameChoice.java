package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.EventHandler;
import it.polimi.ingsw.Event;

public class UsernameChoice implements Event {

    private String username;

    public UsernameChoice(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void accept(EventHandler eventHandler) {
        eventHandler.handle(this);
    }


}
