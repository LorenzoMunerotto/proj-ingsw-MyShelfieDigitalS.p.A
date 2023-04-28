package it.polimi.ingsw.server;

import it.polimi.ingsw.Event;
import it.polimi.ingsw.EventHandler;
import it.polimi.ingsw.Listener;
import it.polimi.ingsw.model.gameState.events.*;


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
   void handle(LibraryUpdateEvent libraryUpdateEvent);
   void handle(PointsUpdateEvent pointsUpdateEvent);
   void handle(CurrentPlayerUpdateEvent currentPlayerUpdateEvent);
   void handle(FirstFullLibraryEvent firstFullLibraryEvent);




}
