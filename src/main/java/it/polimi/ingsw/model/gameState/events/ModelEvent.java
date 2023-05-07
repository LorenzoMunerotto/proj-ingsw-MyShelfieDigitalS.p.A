package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.listener.Event;
import it.polimi.ingsw.listener.Listener;

public interface ModelEvent extends Event {

    @Override
    default void accept(Listener listener) {
        if (listener instanceof ModelChangeEventHandler){
            accept((ModelChangeEventHandler) listener);
        }else{
            System.out.println("Error in event handling");
        }
    }

    void accept(ModelChangeEventHandler modelChangeEventHandler);
}
