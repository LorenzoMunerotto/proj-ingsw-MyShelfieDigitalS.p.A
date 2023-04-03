package it.polimi.ingsw.model.gameMechanics;

import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.enums.ItemTileType;
import org.javatuples.Pair;



public class PlayerManager {
    private Player player;

    public PlayerManager(Player currentPlayer) {
        this.player = currentPlayer;
    }

    public int commonPoints() {
        return 0;
    }


    public int personalPoints(){
        return 0;
    }

    public int adjacentPoints(){

      return 0;

    }


    public int finalPoint(){
        return 0;
    }

    public void calculateTotalPoints(){
        player.setTotPoints(commonPoints()+personalPoints()+adjacentPoints()+finalPoint());
    }

 
}
