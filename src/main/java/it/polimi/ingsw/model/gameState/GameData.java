package it.polimi.ingsw.model.gameState;

import it.polimi.ingsw.model.gameEntity.Bag;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameMechanics.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.gameMechanics.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameState.Exceptions.GameStartedException;
import it.polimi.ingsw.model.gameState.Exceptions.UsernameAlreadyExistsException;
import it.polimi.ingsw.model.gameEntity.Player;

import java.util.*;

/**
 * Class representing the objects of the game.
 */
public class GameData {

    /** It's the number of players chosen by the first player who connected to the server. read-only. Immutable */
    private final int numOfPlayers;

    private Integer currentPlayerIndex;

    /** Board of the game. */
    private final Board board;

    /** Bag of the game */
    private final Bag bag;


    private List<Player> players;



    private List<CommonGoalCard> commonGoalCardsList;

    private int currentNumOfPlayers;
    private boolean started;
    private Optional<String> firstFullLibraryUsername;


    public GameData( int numOfPlayers){
        this.numOfPlayers = numOfPlayers;
        this.board = new Board(numOfPlayers);
        this.bag = new Bag();
        this.currentPlayerIndex = null;
        this.started= false;
        this.currentNumOfPlayers=0;
        this.players= new ArrayList<>();
        this.commonGoalCardsList = CommonCardFactory.createCards();
        this.firstFullLibraryUsername = Optional.empty();
    }

    /**
     * Get the board.
     *
     * @return the board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Get the bag.
     *
     * @return the bag
     */
    public Bag getBag() {
        return this.bag;
    }


    public int getNumOfPlayers(){
        return numOfPlayers;
    }

    public void addPlayer(Player newPlayer) throws UsernameAlreadyExistsException, GameStartedException {

        if (started){
            throw new GameStartedException();
        }

        for(Player player: players) {
            if (newPlayer.getUsername().equalsIgnoreCase(player.getUsername())) {
                throw new UsernameAlreadyExistsException();
            }
        }
        players.add(newPlayer);
        currentNumOfPlayers++;
        if (currentNumOfPlayers==numOfPlayers){
            this.started=true;
            Collections.shuffle(this.players, new Random());
            players.get(0).setChair(true);
            this.currentPlayerIndex = 0;
        }

    }

    public List<CommonGoalCard> getCommonGoalCardsList() {
        return commonGoalCardsList;
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }

    public int getCurrentNumOfPlayers(){
        return currentNumOfPlayers;
    }



}
