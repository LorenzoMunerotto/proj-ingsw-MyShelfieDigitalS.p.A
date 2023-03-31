package it.polimi.ingsw.model.logic;

import it.polimi.ingsw.model.data.GameData;
import it.polimi.ingsw.model.logic.common_cards.CommonCardFactory;
import it.polimi.ingsw.model.logic.common_cards.CommonGoalCard;
import it.polimi.ingsw.model.logic.personal_cards.PersonalGoalCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameLogic {

    /**
     * The number of players in the game.
     */
    private final int numberOfPlayers;
    /**
     * The usernames of the players.
     */
    private final List<String> playersUsernames;
    /**
     * The game data of the game.
     */
    private final GameData gameData;
    /**
     * The board manager of the game.
     */
    private final BoardManager boardManager;
    /**
     * The library manager of the game.
     */

    /**
     * The personal goal cards of the players.
     */
    private final List<PersonalGoalCard> personalGoalCards = new ArrayList<>();
    /**
     * The common goal cards of the game.
     */
    private List<CommonGoalCard> commonGoalCards;

    /**
     * Constructor for game logic, initializes the game data, the board manager and the library manager.
     *
     * @param players         the list of the players.
     * @param numberOfPlayers the number of players in the game.
     */
    public GameLogic(List<Player> players, int numberOfPlayers) {
        this.playersUsernames = players.stream().map(Player::getUsername).collect(Collectors.toList());
        this.numberOfPlayers = numberOfPlayers;
        this.gameData = new GameData(this.playersUsernames, numberOfPlayers);
        this.boardManager = new BoardManager(this.gameData);

    }

    /**
     * Get a list of the usernames of the players.
     *
     * @return a list of the usernames of the players.
     */
    public List<String> getPlayersUsernames() {
        return playersUsernames;
    }

    /**
     * Get the game data of the game.
     *
     * @return the game data.
     */
    public GameData getGameData() {
        return this.gameData;
    }

    /**
     * Get the board manager of the game.
     *
     * @return the board manager.
     */
    public BoardManager getBoardManager() {
        return this.boardManager;
    }

    /**
     * Get the library manager of the game.
     *
     * @return the library manager.
     */


    /**
     * Get the common goal cards of the game.
     *
     * @return a list of common goal cards.
     */
    public List<CommonGoalCard> getCommonGoalCards() {
        return this.commonGoalCards;
    }

    /**
     * Get the personal goal cards of the players.
     *
     * @return a list of personal goal cards.
     */
    public List<PersonalGoalCard> getPersonalGoalCards() {
        return this.personalGoalCards;
    }

    /**
     * This method initializes the game.
     * It refills the board and updates it, it generates 2 random common goal cards,
     * and a personal goal cards for each player.
     */
    public void initGame() {
        this.boardManager.refillBoard();
        this.boardManager.updateBoard();
        this.commonGoalCards = CommonCardFactory.createCards(numberOfPlayers);
        for(String username : playersUsernames){
            //fix this when personal goal cards are implemented
            this.gameData.getPlayerDashboards().get(username).setPersonalGoalCard(new PersonalGoalCard());
        }
    }

    public void phase1(){

    }

    public void phase2(){

    }

    /**
     * Class that represents an exception thrown when a game logic error occurs.
     */
    public static class GameLogicException extends Exception {
        /**
         * Constructs a new exception with the specified detail message.
         *
         * @param message the detail message.
         */
        public GameLogicException(String message) {
            super(message);
        }
    }

}
