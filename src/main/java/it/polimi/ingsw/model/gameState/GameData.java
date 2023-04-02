package it.polimi.ingsw.model.gameState;

import it.polimi.ingsw.model.gameEntity.Bag;
import it.polimi.ingsw.model.gameEntity.Board;
import it.polimi.ingsw.model.gameEntity.PlayerDashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing the objects of the game.
 */
public class GameData {

    /**
     * Board of the game.
     */
    private int currentPlayerIndex;
    private final Board board;
    /**
     * Bag of the game.
     */
    private final Bag bag;
    /**
     * Map that as associates each player with his dashboard.
     */
    private final Map<String, PlayerDashboard> playerDashboards;
    /**
     * List of the random common cards of the match.
     */

    /**
     * Constructor for GameData, initializes board, bag and playerDashboard.
     *
     * @param players list of the players in the game
     */
    public GameData(List<String> players, int numOfPlayers){
        this.board = new Board(numOfPlayers);
        this.bag = new Bag();
        this.playerDashboards = new HashMap<>();
        for(int i = 0; i < numOfPlayers; i++){
            this.playerDashboards.put(players.get(i), new PlayerDashboard());
        }
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
     * Get the map of players and their dashboard.
     *
     * @return the map playerDashboards
     */
    public Map<String, PlayerDashboard> getPlayerDashboards() {
        return this.playerDashboards;
    }
}
