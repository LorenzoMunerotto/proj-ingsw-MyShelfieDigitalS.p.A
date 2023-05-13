package it.polimi.ingsw.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class used to implement the Observable-Observer pattern.
 */
public abstract class AbstractListenable implements Listenable {

    /**
     * List of listeners.
     */
    private final List<Listener> listeners;

    /**
     * Default constructor.
     */
    public AbstractListenable() {
        this.listeners = new ArrayList<>();
    }

    /**
     * Adds a listener to the list.
     *
     * @param listener Listener to add
     */
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    /**
     * This method notifies all the listeners in the list.
     *
     * @param event Event to notify
     */
    public void notifyAllListeners(Event event) {
        for (Listener listener : listeners) {
            listener.update(event);
        }
    }
}