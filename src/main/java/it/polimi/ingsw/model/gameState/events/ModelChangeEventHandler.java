package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.listener.Event;
import it.polimi.ingsw.listener.EventHandler;
import it.polimi.ingsw.listener.Listener;

/**
 * Is the interface for the event handler of the model.
 */
public interface ModelChangeEventHandler extends EventHandler, Listener {

    /**
     * Handles the event.
     *
     * @param event is the event to be handled.
     */
    @Override
    default void handle(Event event) {
        System.out.println("Error in event handling");
    }

    /**
     * Updates the event.
     *
     * @param event the event that has been observed
     */
    @Override
    default void update(Event event) {
        if (event instanceof ModelEvent) {
            update((ModelEvent) event);
        } else {
            System.out.println("Error in event handling");
        }
    }

    /**
     * Updates the model event.
     *
     * @param modelEvent the model event that has been observed
     */
    void update(ModelEvent modelEvent);

    /**
     * Handles the board set event.
     *
     * @param boardSetEvent the board set event to be handled
     */
    void handle(BoardSetEvent boardSetEvent);

    /**
     * Handles the board update event.
     *
     * @param boardUpdateEvent the board update event to be handled
     */
    void handle(BoardUpdateEvent boardUpdateEvent);

    /**
     * Handles the common card reach event.
     *
     * @param commonCardReachEvent the common card reach event to be handled
     */
    void handle(CommonCardReachEvent commonCardReachEvent);

    /**
     * Handles the common cards set event.
     *
     * @param commonCardsSetMessage the common cards set event to be handled
     */
    void handle(CommonCardsSetEvent commonCardsSetMessage);

    /**
     * Handles the current player update event.
     *
     * @param currentPlayerUpdateEvent the current player update event to be handled
     */
    void handle(CurrentPlayerUpdateEvent currentPlayerUpdateEvent);

    /**
     * Handles the first full library event.
     *
     * @param firstFullLibraryEvent the first full library event to be handled
     */
    void handle(FirstFullLibraryEvent firstFullLibraryEvent);

    /**
     * Handles the board refill event.
     *
     * @param boardRefillEvent the board refill event to be handled
     */
    void handle(BoardRefillEvent boardRefillEvent);

    /**
     * Handles the library update event.
     *
     * @param libraryUpdateEvent the library update event to be handled
     */
    void handle(LibraryUpdateEvent libraryUpdateEvent);

    /**
     * Handles the personal card set event.
     *
     * @param personalCardSetEvent the personal card set event to be handled
     */
    void handle(PersonalCardSetEvent personalCardSetEvent);

    /**
     * Handles the library set event.
     *
     * @param librarySetEvent the library set event to be handled
     */
    void handle(LibrarySetEvent librarySetEvent);

    /**
     * Handles the points update event.
     *
     * @param pointsUpdateEvent the points update event to be handled
     */
    void handle(PointsUpdateEvent pointsUpdateEvent);
    void handle(PlayerOrderSetEvent playerOrderSetEvent);
}