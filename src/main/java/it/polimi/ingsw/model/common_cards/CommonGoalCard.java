package it.polimi.ingsw.model.common_cards;

import it.polimi.ingsw.model.GameData;
import it.polimi.ingsw.model.Library;

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
     * @param gameData is the game data
     * @param name     is the name of the player
     * @return true if the rules are respected, false otherwise
     */
    boolean checkRules(GameData gameData, String name);

    boolean checkRules(Library library);
}
