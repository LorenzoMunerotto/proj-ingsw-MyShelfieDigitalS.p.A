package it.polimi.ingsw.model.logic;

import it.polimi.ingsw.model.data.Bag;
import it.polimi.ingsw.model.data.Board;
import it.polimi.ingsw.model.data.PlayerDashboard;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;

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
    private final List<CommonGoalCard> commonGoalCards;

    /**
     * Constructor for GameData, initializes board, bag and playerDashboard.
     *
     * @param players list of the players in the game
     */
    public GameData(List<String> players, int numOfPlayers, List<CommonGoalCard> commonGoalCards){
        this.board = new Board(numOfPlayers);
        this.bag = new Bag();
        this.playerDashboards = new HashMap<>();
        for(int i = 0; i < numOfPlayers; i++){
            this.playerDashboards.put(players.get(i), new PlayerDashboard(/*personalCards.get(i)*/));
        }
        this.commonGoalCards = commonGoalCards;
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

    /**
     * Get the list of the common card of the match.
     *
     * @return list of common card
     */
    public List<CommonGoalCard> getCommonGoalCards(){
        return this.commonGoalCards;
    }
}
