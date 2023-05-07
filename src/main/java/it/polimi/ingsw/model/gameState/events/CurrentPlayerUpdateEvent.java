package it.polimi.ingsw.model.gameState.events;

public class CurrentPlayerUpdateEvent implements ModelEvent {

    private final String username;

    public CurrentPlayerUpdateEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
