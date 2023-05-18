package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.model.gameEntity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerOrderSetEvent implements ModelEvent {

    private final List<String> playerOrder;

    public PlayerOrderSetEvent(List<Player> players) {
        this.playerOrder = new ArrayList<>();
        for (Player player : players) {
            this.playerOrder.add(player.getUsername());
        }
    }

    public List<String> getPlayerOrder() {
        return playerOrder;
    }

    @Override
    public void accept(ModelChangeEventHandler modelChangeEventHandler) {
        modelChangeEventHandler.handle(this);
    }
}
