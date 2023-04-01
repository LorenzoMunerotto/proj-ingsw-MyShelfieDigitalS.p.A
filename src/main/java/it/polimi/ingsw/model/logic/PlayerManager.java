package it.polimi.ingsw.model.logic;

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
