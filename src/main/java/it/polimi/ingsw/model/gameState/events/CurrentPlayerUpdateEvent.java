package it.polimi.ingsw.model.gameState.events;

import org.javatuples.Pair;

public class CurrentPlayerUpdateEvent implements ModelEvent {

    private final Pair<String, Integer> currentPlayer;

    public CurrentPlayerUpdateEvent(String username, int index) {
        this.currentPlayer = new Pair<>(username, index);
    }

    public Pair<String, Integer> getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
