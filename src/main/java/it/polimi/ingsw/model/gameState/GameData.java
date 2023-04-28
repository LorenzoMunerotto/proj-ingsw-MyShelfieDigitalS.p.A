package it.polimi.ingsw.model.gameState;

import it.polimi.ingsw.AbstractListenable;
import it.polimi.ingsw.model.gameEntity.Bag;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameState.exceptions.IllegalNumOfPlayersException;
import it.polimi.ingsw.model.gameEntity.Player;
import it.polimi.ingsw.model.gameState.events.*;

import java.util.*;

/**
 * Class representing the objects of the game.
 */
public class GameData extends AbstractListenable {

    /**
     * Bag of the game
     */
    private final Bag bag;
    /**
     * List of players
     */
    private final List<Player> players;
    /**
     * It's the number of players chosen by the first player who connected to the server. read-only. Immutable
     */
    private int numOfPlayers;
    /**
     * It's the index of the current player.
     */
    private Integer currentPlayerIndex;
    /**
     * Board of the game.
     */
    private Board board;
    /**
     * List of common goal cards
     */
    private List<CommonGoalCard> commonGoalCardsList;
    /**
     * Number of players
     */
    private int currentNumOfPlayers;
    /**
     * Username of the first player that has completed the library
     */
    private Optional<String> firstFullLibraryUsername;

    /**
     * Constructor of the class.
     */
    public GameData() {
        super();
        this.numOfPlayers = 0;
        this.bag = new Bag();
        this.board = null;
        this.currentPlayerIndex = null;
        this.currentNumOfPlayers = 0;
        this.players = new ArrayList<>();
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
     * Set the board.
     *
     * @param board the board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Get the bag.
     *
     * @return the bag
     */
    public Bag getBag() {
        return this.bag;
    }

    /**
     * Get the number of players.
     *
     * @return the number of players
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * Set the number of players.
     *
     * @param numOfPlayers number of player for the game
     * @throws IllegalNumOfPlayersException if the number of players is not between 2 and 4
     */
    public void setNumOfPlayers(int numOfPlayers) throws IllegalNumOfPlayersException {
        if (numOfPlayers < 2 || numOfPlayers > 4) throw new IllegalNumOfPlayersException();
        this.numOfPlayers = numOfPlayers;
    }

    /**
     * This method adds a player to the game.
     *
     * @param newPlayer the player to add
     */
    public void addPlayer(Player newPlayer) {
        players.add(newPlayer);
        currentNumOfPlayers++;
    }

    /**
     * Get the list of common goal cards.
     *
     * @return the list of common goal cards
     */
    public List<CommonGoalCard> getCommonGoalCardsList() {
        return commonGoalCardsList;
    }

    /**
     * Set the list of common goal cards.
     *
     * @param commonGoalCardsList the list of common goal cards
     */
    public void setCommonGoalCardsList(List<CommonGoalCard> commonGoalCardsList) {
        this.commonGoalCardsList = commonGoalCardsList;
        notifyAllListeners(new CommonCardEvent(commonGoalCardsList));
    }

    /**
     * Get the current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Get the current player's client ID.
     *
     * @return the current player's client ID
     */
    public Integer getCurrentPlayerClientID() {
        return players.get(currentPlayerIndex).getClintID();
    }

    /**
     * Get the number of players that are currently playing.
     *
     * @return the number of players that are currently playing
     */
    public int getCurrentNumOfPlayers() {
        return currentNumOfPlayers;
    }

    /**
     * Get the index of the player who is playing this turn.
     *
     * @return the number of the player who is playing this turn
     */
    public Integer getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Set the index of the player who is playing this turn.
     *
     * @param currentPlayerIndex the number of the player who is playing this turn
     */
    public void setCurrentPlayerIndex(Integer currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
        notifyAllListeners(new CurrentPlayerUpdateEvent(getCurrentPlayer().getUsername()));
    }

    /**
     * Get the first player that has completed the library.
     *
     * @return the first player that has completed the library
     */
    public Optional<String> getFirstFullLibraryUsername() {
        return firstFullLibraryUsername;
    }

    /**
     * Set the first player that has completed the library.
     *
     * @param firstFullLibraryUsername the first player that has completed the library
     */
    public void setFirstFullLibraryUsername(String firstFullLibraryUsername) {
        if (this.firstFullLibraryUsername.isEmpty()) {
            this.firstFullLibraryUsername = Optional.of(firstFullLibraryUsername);
        }
    }

    /**
     * Get the list of players.
     *
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Get the player with the given index.
     *
     * @param index the index of the player
     * @return the player with the given index
     */
    public Player getPlayer(int index) {
        return players.get(index);
    }

    /**
     * Get the player from his client ID.
     *
     * @param clientId the client ID of the player
     * @return the player with the given client ID
     */
    public Player getPlayerByClientId(Integer clientId) {
        for (Player player : players) {
            if (player.getClintID() == clientId) {
                return player;
            }

        }
        return null;
    }
}
