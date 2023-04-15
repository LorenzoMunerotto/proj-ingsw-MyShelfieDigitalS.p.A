package it.polimi.ingsw.model.gameState;

import it.polimi.ingsw.model.gameEntity.Bag;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.gameState.Exceptions.GameStartedException;
import it.polimi.ingsw.model.gameState.Exceptions.InvalidNumOfPlayers;
import it.polimi.ingsw.model.gameState.Exceptions.UsernameAlreadyExistsException;
import it.polimi.ingsw.model.gameEntity.Player;

import java.util.*;

/**
 * Class representing the objects of the game.
 */
public class GameData {

    /**
     * Bag of the game
     */
    private final Bag bag;
    /**
     * List of players
     */
    private final List<Player> players;
    /**
     * List of common goal cards
     */
    private final List<CommonGoalCard> commonGoalCardsList;
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
     * Number of players
     */
    private int currentNumOfPlayers;
    /**
     * Boolean that indicates if the game has started
     */
    private boolean started;
    /**
     * Username of the first player that has completed the library
     */
    private Optional<String> firstFullLibraryUsername;

    /**
     * Constructor of the class.
     */
    public GameData() {
        this.numOfPlayers = 0;
        this.bag = new Bag();
        this.currentPlayerIndex = null;
        this.started = false;
        this.currentNumOfPlayers = 0;
        this.players = new ArrayList<>();
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
     * @throws InvalidNumOfPlayers if the number of players is not between 2 and 4
     */
    public void setNumOfPlayers(int numOfPlayers) throws InvalidNumOfPlayers {
        if (numOfPlayers < 2 || numOfPlayers > 4) throw new InvalidNumOfPlayers();
        this.numOfPlayers = numOfPlayers;
    }

    /**
     * This method adds a player to the game.
     *
     * @param newPlayer the player to add
     * @throws UsernameAlreadyExistsException if the username is already taken
     * @throws GameStartedException           if the game has already started
     */
    public void addPlayer(Player newPlayer) throws UsernameAlreadyExistsException, GameStartedException {

        if (started) {
            throw new GameStartedException();
        }

        for (Player player : players) {
            if (newPlayer.getUsername().equalsIgnoreCase(player.getUsername())) {
                throw new UsernameAlreadyExistsException();
            }
        }
        players.add(newPlayer);
        currentNumOfPlayers++;
        if (currentNumOfPlayers == numOfPlayers) {
            this.started = true;
            this.board = new Board(numOfPlayers);
            Collections.shuffle(this.players, new Random());
            players.get(0).setChair(true);
            this.currentPlayerIndex = 0;
        }
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
     * Get the current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
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
     * This method increase currentPlayerIndex at the end of the respective previous player's play.
     */
    public void nextPlayer() {
        if (currentPlayerIndex == numOfPlayers - 1) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex++;
        }
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
        this.firstFullLibraryUsername = Optional.of(firstFullLibraryUsername);
    }

    /**
     * Get the list of players.
     *
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Return true if the game has started.
     *
     * @return true if the game has started, false otherwise
     */
    public boolean isStarted() {
        return this.started;
    }
}
