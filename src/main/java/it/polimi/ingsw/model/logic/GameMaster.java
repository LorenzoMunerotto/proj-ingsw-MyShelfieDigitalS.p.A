package it.polimi.ingsw.model.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameMaster {

    private GameLogic gameLogic;
    /**
     * listOfPersonalCard Array with PersonalCard's numbers already in use
     */
    private int[] listOfPersonalCard= {0,0,0,0,0};
    private final List<Player> players;
    private final int numberOfPlayers;
    private int currentPlayerIndex;
    public final boolean started;

    public GameMaster(String firstPlayerName, int numberOfPlayers) throws IOException {
        players = new ArrayList<>();
        this.numberOfPlayers = numberOfPlayers;
        this.players.add(new Player(firstPlayerName, generateRandomNumber()));
        this.started = false;
    }
    public int generateRandomNumber(){
        while (true){
            int random= (int) (Math.random()*(12)+1);
            for(int i=0; i<players.size(); i++){
                if (listOfPersonalCard[i]==0){
                    if(i==1 && listOfPersonalCard[0]!=random){
                        listOfPersonalCard[1]=random;

                        return random;
                    }
                    if(i==2 && listOfPersonalCard[0]!=random && listOfPersonalCard[1]!=random){
                        listOfPersonalCard[2]=random;
                        return random;
                    }
                    if(i==3 && listOfPersonalCard[0]!=random && listOfPersonalCard[1]!=random && listOfPersonalCard[2]!=random){
                        listOfPersonalCard[3]=random;
                        return random;
                    }
                    if(i==0 && listOfPersonalCard[0]==0){
                        listOfPersonalCard[0]=random;
                        return random;
                    };
                }
            }
        }

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

    public void addPlayer(String playerName) throws IOException {
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
        this.players.add(new Player(playerName, generateRandomNumber()));
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
