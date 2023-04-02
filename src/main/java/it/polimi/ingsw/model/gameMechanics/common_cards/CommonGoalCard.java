package it.polimi.ingsw.model.gameMechanics.common_cards;

import it.polimi.ingsw.model.gameEntity.Library;

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
     * Get the first point on the card.
     * Also removes the point from the list of points.
     *
     * @return the first point on the card
     */
    int getHighestPoint();

    /**
     * Check if the rules of the card are respected.
     *
     * @param library is the library grid
     * @return true if the rules are respected, false otherwise
     */
    boolean checkRules(Library library);
}
