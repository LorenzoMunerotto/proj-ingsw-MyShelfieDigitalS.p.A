package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.Event;
import it.polimi.ingsw.Listener;

public interface ModelEvent extends Event {

    @Override
    default void accept(Listener listener) {

    }

    void accept(ModelChangeEventHandler modelChangeEventHandler);
}
