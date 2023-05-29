package it.polimi.ingsw.listener;

/**
 * Interface of a class that observes other classes.
 */
public interface Listener {

    /**
     * This method is called when the observed object is changed.
     *
     * @param event the event that has been observed
     */
    void update(Event event);
}