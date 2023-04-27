package it.polimi.ingsw.model.gameState.events;

public class FirstFullLibraryEvent extends ModelEvent {

    private final String Username;

    public FirstFullLibraryEvent(String username) {
        Username = username;
    }
}
