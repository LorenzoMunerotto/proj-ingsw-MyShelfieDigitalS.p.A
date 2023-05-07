package it.polimi.ingsw.model.gameState.events;

/**
 *
 */
public class CommonCardUpdateEvent implements ModelEvent{

    private final int points;

    public CommonCardUpdateEvent(int points){
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}