package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.listener.Event;
import it.polimi.ingsw.listener.Listener;

/**
 * Interface which represents an event that can be notified to the view.
 */
public interface ModelEvent extends Event {

    /**
     * Method which accepts a listener.
     *
     * @param listener is the listener to be accepted.
     */
    @Override
    default void accept(Listener listener) {
        if (listener instanceof ModelChangeEventHandler) {
            accept((ModelChangeEventHandler) listener);
        } else {
            System.out.println("Error in event handling");
        }
    }

    /**
     * Method which accepts a model change event handler.
     *
     * @param modelChangeEventHandler is the model change event handler to be accepted.
     */
    void accept(ModelChangeEventHandler modelChangeEventHandler);
}