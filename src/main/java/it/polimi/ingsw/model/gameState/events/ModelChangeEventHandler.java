package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.Event;
import it.polimi.ingsw.EventHandler;
import it.polimi.ingsw.Listener;
import it.polimi.ingsw.model.gameState.events.*;
import it.polimi.ingsw.server.serverMessage.CommonCardsSetMessage;


public interface ModelChangeEventHandler extends EventHandler, Listener
{

   @Override
   default void handle(Event event) {

   }

   @Override
   default void update(Event event) {
      event.accept(this);
   }


   void handle(BoardUpdateEvent boardUpdateEvent);
   void handle(CommonCardReachEvent commonCardReachEvent);
   void handle(CommonCardsSetEvent commonCardsSetMessage);
   void handle(CurrentPlayerUpdateEvent currentPlayerUpdateEvent);
   void handle(FirstFullLibraryEvent firstFullLibraryEvent);
   void handle(LibraryUpdateEvent libraryUpdateEvent);
   void handle(PersonalCardSetEvent personalCardSetEvent);
   void handle(PointsUpdateEvent pointsUpdateEvent);






}
