package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.listener.Event;
import it.polimi.ingsw.listener.EventHandler;
import it.polimi.ingsw.listener.Listener;

public interface ModelChangeEventHandler extends EventHandler, Listener {

    @Override
    default void handle(Event event) {
        System.out.println("Error in event handling");
    }

    @Override
    default void update(Event event) {
        if (event instanceof ModelEvent) {
            update((ModelEvent) event);
        } else {
            System.out.println("Error in event handling");
        }
    }

    void update(ModelEvent modelEvent);

    void handle(BoardSetEvent boardSetEvent);

    void handle(BoardUpdateEvent boardUpdateEvent);

    void handle(CommonCardReachEvent commonCardReachEvent);

    void handle(CommonCardsSetEvent commonCardsSetMessage);

    void handle(CurrentPlayerUpdateEvent currentPlayerUpdateEvent);

    void handle(FirstFullLibraryEvent firstFullLibraryEvent);

   void handle(BoardRefillEvent boardRefillEvent);

   void handle(LibraryUpdateEvent libraryUpdateEvent);

    void handle(PersonalCardSetEvent personalCardSetEvent);

   void handle(LibrarySetEvent librarySetEvent);

   void handle(PointsUpdateEvent pointsUpdateEvent);
}