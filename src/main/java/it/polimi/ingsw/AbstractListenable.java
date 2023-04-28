package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;

/**
 * abstract class used to implement the Observable-Observer pattern
 * every object observed by another object must extend it
 */

public abstract class AbstractListenable implements Listenable{

    private final List<Listener> listeners;

    public AbstractListenable() {
        this.listeners = new ArrayList<>();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void notifyAllListeners(Event event) {
        for (Listener listener: listeners){
            listener.update(event);
        }
    }


}
