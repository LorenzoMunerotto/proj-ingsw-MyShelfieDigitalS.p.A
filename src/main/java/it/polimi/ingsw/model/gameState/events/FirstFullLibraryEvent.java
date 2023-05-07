package it.polimi.ingsw.model.gameState.events;

public class FirstFullLibraryEvent implements ModelEvent {

    private final String username;

    public FirstFullLibraryEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
