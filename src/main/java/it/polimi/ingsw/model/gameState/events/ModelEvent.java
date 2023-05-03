package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.listener.Event;
import it.polimi.ingsw.listener.Listener;

public interface ModelEvent extends Event {

    @Override
    default void accept(Listener listener) {
        if (listener instanceof ModelChangeEventHandler){
            accept((ModelChangeEventHandler) listener);
        }else{
            System.out.println("Errore nella gestione degli eventi");
        }
    }

    void accept(ModelChangeEventHandler modelChangeEventHandler);
}
