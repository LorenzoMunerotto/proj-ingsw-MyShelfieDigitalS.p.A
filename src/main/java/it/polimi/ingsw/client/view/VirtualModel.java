package it.polimi.ingsw.client.view;

import it.polimi.ingsw.model.gameEntity.*;
import it.polimi.ingsw.model.gameEntity.common_cards.CommonGoalCard;

import java.util.List;

/**
 * This interface represents the virtual model, for the proxy pattern.
 * Only getters are needed.
 */
public interface VirtualModel {

    /**
     * Get the board.
     *
     * @return the board.
     */
    BoardCell[][] getBoard();

    /**
     * Get the library.
     *
     * @return the library.
     */
    ItemTile[][] getLibrary();

    /**
     * Get the personal goal card.
     *
     * @return the personal goal card.
     */
    ItemTile[][] getPersonalGoalCard();

    /**
     * Get the common goal cards.
     *
     * @return the common goal cards.
     */
    List<CommonGoalCard> getCommonGoalCards();

    /**
     * Get the players.
     *
     * @return the players.
     */
    List<Player> getPlayers();

    /**
     * Get the bag.
     *
     * @return the bag.
     */
    int getBag();

    /**
     * Get the current player.
     *
     * @return the current player.
     */
    Player getCurrentPlayer();

    /**
     * Get the username.
     *
     * @return the username.
     */
    String getUsername();

    /**
     * Get the winner.
     *
     * @return the winner.
     */
    String getWinner();

    // In the beginning I have a copy of the objects that I need to show to the user
    // when them get updated in the model I have to update them in the view through the controller
}
