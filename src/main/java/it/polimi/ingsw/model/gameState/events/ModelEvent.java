package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.Event;
import it.polimi.ingsw.EventHandler;
import it.polimi.ingsw.Listener;
import it.polimi.ingsw.server.ModelChangeEventHandler;

public abstract class ModelEvent implements Event {



    @Override
    public void accept(Listener listener) {

    }
}
