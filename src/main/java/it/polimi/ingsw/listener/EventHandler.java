package it.polimi.ingsw.listener;

/**
 * This is the interface used for implementing visitor pattern,
 * every object that represent an event in mvc pattern must implement this interface
 * every object that handles events must implement it
 */
public interface EventHandler {

    void handle(Event event);


}
