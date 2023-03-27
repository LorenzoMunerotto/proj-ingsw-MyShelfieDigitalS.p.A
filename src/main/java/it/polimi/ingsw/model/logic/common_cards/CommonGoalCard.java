package it.polimi.ingsw.model.logic.common_cards;

import it.polimi.ingsw.model.data.ItemTile;

import java.util.List;

/**
 * Interface representing the common goal cards.
 */
public interface CommonGoalCard {

    /**
     * Get the index of the card.
     *
     * @return the index of the card
     */
    int getIndex();

    /**
     * Get the points on the card.
     *
     * @return the points on the card
     */
    List<Integer> getPoints();

    /**
     * Check if the rules of the card are respected.
     *
     * @param libraryGrid is the library grid
     * @return true if the rules are respected, false otherwise
     */
    boolean checkRules(ItemTile[][] libraryGrid);
}
