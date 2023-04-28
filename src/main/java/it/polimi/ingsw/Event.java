package it.polimi.ingsw;


/**
 * This is the interface used for implementing visitor pattern,
 * every object that represent an event in mvc pattern must implement this interface
 */

public interface Event {

    /**
     * This method is a part of the implementation of Visitor pattern applied to Observe-Observable pattern
     * @param
     */
    void accept(Listener listener);




}
