package it.polimi.ingsw.listener;

/**
 * This is the interface used for implementing visitor pattern.
 */
public interface Event {

    /**
     * This method is used for accepting a listener.
     *
     * @param listener is the listener to be accepted.
     */
    void accept(Listener listener);
}