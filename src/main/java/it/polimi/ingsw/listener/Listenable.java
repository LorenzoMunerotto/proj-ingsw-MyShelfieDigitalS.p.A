package it.polimi.ingsw.listener;

/**
 * Interface of an object observed by another object.
 * Used to implement the Observable-Observer pattern.
 */
public interface Listenable {

    /**
     * Adds a listener to the list of listeners.
     *
     * @param listener the listener to add
     */
    void addListener(Listener listener);

    /**
     * This method notifies all the listeners of the event.
     *
     * @param event the event to notify
     */
    void notifyAllListeners(Event event);
}