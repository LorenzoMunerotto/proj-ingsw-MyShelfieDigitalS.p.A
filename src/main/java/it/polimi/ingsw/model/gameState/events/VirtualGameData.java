package it.polimi.ingsw.model.gameState.events;

import it.polimi.ingsw.AbstractListenable;
import it.polimi.ingsw.EventHandler;
import it.polimi.ingsw.Event;
import it.polimi.ingsw.Listener;
import it.polimi.ingsw.model.gameEntity.Bag;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameState.GameData;

import java.util.List;
import java.util.Optional;

/**
 * this is the virtual model, a gameData with only getter, every time the gameData changes the view is notified by a
 * VirtualGameData event
 */
public class VirtualGameData extends AbstractListenable implements Listener, Event {


    /** It's the number of players chosen by the first player who connected to the server. read-only. Immutable */
    private int numOfPlayers;
    /** It's the index of the current player. */
    private Integer currentPlayerIndex;
    /** Board of the game. */
    private Board board;
    /** Bag of the game */
    private Bag bag;
    /** List of players */
    private List<Player> players;
    /** List of common goal cards */
    private List<CommonGoalCard> commonGoalCardsList;
    /** Number of players */
    private int currentNumOfPlayers;
    /** Boolean that indicates if the game has started */
    private boolean started;
    /** Username of the first player that has completed the library */
    private Optional<String> firstFullLibraryUsername;


    public VirtualGameData(GameData gameData){
        this.board=gameData.getBoard();
        this.bag=gameData.getBag();
        this.players=gameData.getPlayers();
        this.currentPlayerIndex= gameData.getCurrentPlayerIndex();
        this.numOfPlayers= gameData.getNumOfPlayers();
        this.commonGoalCardsList= gameData.getCommonGoalCardsList();
        this.currentNumOfPlayers= gameData.getCurrentNumOfPlayers();
        this.started=gameData.isStarted();
        this.firstFullLibraryUsername=gameData.getFirstFullLibraryUsername();
    }

    @Override
    public void update(Event event) {
    }


    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Integer getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public Board getBoard() {
        return board;
    }

    public Bag getBag() {
        return bag;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<CommonGoalCard> getCommonGoalCardsList() {
        return commonGoalCardsList;
    }

    public int getCurrentNumOfPlayers() {
        return currentNumOfPlayers;
    }

    public boolean isStarted() {
        return started;
    }

    public Optional<String> getFirstFullLibraryUsername() {
        return firstFullLibraryUsername;
    }

    @Override
    public void accept(EventHandler eventHandler) {
        eventHandler.handle(this);
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }
}
