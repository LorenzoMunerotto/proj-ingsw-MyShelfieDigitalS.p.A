package it.polimi.ingsw.model.gameState.events;

public class CurrentPlayerUpdateEvent extends ModelEvent {

    private final String username;

    public CurrentPlayerUpdateEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
