package it.polimi.ingsw;


/**
 * interface of an object observed by another object
 * used to implement the Observable-Observer pattern
 */

public interface Listenable {


    void addListener(Listener listener);

    void notifyAllListeners(Event event);


}
