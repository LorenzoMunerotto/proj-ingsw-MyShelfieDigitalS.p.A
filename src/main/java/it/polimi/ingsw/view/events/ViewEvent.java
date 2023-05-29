package it.polimi.ingsw.view.events;

import it.polimi.ingsw.listener.Event;
import it.polimi.ingsw.listener.Listener;

public interface ViewEvent extends Event {

    @Override
    default void accept(Listener listener) {
        if (listener instanceof ViewChangeEventHandler){
            accept((ViewChangeEventHandler) listener);
        }else{
            System.out.println("Error in event handling");
        }
    }

    void accept(ViewChangeEventHandler viewChangeEventHandler);
}