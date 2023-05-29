package it.polimi.ingsw.listener;

/**
 * This is the interface used for implementing visitor pattern.
 */
public interface EventHandler {

    /**
     * This method is used for handling the event.
     *
     * @param event is the event to be handled.
     */
    void handle(Event event);
}