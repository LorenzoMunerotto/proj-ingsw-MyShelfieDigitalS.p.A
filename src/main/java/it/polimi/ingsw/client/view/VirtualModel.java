package it.polimi.ingsw.client.view;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;

import java.util.List;

/**
 * This class represents the virtual model, for the proxy pattern.
 * Don't know how to update it.
 * A new model every time? Or with the update method below?
 * It defines new data structures for the game objects, with the essential information for the view.
 */
public class VirtualModel {

    /**
     * The current player.
     */
    private Player currentPlayer;
    /**
     * A new data structure that represents the board.
     */
    private BoardCell[][] board;
    /**
     * A new data structure that represents the library.
     */
    private ItemTile[][] userLibrary;
    /**
     * The username.
     */
    private String username;
    /**
     * The common goal cards.
     * Maybe a triplet that contains the current point, the index and the description.
     */
    private List<CommonGoalCard> commonGoalCards;
    /**
     * A new data structure that represents the personal goal card.
     */
    private ItemTile[][] personalGoalCard;
    /**
     * A list of the players of the game.
     */
    private List<Player> gamePlayers;
    /**
     * The number of tiles in the bag.
     */
    private int gameBag;
    /**
     * The winner of the game.
     */
    private String winner = null;

    /**
     * Default constructor.
     * Maybe it should initialize at least the username and the list of players?
     */
    public VirtualModel() {
    }

    /**
     * Get the board.
     *
     * @return the board.
     */
    public BoardCell[][] getBoard() {
        return this.board;
    }

    /**
     * Get the library.
     *
     * @return the library.
     */
    public ItemTile[][] getLibrary() {
        return this.userLibrary;
    }

    /**
     * Get the personal goal card.
     *
     * @return the personal goal card.
     */
    public ItemTile[][] getPersonalGoalCard() {
        return this.personalGoalCard;
    }

    /**
     * Get the common goal cards.
     *
     * @return the common goal cards.
     */

    public List<CommonGoalCard> getCommonGoalCards() {
        return this.commonGoalCards;
    }

    /**
     * Get the players.
     *
     * @return the players.
     */

    public List<Player> getPlayers() {
        return this.gamePlayers;
    }

    /**
     * Get the bag.
     *
     * @return the bag.
     */

    public int getBag() {
        return this.gameBag;
    }

    /**
     * Get the username.
     *
     * @return the username.
     */

    public String getUsername() {
        return this.username;
    }

    /**
     * Get the winner.
     *
     * @return the winner.
     */

    public String getWinner() {
        return this.winner;
    }

    /**
     * Get the current player.
     *
     * @return the current player.
     */

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Update the board.
     *
     * @param updatedBoard the updated board.
     */
    public void updateBoard (BoardCell[][] updatedBoard){
       this.board = updatedBoard;
    }

    /**
     * Update the library.
     *
     * @param updatedLibrary the updated library.
     */
    public void updateLibrary (ItemTile[][] updatedLibrary){
        this.userLibrary = updatedLibrary;
    }

    /**
     * Update the personal goal card.
     * It doesn't really need to be updated, it should be only set at the beginning of the game.
     *
     * @param updatedPersonalGoalCard the updated personal goal card.
     */
    public void updatePersonalGoalCard (ItemTile[][] updatedPersonalGoalCard){
        this.personalGoalCard = updatedPersonalGoalCard;
    }

    /**
     * Update the common goal cards.
     *
     * @param updatedCommonGoalCards the updated common goal cards.
     */
    public void updateCommonGoalCards (List<CommonGoalCard> updatedCommonGoalCards){
        this.commonGoalCards = updatedCommonGoalCards;
    }

    /**
     * Update the winner.
     *
     * @param winner the winner.
     */
    public void updateWinner(String winner){
        this.winner = winner;
    }

    /**
     * Update the current player.
     *
     * @param currentPlayer the current player.
     */
    public void updateCurrentPlayer(Player currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    /**
     * Update the bag.
     *
     * @param gameBag the bag.
     */
    public void updateBag(int gameBag){
        this.gameBag = gameBag;
    }
}
