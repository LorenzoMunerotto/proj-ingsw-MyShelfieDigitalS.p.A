package it.polimi.ingsw.model.gameState.events;

import org.javatuples.Pair;

/**
 * Class defining the event of updating the current player.
 */
public class CurrentPlayerUpdateEvent implements ModelEvent {

    /**
     * Pair containing the username and the index of the current player.
     */
    private final Pair<String, Integer> currentPlayer;

    /**
     * Default constructor.
     *
     * @param username is the username of the current player
     * @param index    is the index of the current player
     */
    public CurrentPlayerUpdateEvent(String username, int index) {
        this.currentPlayer = new Pair<>(username, index);
    }

    /**
     * Get the current player.
     *
     * @return the current player
     */
    public Pair<String, Integer> getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * {@inheritDoc}
     */
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}