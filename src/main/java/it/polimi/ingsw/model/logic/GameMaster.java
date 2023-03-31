package it.polimi.ingsw.model.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameMaster {

    private GameLogic gameLogic;
    private final List<Player> players;
    private final int numberOfPlayers;
    private int currentPlayerIndex;
    public final boolean started;

    public GameMaster(String firstPlayerName, int numberOfPlayers){
        players = new ArrayList<>();
        this.numberOfPlayers = numberOfPlayers;
        this.players.add(new Player(firstPlayerName));
        this.started = false;
    }

    public GameLogic getGameLogic() {
        return this.gameLogic;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void addPlayer(String playerName){
        if(this.players.size() == this.numberOfPlayers){
            throw new IllegalArgumentException("The game is already full");
        }
        if(playerName == null || playerName.trim().isEmpty()){
            throw new IllegalArgumentException("The player name cannot be empty or null. Please choose another one");
        }
        for(Player player : this.players){
            if(player.getUsername().equalsIgnoreCase(playerName)){
                throw new IllegalArgumentException("The player name is already taken. Please choose another one");
            }
        }
        this.players.add(new Player(playerName));
        if(this.players.size() == this.numberOfPlayers){
            try {
                this.gameLogic = new GameLogic(this.players, this.numberOfPlayers);
            } catch (Exception e) {
                System.err.println("Error while creating an instance of the game data" + e.getMessage());
                e.printStackTrace();
            }
            Collections.shuffle(this.players, new Random());
            players.get(0).setChair(true);
            this.currentPlayerIndex = 0;
        }
    }
}
