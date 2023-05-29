package it.polimi.ingsw.view.events;

import it.polimi.ingsw.client.clientMessage.ChatClientMessage;
import it.polimi.ingsw.listener.Event;
import it.polimi.ingsw.listener.EventHandler;
import it.polimi.ingsw.listener.Listener;

public interface ViewChangeEventHandler extends EventHandler , Listener {

    @Override
    default void handle(Event event) {
        System.out.println("Error in event handling");
    }

    @Override
    default void update(Event event) {
        if (event instanceof ViewEvent) {
            update((ViewEvent) event);
        } else {
            System.out.println("Error in event handling");
        }
    }

    void update (ViewEvent viewEvent);

    void handle(Move move);

    void handle(UsernameChoice usernameChoice);

    void handle(NumOfPlayerChoice numOfPlayerChoice);

    void sendChatMessage(ChatClientMessage chatClientMessage);


}
