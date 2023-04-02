package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.Player;

public class PlayerManager {
    private Player player;

    public PlayerManager(Player currentPlayer) {
        this.player = currentPlayer;
    }

    public int commonPoints(){
        return 0;
    }

    public int personalPoints(){
        return 0;
    }

    public int adjcentPoints(){
        return 0;
    }
    public int finalPoint(){
        return 0;
    }

    public void calculateTotalPoints(){
        player.setTotPoints(commonPoints()+personalPoints()+adjcentPoints()+finalPoint());
    }
}
